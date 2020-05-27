package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.node.ClassDefinition;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.codegen.Helper;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Objects;

public class SuperMethodCallExpression extends Expression {

    private String methodName;
    private List<Expression> arguments;
    private Method resolvedMethod;

    public SuperMethodCallExpression(String methodName, List<Expression> arguments) {
        this.setMethodName(methodName);
        this.setArguments(arguments);
    }

    @Override
    public DataType getType() {
        return getMethod().getReturnType();
    }

    @Override
    public void validateType() throws DataTypeException {
        resolveMethod();
    }

    @Override
    public List<Expression> children() {
        return arguments;
    }

    @Override
    public String toString() {
        return String.format("Super method call: super.%s()", getMethodName());
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = Objects.requireNonNull(methodName);
    }

    private void resolveMethod() throws DataTypeException {
        if (resolvedMethod == null) {
            var classDef = this.<ClassDefinition>findAncestor(n -> n instanceof ClassDefinition)
                    .orElseThrow(() -> new InternalParserException("Method call not inside a class definition!"));
            var superclass = new InternalClassType(classDef).getSupertype()
                    .orElseThrow(() -> new InternalParserException("Cannot locate superclass of " + classDef.getQualifiedName()));
            List<Method> methods = superclass.findMethods(methodName);
            OverloadSelector selector = new OverloadSelector(methods);
            selector.requireAccessibleFrom(new InternalClassType(classDef));
            var argumentTypes = Lists.map(arguments, Expression::getType);
            var candidateMethods = selector.select(argumentTypes);

            if (candidateMethods.size() == 0)
                throw new DataTypeException(String.format("No compatible overload found for %s.%s() with argument types %s",
                        superclass, methodName, argumentTypes));
            else if (candidateMethods.size() > 1) {
                var sb = new StringBuilder(String.format("Multiple overloads found for %s.%s() with argument types %s:\n",
                        superclass, methodName, argumentTypes));
                for (var overload : candidateMethods) {
                    sb.append("    ")
                            .append(overload.getModifiers())
                            .append(' ')
                            .append(overload.getReturnType())
                            .append(' ')
                            .append(overload.getContainingClass())
                            .append('.')
                            .append(methodName)
                            .append('(')
                            .append(String.join(", ", Lists.map(overload.getParameterTypes(), Object::toString)))
                            .append(")\n");
                }

                throw new DataTypeException(sb.toString());
            } else {
                resolvedMethod = candidateMethods.iterator().next();
            }
        }
    }

    public Method getMethod() {
        return resolvedMethod;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void setArguments(List<Expression> arguments) {
        this.arguments = reparentNonNull(arguments);
    }

    @Override
    public void generateCode(CompileContext context) {
        context.addCode(Instruction.new_aload_0());

        for (var paramTypeArg : Lists.zip(resolvedMethod.getParameterTypes(), arguments)) {
            var arg = paramTypeArg.right;
            DataType paramType = paramTypeArg.left,
                     argType = arg.getType();
            arg.generateCode(context);

            if (paramType instanceof PrimitiveType)
                Helper.convertTypes(context, (PrimitiveType)argType, (PrimitiveType)paramType);
            else
                context.addCode(Instruction.new_checkcast(paramType));
        }

        context.addCode(Instruction.new_invokespecial(resolvedMethod));
    }
}

