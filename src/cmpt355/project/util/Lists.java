package cmpt355.project.util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class with static methods helpful for constructing and working with lists.
 */
public class Lists {

    /**
     * Creates a new {@link Builder} object to be used in constructing a list.
     * @param <T> the element type of the list that will eventually be generated
     */
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * Returns a new list consisting of the result of applying f to every element of list. Any exception thrown by f
     * while mapping elements is propagated out.
     *
     * Example use:
     * <pre><code>
     *      List&lt;String&gt; strings = ...;
     *      List&lt;Integer&gt; numbers = Lists.map(strings, Integer::parseInt); // throws {@link NumberFormatException} if parseInt() does on any of the strings
     * </code></pre>
     *
     * @param list   the list of values to be mapped
     * @param f      the mapping function to apply
     * @param <From> the data type being mapped from
     * @param <To>   the data type being mapped to
     * @param <Ex>   the type of exception that may be thrown
     * @return a new list of the mapped values
     * @throws Ex if f throws
     */
    public static <From, To, Ex extends Exception> List<To> map(List<? extends From> list, FunctionEx<From, To, Ex> f)
            throws Ex {
        var newList = new ArrayList<To>(list.size());
        for (var from : list)
            newList.add(f.apply(from));
        return newList;
    }

    /**
     * Applies f to every element of list. Any exceptions thrown by f are propagated out.
     * Example use:
     * <pre><code>
     *     List&lt;String&gt; strings = List.of("x", "y", "z");
     *     Lists.forEach(strings, s -&gt; System.out.printf("%s is a fine kettle of fish.\n"));
     * </code></pre>
     * The above code will print
     * <pre><code>
     *     x is a fine kettle of fish.
     *     y is a fine kettle of fish.
     *     z is a fine kettle of fish.
     * </code></pre>
     * @param list the list of values to apply f to
     * @param f    the function to apply to list values
     * @param <E>  the data type of the values
     * @param <Ex> the type of exception that may be thrown
     * @throws Ex if f throws
     */
    public static <E, Ex extends Exception> void forEach(List<? extends E> list, ConsumerEx<E, Ex> f) throws Ex {
        for (var value : list)
            f.accept(value);
    }

    /**
     * Returns a list of the values from list that satisfy the predicate p. Any exceptions thrown by p are propagated
     * out.
     * Example use:
     * <pre><code>
     *     List&lt;String&gt; strings = List.of("a", "bb", "ccc", "dddd");
     *     List&lt;String&gt; evenStrings = Lists.filter(strings, s -&gt; s.length() % 2 == 0);
     * </code></pre>
     * In the above code, evenStrings will be ["bb", "dddd"] as those are the two strings whose length is even.
     * @param list a list of values
     * @param p    a predicate to apply to the values (takes a value as parameter and returns true or false)
     * @param <E>  the data type of the values
     * @param <Ex> the type of exception that may be thrown
     * @return a list consisting of all values from list for which p returns true
     * @throws Ex if p throws
     */
    public static <E, Ex extends Exception> List<E> filter(List<E> list, PredicateEx<E, Ex> p) throws Ex {
        var newList = new ArrayList<E>(list.size());
        for (var value : list)
            if (p.test(value))
                newList.add(value);
        return newList;
    }

    /**
     * Like Python's zip() builtin function, returns an iterable of pairs of values from the left and right lists. (In
     * other words, makes it possible to use an enhanced for loop to iterate over values from two lists at the same
     * time.)
     * Example use:
     * <pre><code>
     *     List&lt;String&gt; strings = List.of("A", "B", "C");
     *     List&lt;Integer&gt; ints = List.of(1, 2, 3);
     *     for (var pair : Lists.zip(strings, ints)) {
     *         System.out.printf("%s / %d\n", pair.left, pair.right);
     *     }
     * </code></pre>
     * The above code prints
     * <pre><code>
     *     A / 1
     *     B / 2
     *     C / 3
     * </code></pre>
     * @param left  the list of "left" values
     * @param right the list of "right" values
     * @param <L>   the data type of the "left" values
     * @param <R>   the data type of the "right" values
     * @throws IllegalArgumentException if left.size() != right.size()
     */
    public static <L, R> Iterable<Pair<L, R>> zip(List<? extends L> left, List<? extends R> right) {
        if (left.size() != right.size())
            throw new IllegalArgumentException("left and right lists are of different size");

        return () -> new Iterator<>() {
            private Iterator<? extends L> leftIter = left.iterator();
            private Iterator<? extends R> rightIter = right.iterator();

            @Override
            public boolean hasNext() {
                return leftIter.hasNext() && rightIter.hasNext();
            }

            @Override
            public Pair<L, R> next() {
                return Pair.of(leftIter.next(), rightIter.next());
            }
        };
    }

    /**
     * Returns a reversed version of the given list; for example, if list is [1, 2, 3], this method returns [3, 2, 1].
     * The returned list is just a view of the parameter so no list values are actually copied. Consequently this method
     * would be <em>extremely</em> inefficient to use if the parameter list were, say, a linked list. Accordingly, this
     * method throws an IllegalArgumentException if the provided List does not implement {@link RandomAccess}. ArrayList
     * is safe to use; if you have a non-random-access list that you want reversed and you're willing to make a copy of
     * the list contents, use {@code Lists.reversed(new ArrayList&lt;&gt;(list)}.
     * @param list a list whose contents are to be reversed
     * @param <E>  the data type of list elements
     * @return a new list with the same contents but in reverse order (does not actually copy the list contents)
     * @throws IllegalArgumentException if list does not implement RandomAccess
     */
    public static <E> List<E> reversed(final List<? extends E> list) {
        if (!(list instanceof RandomAccess))
            throw new IllegalStateException("Provided list is not random access");

        class ReversedList extends AbstractList<E> implements RandomAccess {
            private final int n = list.size() - 1;

            @Override
            public E get(int index) {
                return list.get(n - index);
            }

            @Override
            public int size() {
                return n + 1;
            }
        }

        return new ReversedList();
    }

    /**
     * A helper class (following the <a href="https://dzone.com/articles/design-patterns-the-builder-pattern">builder</a>
     * pattern) for creating a list.
     *
     * <p>For example, the following code will create a list:</p>
     * <pre><code>List&lt;AstNode&gt; list = Lists.&lt;AstNode&gt;builder()
     *     .add(n1)
     *     .addIfPresent(n2)
     *     .addAll(listOfNodes)
     *     .build();</code></pre>
     * <p>The returned list consists of n1, then n2 (if it's not <code>null</code>), then every element of
     * listOfNodes.</p>
     *
     * @param <T> the element type of the list that will eventually be built
     */
    public static final class Builder<T> {
        private final List<List<? extends T>> lists = new ArrayList<>();

        private Builder() {}

        /**
         * Adds a single value to the list. If value is <code>null</code>, then one of the list elements will be
         * <code>null</code>.
         * @return the Builder so that further methods can be chained
         */
        public Builder<T> add(T value) {
            return addAll(List.of(value));
        }

        /**
         * Adds a single value to the list. If value is <code>null</code>, then it is not added and the list is
         * unchanged.
         * @return the Builder so that further methods can be chained
         */
        public Builder<T> addIfPresent(T value) {
            if (value != null)
                return addAll(List.of(value));
            return this;
        }

        /**
         * Adds a single value to the list. If maybeValue is empty, then it is not added and the list is
         * unchanged.
         * @return the Builder so that further methods can be chained
         */
        public Builder<T> addIfPresent(Optional<? extends T> maybeValue) {
            maybeValue.ifPresent(this::add);
            return this;
        }

        /**
         * Adds a list of values to the list. Every element of list is added individually.
         * @return the Builder so that further methods can be chained
         */
        public Builder<T> addAll(List<? extends T> list) {
            lists.add(list);
            return this;
        }

        /**
         * Builds the final list. The returned list is merely a view into the elements and lists that were added to the
         * builder, so the list elements are not duplicated.
         */
        public List<T> build() {
            return new AbstractList<T>() {

                private int totalSize = 0;

                {
                    for (var list : lists)
                        totalSize += list.size();
                }

                @Override
                public T get(int index) {
                    int baseIndex = 0;
                    for (var list : lists) {
                        if (baseIndex + list.size() > index)
                            return list.get(index - baseIndex);
                        baseIndex += list.size();
                    }
                    return null;
                }

                @Override
                public int size() {
                    return totalSize;
                }

                @Override
                public Iterator<T> iterator() {
                    return new Iterator<T>() {
                        Iterator<List<? extends T>> majorIterator = lists.iterator();
                        Iterator<? extends T> minorIterator;

                        @Override
                        public boolean hasNext() {
                            while (true) {
                                if ((minorIterator == null || !minorIterator.hasNext())
                                        && !majorIterator.hasNext())
                                    return false;
                                else if (minorIterator == null || !minorIterator.hasNext())
                                    minorIterator = majorIterator.next().iterator();

                                if (minorIterator.hasNext())
                                    return true;
                            }
                        }

                        @Override
                        public T next() {
                            if (!hasNext())
                                throw new NoSuchElementException();
                            return minorIterator.next();
                        }
                    };
                }
            };
        }
    }
}
