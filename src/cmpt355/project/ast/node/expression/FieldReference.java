package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Objects;

import static cmpt355.project.language.PrimitiveType.*;

public class FieldReference extends LValue {

    private Expression expr;
    private String fieldName;
    private Field resolved;

    public FieldReference(Expression expr, String fieldName) {
        this.setExpr(expr);
        this.setFieldName(fieldName);
    }

    public FieldReference(Expression expr, Field field) {
        this(expr, field.getName());
        this.resolved = field;
    }

    @Override
    public DataType getType() {
        return getVariable().getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        if (resolved == null) {
            var exprType = expr.getType();
            var maybeVar = exprType.findField(fieldName);
            resolved = maybeVar.orElseThrow(() ->
                    new DataTypeException(String.format("Cannot resolve field %s.%s", exprType, fieldName)));
        }
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .add(expr)
                .addIfPresent(resolved)
                .build();
    }

    @Override
    public String toString() {
        return String.format("field reference: %s", fieldName);
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = reparentNonNull(expr);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = Objects.requireNonNull(fieldName);
    }

    public Field getVariable() {
        return resolved;
    }

    @Override
    public void generateCode(CompileContext context) {
        // Complications:
        //   - ArrayLengthFields are handled differently from other fields
        //   - static fields can be referenced in a static context (e.g. new Math().round(...)). When this happens, we
        //     we still need to evaluate the callee, but we will have to pop it before using getstatic.

        // Generate the code for the object whose field is being accessed
        expr.generateCode(context);

        if (resolved instanceof ArrayLengthField)
            // If this is an array.length field, use the arraylength instruction
            context.addCode(Instruction.new_arraylength());
        else {
            if (resolved.getModifiers().contains(Modifier.STATIC)) {
                // If the field is static, pop the object and do getstatic
                context.addCode(Instruction.new_pop());
                context.addCode(Instruction.new_getstatic(resolved));
            } else
                // The "normal" cases - use getfield
                context.addCode(Instruction.new_getfield(resolved));
        }
    }

    @Override
    public void generateSetCode(CompileContext context, Expression rhs) {
        var varType = resolved.getType();
        var rhsType = rhs.getType();

        expr.generateCode(context);
        boolean staticField = resolved.getModifiers().contains(Modifier.STATIC);

        // Ugh
        if (staticField) {
            context.addCode(Instruction.new_pop());
            rhs.generateCode(context);

            if (varType instanceof PrimitiveType)
                Helper.convertTypes(context, (PrimitiveType)rhsType, (PrimitiveType)varType);
            else
                context.addCode(Instruction.new_checkcast(varType));

            // value →
            Helper.dup(context, varType);
            // value value →

            context.addCode(Instruction.new_putstatic(resolved));
            // value →
        } else {
            rhs.generateCode(context);

            if (varType instanceof PrimitiveType)
                Helper.convertTypes(context, (PrimitiveType)rhsType, (PrimitiveType)varType);
            else
                context.addCode(Instruction.new_checkcast(varType));

            // object value →
            Helper.dup_x1(context, varType);
            // value object value →

            context.addCode(Instruction.new_putfield(resolved));
            // value →
        }
    }
}
