package cmpt355.project.util;

public interface PartialOrdering<E> {

    boolean lessThan(E first, E second);
}
