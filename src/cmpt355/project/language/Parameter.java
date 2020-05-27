package cmpt355.project.language;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a parameter. Adds nothing to the base {@link Variable} type.
 */
public class Parameter extends Variable {

    private Parameter(Modifiers modifiers,
                      DataType type,
                      String name) {
        super(modifiers, type, name);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        List<String> words = new ArrayList<>();
        words.add("parameter:");
        getModifiers().forEach(mod -> words.add(mod.toString()));
        words.add(getType().toString());
        words.add(getName());
        return String.join(" ", words);
    }

    public static final class Builder {

        private final Modifiers modifiers = new Modifiers();
        private DataType type;
        private String name;

        private Builder() {}

        public Builder modifier(Modifier m) {
            modifiers.add(m);
            return this;
        }

        public Builder type(DataType type) {
            this.type = type;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Parameter build() {
            return new Parameter(modifiers, type, name);
        }
    }
}
