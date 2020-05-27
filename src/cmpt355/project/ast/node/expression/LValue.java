package cmpt355.project.ast.node.expression;

import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;

public abstract class LValue extends Expression {

    public abstract void generateSetCode(CompileContext context, Expression rhs);
}
