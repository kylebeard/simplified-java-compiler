package cmpt355.project.ast.node.expression;

import cmpt355.project.InternalCompilerException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.node.DataTypeNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.ExternalClassType;
import cmpt355.project.language.PrimitiveType;

import java.util.Objects;
import java.util.regex.Pattern;

public class Literal extends Expression {

    private static final Pattern HEXADECIMAL_ESCAPE
            = Pattern.compile("^(u[0-9a-fA-F]{4})");
    private static final Pattern OCTAL_ESCAPE
            = Pattern.compile("^([0-7]|[0-7][0-7]|[0-3][0-7][0-7])");

    private DataTypeNode typeNode;
    private String text;

    public Literal(DataTypeNode typeNode, String text) {
        this.setTypeNode(typeNode);
        this.setText(text);
    }

    @Override
    public DataType getType() {
        return typeNode.getType();
    }

    @Override
    public void validateType() {
        // Literals never need to be validated
    }

    @Override
    public String toString() {
        return "literal: " + getText();
    }

    public void setTypeNode(DataTypeNode typeNode) {
        this.typeNode = reparentNonNull(typeNode);
    }

    public DataTypeNode getTypeNode() {
        return typeNode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = Objects.requireNonNull(text);
    }

    @Override
    public void generateCode(CompileContext context) {
        var type = getType();
        if (type == PrimitiveType.INT) {
            int ival = (int)parseInteger(text);
            context.addCode(pushInteger(ival));
        } else if (type == PrimitiveType.LONG) {
            long lval = parseInteger(text);
            if (lval == 0)
                context.addCode(Instruction.new_lconst_0());
            else if (lval == 1)
                context.addCode(Instruction.new_lconst_1());
            else if (Integer.MIN_VALUE <= lval && lval <= Integer.MAX_VALUE) {
                context.addCode(pushInteger((int)lval));
                context.addCode(Instruction.new_i2l());
            } else
                context.addCode(Instruction.new_ldc2_w(lval));
        } else if (type == PrimitiveType.FLOAT) {
            float fval = (float)parseFloatingPoint(text);
            if (fval == 0)
                context.addCode(Instruction.new_fconst_0());
            else if (fval == 1)
                context.addCode(Instruction.new_fconst_1());
            else if (fval == 2)
                context.addCode(Instruction.new_fconst_2());
            else
                context.addCode(Instruction.new_ldc(fval));
        } else if (type == PrimitiveType.DOUBLE) {
            double dval = parseFloatingPoint(text);
            if (dval == 0)
                context.addCode(Instruction.new_dconst_0());
            else if (dval == 1)
                context.addCode(Instruction.new_dconst_1());
            else
                context.addCode(Instruction.new_ldc2_w(dval));
        } else if (type == PrimitiveType.BOOLEAN) {
            if ("true".equals(text))
                context.addCode(Instruction.new_iconst_1());
            else if ("false".equals(text))
                context.addCode(Instruction.new_iconst_0());
            else
                throw new InternalParserException("Boolean literal with invalid text: " + text);
        } else if (type == PrimitiveType.CHAR) {
            String text = unescape(this.text.substring(1, this.text.length() - 1));
            if (text.length() != 1)
                throw new InternalParserException("Invalid char literal: " + this.text);
            int cval = text.charAt(0);
            context.addCode(pushInteger(cval));
        } else if (type.equals(ExternalClassType.STRING)) {
            String text = unescape(this.text.substring(1, this.text.length() - 1));
            context.addCode(Instruction.new_ldc(text));
        } else if (type == DataType.NULL) {
            context.addCode(Instruction.new_aconst_null());
        } else
            throw new InternalCompilerException("Unknown literal type: " + type);
    }

    private long parseInteger(String s) {
        s = s.replace("_", "").toLowerCase();
        if (s.endsWith("l"))
            s = s.substring(0, s.length() - 1);
        long lval;
        if (s.startsWith("0x"))
            lval = Long.parseLong(s.substring(2), 16);
        else if (s.startsWith("0b"))
            lval = Long.parseLong(s.substring(2), 2);
        else if (s.startsWith("0"))
            lval = Long.parseLong(s, 8);
        else
            lval = Long.parseLong(s);
        return lval;
    }

    private Instruction pushInteger(int val) {
        if (val == -1)
            return Instruction.new_iconst_m1();
        else if (val == 0)
            return Instruction.new_iconst_0();
        else if (val == 1)
            return Instruction.new_iconst_1();
        else if (val == 2)
            return Instruction.new_iconst_2();
        else if (val == 3)
            return Instruction.new_iconst_3();
        else if (val == 4)
            return Instruction.new_iconst_4();
        else if (val == 5)
            return Instruction.new_iconst_5();
        else if (Byte.MIN_VALUE <= val && val <= Byte.MAX_VALUE)
            return Instruction.new_bipush((byte)val);
        else if (Short.MIN_VALUE <= val && val <= Short.MAX_VALUE)
            return Instruction.new_sipush((short)val);
        else
            return Instruction.new_ldc(val);
    }

    private double parseFloatingPoint(String s) {
        s = s.replace("_", "").toLowerCase();
        if (s.endsWith("f"))
            s = s.substring(0, s.length() - 1);
        return Double.parseDouble(s);
    }

    private String unescape(String text) {
        var sb = new StringBuilder(text.length() * 12 / 10);
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == '\\') {
                String seq = text.substring(i + 1, Math.min(i + 6, text.length()));
                if (seq.startsWith("b"))
                    sb.append('\b');
                else if (seq.startsWith("t"))
                    sb.append('\t');
                else if (seq.startsWith("n"))
                    sb.append('\n');
                else if (seq.startsWith("f"))
                    sb.append('\f');
                else if (seq.startsWith("r"))
                    sb.append('\r');
                else if (seq.startsWith("\\"))
                    sb.append('\\');
                else if (seq.startsWith("'"))
                    sb.append('\'');
                else if (seq.startsWith("\""))
                    sb.append('"');
                else {
                    var matcher = OCTAL_ESCAPE.matcher(seq);
                    if (matcher.matches()) {
                        String match = matcher.group(1);
                        sb.append((char)Integer.parseInt(match, 8));
                        i += matcher.group(0).length();
                    } else {
                        matcher = HEXADECIMAL_ESCAPE.matcher(seq);
                        if (matcher.matches()) {
                            String match = matcher.group(1);
                            sb.append((char)Integer.parseInt(match, 16));
                            i += matcher.group(0).length();
                        } else
                            throw new InternalParserException("Bad text literal: " + text);
                    }
                }

                ++i;
            } else
                sb.append(c);
        }

        return sb.toString();
    }
}
