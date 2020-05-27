package cmpt355.project.util;

import java.util.List;

/**
 * A functional interface similar to {@link java.util.function.Consumer} that can also throw an exception of a generic
 * type. The standard Consumer interface does not allow for any (checked) exceptions to be thrown which is inconvenient
 * when we wish to apply functional style to methods that may throw.
 * @param <T>  the type of value consumed
 * @param <Ex> the type of exception that may be thrown
 * @see java.util.function
 * @see Lists#forEach(List, ConsumerEx)
 */
@FunctionalInterface
public interface ConsumerEx<T, Ex extends Exception> {

    /**
     * Processes a single value, possibly throwing an exception.
     */
    void accept(T value) throws Ex;
}
