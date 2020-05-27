package cmpt355.project.language;

/**
 * A class representing a local variable. Adds nothing to the base {@link Variable} type.
 */
public class LocalVariable extends Variable {

    public LocalVariable(DataType type, Modifiers modifiers, String name) {
        super(modifiers, type, name);
    }
}
