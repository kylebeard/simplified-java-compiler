package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.language.DataType;
import cmpt355.project.language.Field;
import cmpt355.project.language.LocalVariable;
import cmpt355.project.language.Parameter;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Objects;

/**
 * AST node representing a "name expression" &mdash; a series of identifiers separated by dots such as {@code x.y.z}.
 *
 * <p>Due to the nature of Java, an expression such as {@code x.y.z} could mean several different things:</p>
 * <ul>
 *     <li>{@code x} could be a variable and {@code y} the name of a field of it (and presumably {@code z} is one of
 *         {@code y}'s fields);</li>
 *     <li>{@code x} could be the name of a class, {@code y} a static field of {@code x}, and {@code z} a field of
 *         {@code y};</li>
 *     <li>{@code x.y} could be the qualified name of a class and {@code z} a static field of {@code x.y}.</li>
 * </ul>
 * <p>It is impossible to know which is the case until there is a symbol table, so a NameExpression just stores it as
 * a {@link QualifiedIdentifier} until names can be resolved.</p>
 */
public class NameExpression extends LValue {

    private QualifiedIdentifier name;
    private LValue resolved = null;

    public NameExpression(QualifiedIdentifier name) {
        setName(name);
    }

    public QualifiedIdentifier getName() {
        return name;
    }

    public LValue getResolved() {
        return resolved;
    }

    private void resolve() throws DataTypeException {
        // First try to resolve first component of name as a variable
        var nameHead = name.head();
        var maybeVariable = findVariable(nameHead);

        if (maybeVariable.isPresent()) {
            // Yay, we found it!
            var variable = maybeVariable.get();
            LValue varRefExpr = null;
            if (variable instanceof LocalVariable)
                varRefExpr = new LocalVariableReference((LocalVariable)variable);
            else if (variable instanceof Parameter)
                varRefExpr = new ParameterReference((Parameter)variable);
            else if (variable instanceof Field)
                varRefExpr = new FieldReference(new ThisExpression(), variable.getName());
            else
                assert (false) : "Unknown variable type";
            // If there are no other components, we're done
            if (name.names.size() == 1)
                resolved = varRefExpr;
                // Otherwise, this is a field reference expression
            else {
                resolved = varRefExpr;
                for (var tail = name.tail(); true; tail = tail.tail()) {
                    resolved = new FieldReference(resolved, tail.head());
                    if (tail.names.size() == 1)
                        break;
                }
            }
        } else {
            // Otherwise, presumably this is a static field reference (so some prefix of name is a qualified class name)
            for (int i = 1; i < name.names.size(); ++i) {
                var maybeClass = findClass(name.head(i).toString());
                if (maybeClass.isPresent()) {
                    String fieldName = name.names.get(i);
                    resolved = new StaticFieldReference(maybeClass.get(), fieldName);

                    for (var tail = name.tail(i); tail.names.size() > 1; tail = tail.tail()) {
                        resolved = new FieldReference(resolved, tail.tail().head());
                    }
                }
            }

            if (resolved == null)
                throw new DataTypeException("Unknown name: " + name.head());
        }

        resolved = reparentNonNull(resolved);
    }

    public void setName(QualifiedIdentifier name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public DataType getType() {
        return getResolved().getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        resolve();
        resolved.postOrderTraversal(AstNode::validateType);
    }

    @Override
    public String toString() {
        return "name expression: " + name.toString();
    }

    @Override
    public List<LValue> children() {
        return Lists.<LValue>builder().addIfPresent(resolved).build();
    }

    @Override
    public void generateCode(CompileContext context) {
        resolved.generateCode(context);
    }

    @Override
    public void generateSetCode(CompileContext context, Expression rhs) {
        resolved.generateSetCode(context, rhs);
    }
}
