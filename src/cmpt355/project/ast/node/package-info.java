/**
 * AST (Abstract Syntax Tree) node classes for the Java compiler.
 *
 * The key classes are
 * <ul>
 *      <li>{@link cmpt355.project.ast.node.AstNode} &mdash; abstract base class of all AST nodes; provides utility
 *          methods for finding ancestor nodes and walking the tree</li>
 *      <li>{@link cmpt355.project.ast.node.Statement} &mdash; base class for statement-like nodes</li>
 *      <li>{@link cmpt355.project.ast.node.Expression} &mdash; base class for expression-like nodes</li>
 * </ul>
 *
 * There are some AST nodes not defined in this package, the most important of which are the
 * {@link cmpt355.project.language.DataType} family of classes in the {@link cmpt355.project.language} package.
 */
package cmpt355.project.ast.node;