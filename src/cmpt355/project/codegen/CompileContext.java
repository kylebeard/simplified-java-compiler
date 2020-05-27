package cmpt355.project.codegen;

import cmpt355.project.InternalCompilerException;
import cmpt355.project.jvm.Instruction;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * The main class used when generating JVM assembly code. Each {@code CompileContext} is specific to a single method and
 * contains
 * <ul style="list-style-type: square;">
 *     <li>a list of all the local variables (including parameters) in the method with their mapping to the local
 *          variable array;</li>
 *     <li>the generated code of the method (a list of {@link Instruction}s);</li>
 *     <li>{@link Label}s for branch targets.</li>
 * </ul>
 *
 * The main methods of use are
 * <ul style="list-style-type: square;">
 *     <li>{@link #addCode(Instruction)} &mdash; adds an instruction to the method's code;</li>
 *     <li>{@link #findMethodVariable(String)} &mdash; returns the index of a local variable (or parameter) in the
 *         method's local variable array;</li>
 *     <li>{@link #newLabel(String)} &mdash; creates a new label that can be used as a branch target.</li>
 * </ul>
 *
 * @see Instruction
 */
public class CompileContext {

    private static final Predicate<String> LABEL_VALID = Pattern.compile("^[a-zA-Z_0-9]+$").asMatchPredicate();

    private Map<String, Integer> methodVariableMap = new HashMap<>();
    private List<Instruction> methodCode = new ArrayList<>();
    private List<DynamicLabel> labels = new ArrayList<>();
    private int numLocals;
    private int runningStackSize = 0;
    private int maxStackSize = 0;

    public CompileContext() {
    }

    void defineMethodVariable(String name, int index) {
        methodVariableMap.put(name, index);
    }

    /**
     * Returns the index of a local variable (or parameter) in the local variable array (as would be used by e.g.
     * {@link Instruction#new_istore(int)}.
     * @param name the name of the local variable to look up
     * @return an index (&ge; 0, or &ge; 1 if in a non-static method)
     * @throws InternalCompilerException if {@code name} is not the name of a local variable in this method
     */
    public int findMethodVariable(String name) {
        Integer index = methodVariableMap.getOrDefault(name, null);
        if (index == null)
            throw new InternalCompilerException("Cannot find method variable with name " + name);
        return methodVariableMap.get(name);
    }

    private void updateStackSize(int delta) {
        runningStackSize += delta;
        maxStackSize = Math.max(maxStackSize, runningStackSize);
    }

    /**
     * Adds an instruction to the code of this method. This method must be called in the order that the instructions
     * should appear in the generated assembly file.
     * @param instr the instruction to add
     * @throws NullPointerException if {@code instr == null}
     */
    public void addCode(Instruction instr) {
        methodCode.add(Objects.requireNonNull(instr));
        updateStackSize(instr.computeStackDelta());
    }

    public void adjustStackSize(int delta) {
        updateStackSize(delta);
    }

    public int getMaxStackSize() {
        return Math.max(maxStackSize, 0);
    }

    public List<Instruction> getCode() {
        return Collections.unmodifiableList(methodCode);
    }

    /**
     * Creates a new
     * @return
     */
    public Label newLabel() {
        return newLabel(null);
    }

    /**
     * Creates a new label with the given tag, which will be incorporated into the generated label in the assembly code
     * for easier debugging. The tag must consist of letters, digits, and underscores.
     *
     * <strong>A tag must be {@link Label#mark() mark()}ed at the point of the code that it should branch to.</strong> See
     * {@link Label} for more information.
     *
     * @param tag the tag, or {@code null} if no tag is desired.
     * @return a {@link Label} that can be used as the target of branch instructions.
     * @throws IllegalArgumentException if {@code tag} contains any characters other than (ASCII) letters, digits, or
     *      underscores.
     */
    public Label newLabel(String tag) {
        if (tag != null && !LABEL_VALID.test(tag))
            throw new IllegalArgumentException("Invalid tag '" + tag + "': tag must consist of letters, digits, and underscores");
        var newLabel = new DynamicLabel(this, tag);
        labels.add(newLabel);
        return newLabel;
    }

    private void mark(DynamicLabel label) {
        label.setCodeIndex(methodCode.size());
    }

    void applyLabels() {
        labels.sort(Comparator.comparing(DynamicLabel::getCodeIndex));
        int lastCodeIndex = -1;
        DynamicLabel lastLabel = null;

        // If we have a dangling label at the end with no instruction for it to label, add a "nop" for it
        if (!labels.isEmpty()) {
            var finalLabel = labels.get(labels.size() - 1);
            if (finalLabel.getCodeIndex() == methodCode.size())
                addCode(Instruction.new_nop());
        }

        for (var label : labels) {
            if (label.getCodeIndex() == lastCodeIndex) {
                lastLabel.appendTag(label.getTag());
                label.proxyFor(lastLabel);
            } else {
                int codeIndex = label.getCodeIndex();
                methodCode.get(codeIndex).setLabel(label);

                lastCodeIndex = codeIndex;
                lastLabel = label;
            }
        }
    }

    void setNumLocals(int numLocals) {
        this.numLocals = numLocals;
    }

    int getNumLocals() {
        return numLocals;
    }

    private class DynamicLabel extends Label {

        private final CompileContext compileContext;
        private int codeIndex = -1;
        private String tag;
        private DynamicLabel proxy;

        private DynamicLabel(CompileContext compileContext, String tag) {
            super("(auto)");
            this.compileContext = Objects.requireNonNull(compileContext);
            this.tag = tag;
        }

        private int getCodeIndex() {
            if (codeIndex < 0)
                throw new IllegalStateException("Label with tag " + tag + " has not been mark()ed");
            return codeIndex;
        }

        public String getTag() {
            return tag;
        }

        private void appendTag(String s) {
            if (s != null)
                tag += "_" + s;
            else
                tag = s;
        }

        @Override
        public String getLabel() {
            if (proxy != null)
                return proxy.getLabel();
            if (tag == null)
                return String.format("L%d", codeIndex);
            else
                return String.format("L%d_%s", codeIndex, tag);
        }

        @Override
        public void mark() {
            compileContext.mark(this);
        }

        private void setCodeIndex(int codeIndex) {
            this.codeIndex = codeIndex;
        }

        private void proxyFor(DynamicLabel other) {
            this.proxy = other;
        }
    }
}
