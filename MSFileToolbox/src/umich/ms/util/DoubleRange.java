package umich.ms.util;

/**
 * Represents a range of doubles, e.g. retention times.
 * Created by dmitriya on 2015-03-04.
 */
public class DoubleRange extends Interval1D<Double> {
    public DoubleRange(Double left, Double right) {
        super(left, right);
    }

    public static DoubleRange fromInterval1D(Interval1D<Double> interval) {
        return new DoubleRange(interval.lo, interval.hi);
    }

    /**
     * If the interval contains just a single point (i.e. this.lo == this.hi), then the overlap is 0.<br/>
     * Consider using {@link #overlapRelative(DoubleRange)}.
     * @param other interval to compare to
     * @return 0 if one of the intervals is a single point
     */
    public double overlapAbsolute(DoubleRange other) {
        if (!intersects(other)) {
            return 0;
        }
        int loCmp = lo.compareTo(other.lo);
        int hiCmp = hi.compareTo(other.hi);
        if (loCmp >= 0 && hiCmp <= 0) {
            return this.length();
        } else if (loCmp <= 0 && hiCmp >= 0) {
            return other.length();
        } else {
            double newLo = (loCmp >= 0) ? this.lo : other.lo;
            double newHi = (hiCmp <= 0) ? this.hi : other.hi;
            return newHi - newLo;
        }
    }

    /**
     * Relative overlap, that is the overlap, divided by the length of the largest interval.<br/>
     * If both intervals are single points and they're equal - returns 1
     * @param other interval to compare to
     * @return if the intervals are single points will return 1, if the points are the same. If a point is compared
     * against a non-point interval, then the result is zero.
     */
    public double overlapRelative(DoubleRange other) {
        double lenThis = length();
        double lenOther = other.length();
        if (lenThis == 0 && lenOther == 0) {
            // check for single points being compared, if it's the same point overlap is 1
            // if points are different, overlap is 0
            return this.equals(other) ? 1 : 0;
        }
        
        double overlapAbs = overlapAbsolute(other);
        return overlapAbs / Math.max(lenThis, lenOther); // one of the lengths is guaranteed to be non-zero
    }

    public final double length() {
        return hi - lo;
    }
}
