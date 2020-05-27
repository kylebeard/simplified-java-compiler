package cmpt355.project.util;

import java.util.List;

/**
 * A functional interface similar to {@link java.util.function.Function} that can also throw an exception of a generic
 * type. The standard Function interface does not allow for any (checked) exceptions to be thrown which is inconvenient
 * when we wish to apply functional style to methods that may throw.
 * @param <T>  the type of value consumed
 * @param <R>  the type of value produced
 * @param <Ex> the type of exception that may be thrown
 * @see java.util.function
 * @see Lists#map(List, FunctionEx)
 */
public interface FunctionEx<T, R, Ex extends Exception> {

    /**
     * Maps a single T value to one of type R, possibly throwing an exception.
     */
    R apply(T x) throws Ex;
}
