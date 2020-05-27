package cmpt355.project.ast;

import cmpt355.project.ast.node.AstNode;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class representing a path of AST nodes, i.e. a list of nodes where each node is a child of the previous one. Such a
 * class may be helpful when resolving data types and so on in later stages of the front end.
 */
public class AstPath {

    private final List<AstNode> components;

    private AstPath(List<AstNode> components) {
        this.components = Collections.unmodifiableList(components);
    }

    /**
     * Returns an empty AstPath.
     */
    public static AstPath of() {
        return new AstPath(List.of());
    }

    /**
     * Returns an AstPath containing a single node.
     * @throws NullPointerException if root is {@code null}
     */
    public static AstPath of(AstNode root) {
        return new AstPath(List.of(root));
    }

    /**
     * Returns a new AstPath consisting of this path with node appended.
     * @throws NullPointerException if node is {@code null}
     */
    public AstPath appending(AstNode node) {
        if (node == null)
            throw new IllegalArgumentException("node cannot be null");
        var newComponents = new ArrayList<>(components);
        newComponents.add(node);
        return new AstPath(newComponents);
    }

    /**
     * Returns the length of this path (the number of nodes).
     */
    public int length() {
        return components.size();
    }

    /**
     * Returns true if this path is empty (has no nodes).
     */
    public boolean isEmpty() {
        return components.size() == 0;
    }

    /**
     * Returns the last node of this path.
     * @throws IllegalStateException if this path is empty
     */
    public AstNode last() {
        if (isEmpty())
            throw new IllegalStateException("Empty path");
        return components.get(components.size() - 1);
    }

    /**
     * Returns a path that is the same as this one except the last node has been deleted.
     * @throws IllegalStateException if this path is empty
     */
    public AstPath pop() {
        if (isEmpty())
            throw new IllegalStateException("Empty path");
        return new AstPath(components.subList(0, components.size() - 1));
    }

    /**
     * Returns true if this path is a prefix of other, in other words if this path forms the beginning of other. A path
     * is always a prefix of itself. For example,
     * ([a, b] is a prefix of [a, b] and also of [a, b, c, d, e], but [a, b] is not a prefix of [a, c, b, d, e]).
     * @throws NullPointerException if other is {@code null}
     */
    public boolean isPrefix(AstPath other) {
        if (other == this)
            return true;

        if (components.size() > other.components.size())
            return false;
        for (int i = 0; i < components.size(); ++i) {
            if (!components.get(i).equals(other.components.get(i)))
                return false;
        }

        return true;
    }

    /**
     * Returns an iterable of all possible continuations of this path using children of the last node. For example, if
     * this path is [a, b, c] and c has three children x, y, and z, then this method returns an iterable consisting of
     * the three paths [a, b, c, x], [a, b, c, y], and [a, b, c, z].
     */
    public Iterable<AstPath> continuations() {
        return () -> new Iterator<>() {
            private final Iterator<? extends AstNode> children = last().children().iterator();

            @Override
            public boolean hasNext() {
                return children.hasNext();
            }

            @Override
            public AstPath next() {
                return appending(children.next());
            }
        };
    }

    /**
     * Returns the longest prefix of this path for which the given predicate returns true. If this path is [a, b, c],
     * then the method returns one of [a, b, c], [a, b], [a], or the empty path, whichever is the first to be accepted
     * by the predicate.
     */
    public Optional<AstPath> findAncestor(Predicate<AstPath> predicate) {
        var p = this;
        while (!p.isEmpty()) {
            if (predicate.test(p))
                return Optional.of(p);
            p = p.pop();
        }

        return Optional.empty();
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AstPath))
            return false;
        var path = (AstPath)obj;
        return components.equals(path.components);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(20 * length());
        components.forEach(n -> sb.append('{').append(n).append('}'));
        return sb.toString();
    }
}
