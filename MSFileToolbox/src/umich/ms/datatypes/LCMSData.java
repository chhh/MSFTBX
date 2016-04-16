/* 
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.datatypes;

import com.google.common.base.FinalizablePhantomReference;
import com.google.common.base.FinalizableReferenceQueue;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.LCMSDataSource;

/**
 * A wrapper uniting a data source and a scan collection.
 * @author Dmitry Avtonomov
 */
public class LCMSData implements Serializable {
    private static final long serialVersionUID = 7653994906958188619L;

    protected final static transient FinalizableReferenceQueue FRQ = new FinalizableReferenceQueue();
    private final transient Set<User> userPhantomRefs = Sets.newConcurrentHashSet();

    protected LCMSDataSource<?> source;
    protected IScanCollection scans;

    private final Object USER_DUMMY = new Object();
    private transient RemovalListener<Object, Set<LCMSDataSubset>> listener;
    /** Holds weak references to the objects using data as keys. */
    private transient Cache<Object, Set<LCMSDataSubset>> cache;
    protected volatile boolean isReleasingMemory = false;


    /**
     * Will create LCMSData backed up by {@link ScanCollectionDefault} with
     * spectra auto-loading turned off.
     * @param source
     */
    public LCMSData(LCMSDataSource<?> source) {
        this(source, new ScanCollectionDefault(false));
    }

    /**
     * You can pass you IScanCollection implementation, but you likely won't
     * want to do that.
     * @param source
     * @param scans
     */
    public LCMSData(LCMSDataSource<?> source, IScanCollection scans) {
        if (source == null || scans == null) {
            throw new IllegalArgumentException("Both source and scans must be non-null");
        }

        this.source = source;
        this.scans = scans;
        this.scans.setDataSource(source);

        // usage tracking setup
        this.listener = buildRemovalListener();
        this.cache = CacheBuilder.newBuilder()
                .weakKeys()
                .concurrencyLevel(1)
                .removalListener(listener)
                .build();
    }

    public LCMSDataSource<?> getSource() {
        return source;
    }

    public IScanCollection getScans() {
        return scans;
    }

    private RemovalListener<Object, Set<LCMSDataSubset>> buildRemovalListener() {
        return new RemovalListener<Object, Set<LCMSDataSubset>>() {
            @Override public void onRemoval(RemovalNotification<Object, Set<LCMSDataSubset>> notification) {
                if (isReleasingMemory) {
                    return;
                }
                Set<LCMSDataSubset> subsets = notification.getValue();
                for (LCMSDataSubset subset : subsets) {
                    unsafeUnload(subset);
                }
            }
        };
    }

    /**
     * Checks among the loaded by all users if this particular subset has already
     * been loaded by someone else. <b>Note</b> that if for scan
     * collection you've set a {@link StorageStrategy} other than
     * {@link StorageStrategy#STRONG}, this check won't guarantee anything other
     * than the scan meta info was loaded. Spectra might have been garbage collected.
     * You can remedy this by setting {@link IScanCollection#isAutoloadSpectra(boolean) }
     * true, in which case spectra will be automatically loaded, whenever
     * spectrum references are null.
     *
     * @param subset
     * @return
     */
    public synchronized boolean isLoaded(LCMSDataSubset subset) {

        for (Map.Entry<Object, Set<LCMSDataSubset>> entry : cache.asMap().entrySet()) {
            Object user = entry.getKey();
            if (user == null) {
                // it has already been reclaimed, move on
                // this condition should not be triggered though, because we
                // have eviction listener, which should run cleanup whenever
                // any key in the cache becomes null
                continue;
            }
            Set<LCMSDataSubset> subsets = entry.getValue();
            for (LCMSDataSubset subsetInUse : subsets) {
                if (subsetInUse.contains(subset)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Will load scan meta-info and spectra for the specified subset. Will also
     * keep track of loaded subsets. If a subset is loaded, then it is considered
     * to be in use by some component until it doesn't explicitly call
     * {@link #unload(umich.ms.datatypes.LCMSDataSubset) }.
     * @param subset
     * @throws FileParsingException
     */
    public synchronized void load(LCMSDataSubset subset) throws FileParsingException {
        load(subset, USER_DUMMY);
    }

    /**
     * Will load scan meta-info and spectra for the specified subset. Will also
     * keep track of loaded subsets. If a subset is loaded, then it is considered
     * to be in use by some component until it doesn't explicitly call
     * {@link #unload(umich.ms.datatypes.LCMSDataSubset) }.
     * @param subset
     * @param user identify yourself somehow, other components might be using this
     * LCMSData as well, so if you don't provide that identifier, there is no
     * way to tell, for example, when unloading a subset if it's in use by you
     * or someone else. So some other component, might call unload() and without
     * the identifier your spectra will be lost.
     * @throws FileParsingException
     */
    public synchronized void load(LCMSDataSubset subset, Object user) throws FileParsingException {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        // load data, if it's not there yet
        if (!isLoaded(subset)) {
            scans.loadData(subset, null);
        }

        // add the subset
        Set<LCMSDataSubset> userSubsets = cache.getIfPresent(user);
        if (userSubsets == null) {
            addNewUser(user);
            userSubsets = new HashSet<>(2);
            userSubsets.add(subset);
            cache.put(user, userSubsets);
        } else {
            userSubsets.add(subset);
        }
    }

    /**
     * Unloads spectra matched by this subset.
     * @param subset
     */
    public synchronized void unload(LCMSDataSubset subset) {
        //System.err.println("=========== UNLOAD (no-user) CALLED ***********************");
        unload(subset, USER_DUMMY, null);
    }

    /**
     * Unloads spectra matched by this subset.
     * @param subset to be unloaded
     * @param user the user, that has had this subset loaded. If other users have
     * parts of this subset loaded, those parts won't be unloaded.
     * @param exclude can be null. If specified, data from these subsets won't be excluded.
     */
    public synchronized void unload(LCMSDataSubset subset, Object user, Set<LCMSDataSubset> exclude) {
        //System.err.println("=========== UNLOAD (user) CALLED ***********************");
        if (!isLoaded(subset)) {
            throw new IllegalStateException("LCMSData load/unload methods only"
                    + " work for subsets loaded/unloaded using LCMSData API. The"
                    + " subset you requested to unload wasn't loaded. If you've"
                    + " loaded data into ScanCollection manually, then use"
                    + " IScanCollection's API for unloading data manually as"
                    + " well.");
        }
        Set<LCMSDataSubset> userSubsets = cache.getIfPresent(user);
        if (userSubsets == null) {
            throw new IllegalStateException("The user was not present in cache,"
                    + " which means it either has never been there, or has already"
                    + " been reclaimed by GC.");
        }
        if (!userSubsets.contains(subset)) {
            throw new IllegalArgumentException("This user has not loaded the subset"
                    + " in the first place. The user must load the subset using"
                    + " LCMSData API first. Unloading a subset that a user has not"
                    + " loaded itself is illegal.");
        }
        // removing the subset from the user's currently loaded subsets
        userSubsets.remove(subset);

        // getting subsets loaded by all other users of this LCMSData
        ConcurrentMap<Object, Set<LCMSDataSubset>> otherUserMaps = cache.asMap();
        HashSet<LCMSDataSubset> otherUsersSubsetsCombined = new HashSet<>();
        for (Map.Entry<Object, Set<LCMSDataSubset>> entry : otherUserMaps.entrySet()) {
            Object otherUser = entry.getKey();
            if (otherUser != user) {
                // we need to exclude the user requesting the unloading operation from the
                // list of other loaded subsets
                Set<LCMSDataSubset> otherUserSubsets = entry.getValue();
                otherUsersSubsetsCombined.addAll(otherUserSubsets);
            }
        }
        if (exclude != null) {
            otherUsersSubsetsCombined.addAll(exclude);
        }
        if (otherUsersSubsetsCombined.isEmpty()) {
            scans.unloadData(subset);
        } else {
            scans.unloadData(subset, otherUsersSubsetsCombined);
        }
    }

    /**
     * Unloads the subset without checking the user. It's required by our user-tracking
     * automatic cleanup, if a user was GCed and it hasn't unloaded its subsets, we detect
     * the GC automatically, and unload all the subsets, used by this killed user.<br/>
     * It will still check for other users using scans from this subset.
     * @param subset
     */
    private void unsafeUnload(LCMSDataSubset subset) {
        //System.err.println("=========== UNLOAD (unsafe) CALLED ***********************");
        // getting subsets loaded by all other users of this LCMSData
        ConcurrentMap<Object, Set<LCMSDataSubset>> otherUserMaps = cache.asMap();
        HashSet<LCMSDataSubset> otherUsersSubsetsCombined = new HashSet<>();
        for (Map.Entry<Object, Set<LCMSDataSubset>> entry : otherUserMaps.entrySet()) {
            Set<LCMSDataSubset> otherUserSubsets = entry.getValue();
            otherUsersSubsetsCombined.addAll(otherUserSubsets);
//            for (LCMSDataSubset otherSubset : otherUserSubsets) {
//                if (otherSubset != subset) {
//                    otherUsersSubsetsCombined.add(otherSubset);
//                }
//            }
        }
        if (otherUsersSubsetsCombined.isEmpty()) {
            scans.unloadData(subset);
        } else {
            scans.unloadData(subset, otherUsersSubsetsCombined);
        }
    }

    /**
     * Releases all memory by calling {@link LCMSDataSource#releaseMemory() } and
     * {@link IScanCollection#reset() }. Effectively, you get this object to the
     * same state as it was after calling the constructor, however any
     * ScanCollection configurations are preserved (e.g. spectra auto-loading)
     * setting.<br/>
     * <b>IMPORTANT: will clear the registry of loaded subsets without warning!</b>
     * <br/> It's up to you to make sure you don't call this method while some
     * component is still using the data.
     */
    public synchronized void releaseMemory() {

        isReleasingMemory = true;

        userPhantomRefs.clear();
        cache.invalidateAll();
        scans.reset();
        source.releaseMemory();
        //loadedSubsets.clear();

        isReleasingMemory = false;
    }

    private User addNewUser(Object user) {
        User u = new User(user);
        userPhantomRefs.add(u);
        return u;
    }

    /**
     * This is a PhantomRef for the object, that requests loading of data,
     * so we could track it being garbage collected.
     * If a user hasn't explicitly unloaded its data-region(s) from this
     * LCMSData before being garbage collected, we'll notice that using this
     * reference and trigger cleanup.
     */
    protected class User extends FinalizablePhantomReference<Object> {

        public User(Object referent) {
            super(referent, FRQ);
        }

        @Override
        public void finalizeReferent() {
            userPhantomRefs.remove(this);
            // when some user has been reclaimed - fire up cleaning utilities
            LCMSData.this.cache.cleanUp();
        }
    }

}
