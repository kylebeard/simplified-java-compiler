package cmpt355.project.jvm;

import java.util.Objects;

public class StringOperand extends Operand {

    private final String value;

    public StringOperand(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringOperand that = (StringOperand) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", escape(value));
    }

    private String escape(String s) {
        var sb = new StringBuilder(s.length() * 12 / 10);
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c > 127)
                sb.append(String.format("\\u%04x", (int)c));
            else
                sb.append(switch (c) {
                    case '\0' -> "\\0";
                    case '\b' -> "\\b";
                    case '\t' -> "\\t";
                    case '\n' -> "\\n";
                    case '\f' -> "\\f";
                    case '\r' -> "\\r";
                    case '\"' -> "\\\"";
                    case '\\' -> "\\\\";
                    default -> c;
                });
        }

        return sb.toString();
    }
}
