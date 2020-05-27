package cmpt355.project.util;

import java.util.List;

/**
 * A functional interface similar to {@link java.util.function.Predicate} that can also throw an exception of a generic
 * type. The standard Predicate interface does not allow for any (checked) exceptions to be thrown which is inconvenient
 * when we wish to apply functional style to methods that may throw.
 * @param <T>  the type of value consumed
 * @param <Ex> the type of exception that may be thrown
 * @see java.util.function
 * @see Lists#filter(List, PredicateEx)
 */
@FunctionalInterface
public interface PredicateEx<T, Ex extends Exception> {

    /**
     * Given a value, returns either {@code true} or {@code false}, possibly throwing an exception.
     */
    boolean test(T value) throws Ex;

    /**
     * Returns a predicate that is the "and" of this predicate and p: returns {@code true} only if both this and p do.
     */
    default PredicateEx<T, Ex> and(PredicateEx<? super T, ? extends Ex> p) {
        return t -> this.test(t) && p.test(t);
    }

    /**
     * Returns a predicate that is the "or" of this predicate and p: returns {@code true} if either this or p does (or
     * both)
     */
    default PredicateEx<T, Ex> or(PredicateEx<? super T, ? extends Ex> p) {
        return t -> this.test(t) || p.test(t);
    }
}
