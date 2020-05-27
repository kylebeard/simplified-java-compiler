package cmpt355.project.util;

import java.util.List;
import java.util.Objects;

/**
 * An immutable container for a pair of values ("left" and "right"), possibly of different data types.
 * @param <L> the data type of the "left" value
 * @param <R> the data type of the "right" value
 * @see Lists#zip(List, List)
 */
public class Pair<L, R> {

    /** The "left" value */
    public final L left;
    /** The "right" value */
    public final R right;

    private Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Creates a pair of two values, e.g. {@code Pair.of("red", 5)} returns a Pair whose left value is the String "red"
     * and right value is the Integer 5.
     * @param left  the "left" value
     * @param right the "right" value
     * @param <L>   the type of the "left" value
     * @param <R>   the type of the "right" value
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    /**
     * Returns the "left" value.
     */
    public L getLeft() {
        return left;
    }

    /**
     * Returns the "right" value.
     */
    public R getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) &&
                Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", left, right);
    }
}
