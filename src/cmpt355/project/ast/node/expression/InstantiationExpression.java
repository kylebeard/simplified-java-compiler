package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.ClassDefinition;
import cmpt355.project.ast.node.DataTypeNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.Collections;
import java.util.List;

public class InstantiationExpression extends Expression {

    private DataTypeNode typeNode;
    private List<Expression> arguments;
    private Method resolvedConstructor;

    public InstantiationExpression(DataTypeNode typeNode, List<Expression> arguments) {
        this.setTypeNode(typeNode);
        this.setArguments(Collections.unmodifiableList(arguments));
    }

    @Override
    public DataType getType() {
        return typeNode.getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        resolveMethod();
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .add(typeNode)
                .addAll(arguments)
                .build();
    }

    @Override
    public String toString() {
        return "instantiation";
    }

    public void setTypeNode(DataTypeNode typeNode) {
        if (!(typeNode.getType() instanceof ClassType))
            throw new IllegalArgumentException("Type must be a ClassType");
        this.typeNode = reparentNonNull(typeNode);
    }

    public DataTypeNode getTypeNode() {
        return typeNode;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void setArguments(List<Expression> arguments) {
        this.arguments = Collections.unmodifiableList(reparentNonNull(arguments));
    }

    private void resolveMethod() throws DataTypeException {
        if (resolvedConstructor == null) {
            List<Method> methods = typeNode.getType().findMethods(Method.CONSTRUCTOR_NAME);
            var classDef = this.<ClassDefinition>findAncestor(n -> n instanceof ClassDefinition)
                    .orElseThrow(() -> new InternalParserException("Method call not inside a class definition!"));
            OverloadSelector selector = new OverloadSelector(methods);
            selector.requireAccessibleFrom(new InternalClassType(classDef));
            var argumentTypes = Lists.map(arguments, Expression::getType);
            var candidateMethods = selector.select(argumentTypes);

            if (candidateMethods.size() == 0)
                throw new DataTypeException(String.format("No compatible overload found for %s.%s() with argument types %s",
                        typeNode.getType(), Method.CONSTRUCTOR_NAME, argumentTypes));
            else if (candidateMethods.size() > 1) {
                var sb = new StringBuilder(String.format("Multiple overloads found for %s.%s() with argument types %s:\n",
                        typeNode.getType(), Method.CONSTRUCTOR_NAME, argumentTypes));
                for (var overload : candidateMethods) {
                    sb.append("    ")
                            .append(overload.getModifiers())
                            .append(' ')
                            .append(overload.getReturnType())
                            .append(' ')
                            .append(overload.getContainingClass())
                            .append('.')
                            .append(Method.CONSTRUCTOR_NAME)
                            .append('(')
                            .append(String.join(", ", Lists.map(overload.getParameterTypes(), Object::toString)))
                            .append(")\n");
                }

                throw new DataTypeException(sb.toString());
            } else
                resolvedConstructor = candidateMethods.iterator().next();
        }
    }

    public Method getConstructor() {
        return resolvedConstructor;
    }

    @Override
    public void generateCode(CompileContext context) {
        context.addCode(Instruction.new_new(resolvedConstructor.getContainingClass()));
        context.addCode(Instruction.new_dup());
        for (var paramTypeArg : Lists.zip(resolvedConstructor.getParameterTypes(), arguments)) {
            var paramType = paramTypeArg.left;
            var arg = paramTypeArg.right;
            var argType = arg.getType();

            arg.generateCode(context);
            if (paramType instanceof PrimitiveType)
                Helper.convertTypes(context, (PrimitiveType)argType, (PrimitiveType)paramType);
            else
                context.addCode(Instruction.new_checkcast(paramType));
        }
        context.addCode(Instruction.new_invokespecial(resolvedConstructor));
    }
}
