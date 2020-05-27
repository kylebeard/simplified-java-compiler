package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.AstPath;
import cmpt355.project.ast.SymbolHolder;
import cmpt355.project.ast.SymbolTable;
import cmpt355.project.language.ClassType;
import cmpt355.project.language.Variable;
import cmpt355.project.util.ConsumerEx;
import cmpt355.project.util.Lists;
import cmpt355.project.util.Trees;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Abstract base class for abstract syntax tree nodes. Defines some utility methods for AST traversal. Subclasses of
 * AstNode override {@link #children()} so that the AST can be traversed.
 *
 * <p><strong>Important note:</strong> since many kinds of AST nodes need to be able to climb the tree (towards the
 * root) &mdash; for example, a node representing reference to a variable will need to find the declaration to that
 * variable in an enclosing scope to determine its data type &mdash; it is very important that every AstNode has its
 * parent node set. The recommended way to do this is to use the {@link #reparent(AstNode)} and related methods, for
 * example</p>
 * <pre><code>
 *     public class WhileLoop extends Statement {
 *
 *         private Expression condition;
 *
 *         public void setCondition(Expression condition) {
 *             this.condition = reparentNonNull(condition);
 *         }
 *     }
 * </code></pre>
 * <p>({@link #reparentNonNull(AstNode)} calls {@link #setParentNode(AstNode)} and throws an exception if the argument
 * is null, making sure that the parent is set and that the argument to setCondition() was not null simultaneously.)</p>
 */
public abstract class AstNode {

    private AstNode parentNode;

    protected AstNode() {
        this(null);
    }

    protected AstNode(AstNode parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * Typechecks this AST node, ensuring that data types are used correctly in it. The default version does nothing;
     * subclasses of AstNode should override this method if they have anything to do with data types.
     *
     * This method should only be called on a node after it has been called on all of the node's children. In other
     * words this method should be called in a post-order traversal.
     * @throws DataTypeException if there is a data type error involving this node
     */
    public void validateType() throws DataTypeException {
        // Default does nothing
    }

    /**
     * Returns the parent node of this AstNode, or <code>null</code> if the node has no parent. (By the time the AST is
     * done being built, the only node with no parent should be the root [see the note at the top of the class about
     * parent nodes].)
     */
    public AstNode getParentNode() {
        return parentNode;
    }

    /**
     * Sets the parent node of this node. Every AstNode except the root should have its parent set.
     */
    public void setParentNode(AstNode parentNode) {
        this.parentNode = parentNode;
    }

    private <T> Optional<T> lookupInSymbolTable(String name, BiFunction<SymbolTable, String, Optional<T>> lookup) {
        for (AstNode node = parentNode; node != null; node = node.parentNode) {
            if (node instanceof SymbolHolder) {
                var table = ((SymbolHolder)node).getSymbolTable();
                var lookupValue = lookup.apply(table, name);

                if (lookupValue.isPresent())
                    return lookupValue;
            }
        }

        return Optional.empty();
    }

    /**
     * Convenience method to look up a variable from all symbol holder parents of this node. Returns the variable found
     * from the closest ancestor SymbolHolder node, or an empty Optional if not found.
     */
    public Optional<? extends Variable> findVariable(String varName) {
        return lookupInSymbolTable(varName, SymbolTable::lookupVariable);
    }

    /**
     * Convenience method to look up a class type from all symbol holder parents of this node. Returns the class found
     * from the closest ancestor SymbolHolder node, or an empty Optional if not found.
     */
    public Optional<? extends ClassType> findClass(String className) {
        return lookupInSymbolTable(className, SymbolTable::lookupClass);
    }

    /**
     * Convenience method to be used by AstNode subclasses: if <code>node</code> is non-null, set its parent to this.
     * Either way, return it. Equivalent to
     * <pre><code>
     *     if (node != null)
     *         node.setParentNode(this);
     *     return node;
     * </code></pre>
     * @param node the node to set the parent of
     * @param <N>  the type of the node being set (so that the return type of reparent() is the same as whatever node
     *             was given so casts are not necessary)
     * @return the same node (after setting its parent)
     */
    protected <N extends AstNode> N reparent(N node) {
        if (node != null)
            node.setParentNode(this);
        return node;
    }

    /**
     * Convenience method to be used by AstNode subclasses: if <code>list</code> is non-null, set each node it contains
     * to this; either way, return it. Equivalent to
     * <pre><code>
     *     if (list != null)
     *         list.forEach(elem -&gt; elem.setParentNode(this));
     *     return list;
     * </code></pre>
     * @param list a list of nodes to set the parent of
     * @param <N>  the type of the elements of list
     * @param <L>  the type of the list
     * @return the same list (after setting parent of each element)
     */
    protected <N extends AstNode, L extends List<? extends N>> L reparent(L list) {
        if (list != null)
            list.forEach(elem -> elem.setParentNode(this));
        return list;
    }

    /**
     * Similar to {@link #reparent(AstNode)} but throws a NullPointerException if node is <code>null</code>.
     * @param node the node to set the parent of
     * @param <N>  the type of the node being set (so that the return type of reparentNonNull() is the same as whatever
     *             was given so casts are not necessary)
     * @return the same node (after setting its parent)
     * @throws NullPointerException if node is <code>null</code>
     */
    protected <N extends AstNode> N reparentNonNull(N node) {
        Objects.requireNonNull(node);
        node.setParentNode(this);
        return node;
    }

    /**
     * Similar to {@link #reparent(List)} but throws a NullPointerException if <code>list</code> is null.
     * @param list a list of nodes to set the parent of
     * @param <N>  the type of the elements of list
     * @param <L>  the type of the list
     * @return the same list (after setting parent of each element)
     * @throws NullPointerException if list is <code>null</code>
     */
    protected <N extends AstNode, L extends List<? extends N>> L reparentNonNull(L list) {
        Objects.requireNonNull(list);
        list.forEach(elem -> elem.setParentNode(this));
        return list;
    }

    /**
     * Returns a list of all AstNode children of this node. (The {@link List#of()} and {@link Lists#builder()} methods
     * are convenient ways to create such lists.)
     *
     * <p>Please note that tree traversal is performed by calling this method on nodes of the AST; therefore any node
     * not included in its parent's children() is effectively not in the AST.</p>
     */
    public List<? extends AstNode> children() {
        return List.of();
    }

    /**
     * Returns the first ancestor node of this node for which the predicate returns true. For example,
     * <code>node.findAncestor(n -&gt; n instanceof Statement)</code> will return the first ancestor that is a
     * Statement, or an empty Optional if there is no such ancestor.
     * @param predicate
     * @param <N> type of the value returned in the optional, so that code such as
     *           <code>Optional&lt;Optional&gt; stmt = node.findAncestor(n -&gt; n instanceof Statement);</code> is allowed
     * @return an Optional containing the nearest ancestor matching the predicate, or an empty Optional if there is no
     * such ancestor
     */
    @SuppressWarnings("unchecked")
    public <N> Optional<N> findAncestor(Predicate<? super AstNode> predicate) {
        AstNode ancestor = this.parentNode;
        while (ancestor != null && !predicate.test(ancestor)) {
            ancestor = ancestor.parentNode;
        }

        return Optional.ofNullable((N)ancestor);
    }

    /**
     * Returns a String representation of this node. This method is declared <code>abstract</code> in AstNode to force
     * subclasses to implement it.
     */
    public abstract String toString();

    /**
     * Performs a depth-first post-order traversal of the AST rooted at this node, calling f.accept() on each node.
     * @param f the operation to apply to each node
     */
    public <Ex extends Exception> void postOrderTraversal(ConsumerEx<AstNode, Ex> f) throws Ex {
        Trees.postOrder(this, AstNode::children, f);
    }

    /**
     * Performs a depth-first pre-order traversal of the AST rooted at this node, calling f.accept() on each node.
     * @param f the operation to apply to each node
     */
    public <Ex extends Exception> void preOrderTraversal(ConsumerEx<AstNode, Ex> f) throws Ex {
        Trees.preOrder(this, AstNode::children, f);
    }

    /**
     * Performs a depth-first post-order traversal of the AST rooted at this node. An {@link AstPath} is formed for
     * each node consisting of the path from this node to the descendant, and this path is passed to f.accept().
     * @param f the operation to apply to each AstPath
     */
    public <Ex extends Exception> void postOrderPathTraversal(ConsumerEx<AstPath, Ex> f) throws Ex {
        Trees.postOrder(AstPath.of(this), AstPath::continuations, f);
    }

    /**
     * Performs a depth-first pre-order traversal of the AST rooted at this node. An {@link AstPath} is formed for
     * each node consisting of the path from this node to the descendant, and this path is passed to f.accept().
     * @param f the operation to apply to each AstPath
     */
    public <Ex extends Exception> void preOrderPathTraversal(ConsumerEx<AstPath, Ex> f) throws Ex {
        Trees.preOrder(AstPath.of(this), AstPath::continuations, f);
    }

    /**
     * Returns a Stream containing all nodes in the AST rooted at this node in a depth-first post-order search.
     */
    public Stream<? extends AstNode> postOrderTraversal() {
        return Trees.postOrder(this, AstNode::children);
    }

    /**
     * Returns a Stream containing all nodes in the AST rooted at this node in a depth-first pre-order search.
     */
    public Stream<? extends AstNode> preOrderTraversal() {
        return Trees.preOrder(this, AstNode::children);
    }
}
