package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.*;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Optional;

/**
 * AST node representing a method called on an object.
 */
public class MethodCallExpression extends Expression {

    private Expression callee;
    private String methodName;
    private List<Expression> arguments;
    private Method resolvedMethod;

    public MethodCallExpression(Expression callee, String methodName, List<Expression> arguments) {
        this.setCallee(callee);
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
    public List<AstNode> children() {
        return Lists.<AstNode>builder().addIfPresent(getCallee()).addAll(getArguments()).build();
    }

    @Override
    public String toString() {
        if (resolvedMethod != null)
            return String.format("method call: %s %s", resolvedMethod.getName(), resolvedMethod.getJvmSignature());
        else
            return String.format("method call: %s()", methodName);
    }

    public Optional<Expression> getCallee() {
        return Optional.ofNullable(callee);
    }

    public void setCallee(Expression callee) {
        this.callee = reparent(callee);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    private void resolveMethod() throws DataTypeException {
        if (resolvedMethod == null) {
            List<Method> methods;

            var classDef = this.<ClassDefinition>findAncestor(n -> n instanceof ClassDefinition)
                    .orElseThrow(() -> new DataTypeException("Method call not inside a class definition!"));

            boolean staticContext;

            if (callee == null) {
                Optional<MethodDefinition> maybeEnclosingMethod  = findAncestor(n -> n instanceof MethodDefinition);
                if (maybeEnclosingMethod.isPresent())
                    staticContext = maybeEnclosingMethod.get().getModifiers().contains(Modifier.STATIC);
                else {
                    Optional<FieldDeclaration> maybeEnclosingField = findAncestor(n -> n instanceof FieldDeclaration);
                    var enclosingField = maybeEnclosingField.orElseThrow(() -> new DataTypeException("Method call not inside method call or field initialization!"));
                    staticContext = enclosingField.getModifiers().contains(Modifier.STATIC);
                }

                var classType = new InternalClassType(classDef);
                methods = classType.findMethods(methodName);
            } else {
                staticContext = false;
                methods = callee.getType().findMethods(methodName);
            }

            OverloadSelector selector = new OverloadSelector(methods);
            selector.requireAccessibleFrom(new InternalClassType(classDef));
            if (staticContext)
                selector.requireModifier(Modifier.STATIC);
            var argumentTypes = Lists.map(arguments, Expression::getType);
            var candidateMethods = selector.select(argumentTypes);

            if (candidateMethods.size() == 0) {
                if (callee == null)
                    throw new DataTypeException(String.format("No compatible overload found for %s() with argument types %s",
                            methodName, argumentTypes));
                else
                    throw new DataTypeException(String.format("No compatible overload found for %s.%s() with argument types %s",
                            callee.getType(), methodName, argumentTypes));
            } else if (candidateMethods.size() > 1) {
                var sb = new StringBuilder(String.format("Multiple overloads found for %s.%s() with argument types %s:\n",
                        callee.getType(), methodName, argumentTypes));
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
                if (callee == null && !staticContext) {
                    callee = reparent(new ThisExpression());
                    callee.validateType();
                }
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
        callee.generateCode(context);
        var isStaticMethod = resolvedMethod.getModifiers().contains(Modifier.STATIC);
        // if method is static we dont need a reference to callee
        if (isStaticMethod)
            context.addCode(Instruction.new_pop());

        for (var paramTypeArg : Lists.zip(resolvedMethod.getParameterTypes(), arguments)){
            var paramType = paramTypeArg.left;
            var arg = paramTypeArg.right;
            var argType = arg.getType();

            arg.generateCode(context);
            if (paramType instanceof PrimitiveType)
                Helper.convertTypes(context, (PrimitiveType)argType, (PrimitiveType)paramType);
            else
                context.addCode(Instruction.new_checkcast(paramType));
        }

        if(isStaticMethod)
            context.addCode(Instruction.new_invokestatic(resolvedMethod));
        else
            context.addCode(Instruction.new_invokevirtual(resolvedMethod));
    }
}
