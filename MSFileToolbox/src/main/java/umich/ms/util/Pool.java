package umich.ms.util;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Pool<T> {
  private final Supplier<T> factory;
  private final Consumer<T> cleanup;
  private final ConcurrentLinkedDeque<T> pool = new ConcurrentLinkedDeque<>();

  public Pool(Supplier<T> factory, Consumer<T> cleanup) {
    this.factory = factory;
    this.cleanup = cleanup != null ? cleanup : (t) -> {
    };
  }

  public T borrow() {
    T t = pool.pollFirst();
    return t != null ? t : factory.get();
  }

  public void surrender(T t) {
    cleanup.accept(t);
    pool.addLast(t);
  }
}
