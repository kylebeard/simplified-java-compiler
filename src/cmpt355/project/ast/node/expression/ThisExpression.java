package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.node.ClassDefinition;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.FieldDeclaration;
import cmpt355.project.ast.node.MethodDefinition;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.InternalClassType;
import cmpt355.project.language.Modifier;

import java.util.Optional;

public class ThisExpression extends Expression {

    private InternalClassType type;

    public ThisExpression() {}

    @Override
    public DataType getType() {
        if (type == null) {
            Optional<ClassDefinition> maybeClassDef = findAncestor(n -> n instanceof ClassDefinition);
            var classDef = maybeClassDef.orElseThrow(() -> new InternalParserException("this expression without ClassDefinition ancestor!"));
            type = new InternalClassType(classDef);
        }

        return type;
    }

    @Override
    public void validateType() throws DataTypeException {
        Optional<MethodDefinition> maybeMethod = findAncestor(n -> n instanceof MethodDefinition);
        if (maybeMethod.isPresent()) {
            var method = maybeMethod.get();
            if (method.getModifiers().contains(Modifier.STATIC))
                throw new DataTypeException("'this' used inside static method " + method.getName());
        } else {
            Optional<FieldDeclaration> maybeField = findAncestor(n -> n instanceof FieldDeclaration);
            if (maybeField.isPresent()) {
                var field = maybeField.get();
                if (field.getModifiers().contains(Modifier.STATIC))
                    throw new DataTypeException("'this' used in initialization of static field " + field.getName());
            } else
                throw new InternalParserException("'this' not inside either method definition or field initialization");
        }
    }

    @Override
    public String toString() {
        return "this";
    }

    @Override
    public void generateCode(CompileContext context) {
        context.addCode(Instruction.new_aload_0());
    }
}
