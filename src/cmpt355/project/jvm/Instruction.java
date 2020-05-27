package cmpt355.project.jvm;

import cmpt355.project.InternalCompilerException;
import cmpt355.project.codegen.Label;
import cmpt355.project.language.*;

import java.util.*;

/**
 * A class representing a single JVM instruction. An instruction consists of
 *
 * <ul style="list-style-type: square;">
 *     <li>an {@link Opcode} ({@code aload_0} or whatever);</li>
 *     <li>zero or more {@link Operand}s appropriate for the opcode;</li>
 *     <li>an optional {@link Label} if this instruction is the target of a branch.</li>
 * </ul>
 *
 * Although there are public constructors, the best way to create an instruction is usually by using one of the static
 * factory methods ({@code Instruction.new_[opcode](...)}) as these have parameters as appropriate for the opcode.
 */
public class Instruction {

    private Label label;
    private final Opcode opcode;
    private final List<Operand> operands;

    /**
     * Create a new instruction from an optional label, an opcode, and a list of operands.
     */
    public Instruction(Label label, Opcode opcode, List<Operand> operands) {
        this.label = label;
        this.opcode = Objects.requireNonNull(opcode);
        this.operands = new ArrayList<>(operands);
    }

    /**
     * Creates a new instruction from an opcode and a list of operands.
     */
    public Instruction(Opcode opcode, List<Operand> operands) {
        this(null, opcode, operands);
    }

    /**
     * Creates a new instruction from an opcode and zero or more operands.
     */
    public Instruction(Opcode opcode, Operand... operands) {
        this(opcode, Arrays.asList(operands));
    }

    /**
     * Creates a new instruction from an optional label, an opcode, and zero or more operands.
     * @param label
     * @param opcode
     * @param operands
     */
    public Instruction(Label label, Opcode opcode, Operand... operands) {
        this(label, opcode, Arrays.asList(operands));
    }

    /**
     * Returns an {@link Optional} of the label of this instruction.
     */
    public Optional<Label> getLabel() {
        return Optional.ofNullable(label);
    }

    /**
     * Sets the label for this instruction. (Normally this is done by {@link cmpt355.project.codegen.CodeGenerator}
     * &mdash; implementors of {@code generateCode()} should typically be using {@link Label#mark()} instead.)
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * Returns the opcode of this instruction.
     * @return
     */
    public Opcode getOpcode() {
        return opcode;
    }

    /**
     * Returns the operands of this instruction.
     * @return
     */
    public List<Operand> getOperands() {
        return Collections.unmodifiableList(operands);
    }

    /**
     * Calculates the stack delta of this instruction. For most instructions this is a fixed property of the opcode
     * (e.g. {@code aload_0} always increases the stack size by 1) and can be obtained from
     * {@link Opcode#getStackDelta()}, but for some (for example {@link Opcode#getfield}) it varies based on the
     * operand. This method calculates if necessary and then returns the stack delta for any kind of instruction.
     * @return
     */
    public int computeStackDelta() {
        // For most instructions the stack size delta is fixed (e.g. a pop instruction always reduces stack size by 1).
        // For some it depends on the instruction operands (e.g. a getfield instruction can push 1 or 2 stack slots
        // depending on the data type of the field).
        int opcodeDelta = opcode.getStackDelta();
        if (opcodeDelta != Opcode.SPECIAL_HANDLING)
            // No special handling needed
            return opcodeDelta;
        else return switch (opcode) {
            case getfield ->
                    computeFieldSize(((FieldOperand)operands.get(0)).getField()) - 1;
            case getstatic ->
                    computeFieldSize(((FieldOperand)operands.get(0)).getField());
            case invokedynamic ->
                    throw new UnsupportedOperationException("invokedynamic unsupported");
            case invokeinterface, invokespecial, invokevirtual ->
                    -1 + computeMethodDelta(((MethodOperand)operands.get(0)).getMethod());
            case invokestatic ->
                    computeMethodDelta(((MethodOperand)operands.get(0)).getMethod());
            case multianewarray ->
                    ((ArrayType)((TypeOperand)operands.get(0)).getType()).getDimension();
            case putfield ->
                    -computeFieldSize(((FieldOperand)operands.get(0)).getField()) - 1;
            case putstatic ->
                    -computeFieldSize(((FieldOperand)operands.get(0)).getField());
            default ->
                    throw new UnsupportedOperationException("computeStackDelta() unsupported for opcode " + opcode);
        };
    }

    private int dataTypeSize(DataType type) {
        return (type == PrimitiveType.LONG || type == PrimitiveType.DOUBLE) ? 2
                : (type == DataType.VOID) ? 0
                : 1;
    }

    private int computeFieldSize(Field f) {
        var type = f.getType();
        return dataTypeSize(f.getType());
    }

    private int computeMethodDelta(Method method) {
        int delta = dataTypeSize(method.getReturnType());
        for (var paramType : method.getParameterTypes())
            delta -= dataTypeSize(paramType);
        return delta;
    }

    private int computeLdcOperandSize(Operand operand) {
        return (operand instanceof LongOperand || operand instanceof DoubleOperand) ? 2 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(label, that.label) &&
                opcode == that.opcode &&
                operands.equals(that.operands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, opcode, operands);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(50);
        getLabel().ifPresent(label -> sb.append(label.getLabel()).append(":\t"));
        sb.append(opcode.getMnemonic());
        operands.forEach(op -> sb.append(' ').append(op));
        return sb.toString();
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aaload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aaload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aaload">JVM Specification entry for this instruction</a>. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.1">JVM Specification entry for this instruction</a>. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.1">JVM Specification entry for this instruction</a>. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aaload() {
        return new Instruction(Opcode.aaload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aastore">JVM Specification entry for this instruction</a>. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aastore() {
        return new Instruction(Opcode.aastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aconst_null}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aconst_null, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aconst_null">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aconst_null() {
        return new Instruction(Opcode.aconst_null);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aload(int op) {
        return new Instruction(Opcode.aload, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aload_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aload_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aload_0() {
        return new Instruction(Opcode.aload_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aload_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aload_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aload_1() {
        return new Instruction(Opcode.aload_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aload_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aload_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aload_2() {
        return new Instruction(Opcode.aload_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#aload_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.aload_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.aload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_aload_3() {
        return new Instruction(Opcode.aload_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#anewarray}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.anewarray, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.anewarray">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_anewarray(DataType op) {
        return new Instruction(Opcode.anewarray, new TypeOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#areturn}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.areturn, ...)</code>)
     * but enforces operand types.
     */
    public static Instruction new_areturn() {
        return new Instruction(Opcode.areturn);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#arraylength}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.arraylength, ...)</code>)
     * but enforces operand types.
     */
    public static Instruction new_arraylength() {
        return new Instruction(Opcode.arraylength);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#astore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.astore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.astore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_astore(int op) {
        return new Instruction(Opcode.astore, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#astore_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.astore_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.astore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_astore_0() {
        return new Instruction(Opcode.astore_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#astore_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.astore_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.astore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_astore_1() {
        return new Instruction(Opcode.astore_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#astore_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.astore_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.astore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_astore_2() {
        return new Instruction(Opcode.astore_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#astore_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.astore_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.astore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_astore_3() {
        return new Instruction(Opcode.astore_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#athrow}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.athrow, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.athrow">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_athrow() {
        return new Instruction(Opcode.athrow);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#baload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.baload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.baload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_baload() {
        return new Instruction(Opcode.baload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#bastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.bastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.bastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_bastore() {
        return new Instruction(Opcode.bastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#bipush}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.bipush, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.bipush">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_bipush(byte op) {
        return new Instruction(Opcode.bipush, new ByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#caload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.caload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.caload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_caload() {
        return new Instruction(Opcode.caload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#castore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.castore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.castore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_castore() {
        return new Instruction(Opcode.castore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#checkcast}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.checkcast, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.checkcast">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_checkcast(DataType op) {
        return new Instruction(Opcode.checkcast, new TypeOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#d2f}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.d2f, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.d2f">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_d2f() {
        return new Instruction(Opcode.d2f);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#d2i}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.d2i, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.d2i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_d2i() {
        return new Instruction(Opcode.d2i);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#d2l}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.d2l, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.d2l">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_d2l() {
        return new Instruction(Opcode.d2l);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dadd}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dadd, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dadd">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dadd() {
        return new Instruction(Opcode.dadd);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#daload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.daload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.daload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_daload() {
        return new Instruction(Opcode.daload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dastore() {
        return new Instruction(Opcode.dastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dcmpg}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dcmpg, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dcmp_op">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dcmpg() {
        return new Instruction(Opcode.dcmpg);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dcmpl}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dcmpl, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dcmp_op">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dcmpl() {
        return new Instruction(Opcode.dcmpl);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dconst_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dconst_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dconst_d">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dconst_0() {
        return new Instruction(Opcode.dconst_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dconst_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dconst_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dconst_d">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dconst_1() {
        return new Instruction(Opcode.dconst_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ddiv}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ddiv, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ddiv">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ddiv() {
        return new Instruction(Opcode.ddiv);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dload(int op) {
        return new Instruction(Opcode.dload, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dload_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dload_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dload_0() {
        return new Instruction(Opcode.dload_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dload_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dload_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dload_1() {
        return new Instruction(Opcode.dload_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dload_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dload_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dload_2() {
        return new Instruction(Opcode.dload_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dload_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dload_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dload_3() {
        return new Instruction(Opcode.dload_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dmul}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dmul, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dmul">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dmul() {
        return new Instruction(Opcode.dmul);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dneg}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dneg, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dneg">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dneg() {
        return new Instruction(Opcode.dneg);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#drem}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.drem, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.drem">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_drem() {
        return new Instruction(Opcode.drem);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dreturn}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dreturn, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dreturn">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dreturn() {
        return new Instruction(Opcode.dreturn);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dstore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dstore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dstore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dstore(int op) {
        return new Instruction(Opcode.dstore, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dstore_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dstore_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dstore_0() {
        return new Instruction(Opcode.dstore_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dstore_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dstore_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dstore_1() {
        return new Instruction(Opcode.dstore_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dstore_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dstore_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dstore_2() {
        return new Instruction(Opcode.dstore_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dstore_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dstore_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dstore_3() {
        return new Instruction(Opcode.dstore_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dsub}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dsub, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dsub">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dsub() {
        return new Instruction(Opcode.dsub);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dup}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dup, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dup">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dup() {
        return new Instruction(Opcode.dup);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dup_x1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dup_x1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dup_x1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dup_x1() {
        return new Instruction(Opcode.dup_x1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dup_x2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dup_x2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dup_x2">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dup_x2() {
        return new Instruction(Opcode.dup_x2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dup2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dup2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dup2">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dup2() {
        return new Instruction(Opcode.dup2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dup2_x1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dup2_x1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dup2_x1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dup2_x1() {
        return new Instruction(Opcode.dup2_x1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#dup2_x2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.dup2_x2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.dup2_x2">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_dup2_x2() {
        return new Instruction(Opcode.dup2_x2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#f2d}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.f2d, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.f2d">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_f2d() {
        return new Instruction(Opcode.f2d);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#f2i}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.f2i, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.f2i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_f2i() {
        return new Instruction(Opcode.f2i);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#f2l}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.f2l, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.f2l">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_f2l() {
        return new Instruction(Opcode.f2l);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fadd}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fadd, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fadd">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fadd() {
        return new Instruction(Opcode.fadd);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#faload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.faload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.faload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_faload() {
        return new Instruction(Opcode.faload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fastore() {
        return new Instruction(Opcode.fastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fcmpg}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fcmpg, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fcmp_op">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fcmpg() {
        return new Instruction(Opcode.fcmpg);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fcmpl}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fcmpl, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fcmp_op">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fcmpl() {
        return new Instruction(Opcode.fcmpl);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fconst_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fconst_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fconst_f">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fconst_0() {
        return new Instruction(Opcode.fconst_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fconst_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fconst_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fconst_f">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fconst_1() {
        return new Instruction(Opcode.fconst_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fconst_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fconst_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fconst_f">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fconst_2() {
        return new Instruction(Opcode.fconst_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fdiv}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fdiv, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fdiv">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fdiv() {
        return new Instruction(Opcode.fdiv);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fload(int op) {
        return new Instruction(Opcode.fload, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fload_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fload_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fload_0() {
        return new Instruction(Opcode.fload_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fload_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fload_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fload_1() {
        return new Instruction(Opcode.fload_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fload_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fload_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fload_2() {
        return new Instruction(Opcode.fload_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fload_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fload_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fload_3() {
        return new Instruction(Opcode.fload_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fmul}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fmul, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fmul">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fmul() {
        return new Instruction(Opcode.fmul);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fneg}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fneg, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fneg">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fneg() {
        return new Instruction(Opcode.fneg);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#frem}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.frem, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.frem">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_frem() {
        return new Instruction(Opcode.frem);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#freturn}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.freturn, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.freturn">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_freturn() {
        return new Instruction(Opcode.freturn);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fstore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fstore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fstore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fstore(int op) {
        return new Instruction(Opcode.fstore, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fstore_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fstore_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fstore_0() {
        return new Instruction(Opcode.fstore_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fstore_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fstore_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fstore_1() {
        return new Instruction(Opcode.fstore_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fstore_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fstore_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fstore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fstore_2() {
        return new Instruction(Opcode.fstore_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fstore_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fstore_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fstore_3">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fstore_3() {
        return new Instruction(Opcode.fstore_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#fsub}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.fsub, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.fsub">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_fsub() {
        return new Instruction(Opcode.fsub);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#getfield}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.getfield, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.getfield">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_getfield(Field op) {
        if (op.getModifiers().contains(Modifier.STATIC))
            throw new InternalCompilerException("getfield of static field " + op);
        return new Instruction(Opcode.getfield, new FieldOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#getstatic}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.getstatic, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.getstatic">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_getstatic(Field op) {
        if (!op.getModifiers().contains(Modifier.STATIC))
            throw new InternalCompilerException("getstatic of non-static field " + op);
        return new Instruction(Opcode.getstatic, new FieldOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#goto_}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.goto_, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.goto">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_goto(InstructionAddressOperand op) {
        return new Instruction(Opcode.goto_, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#i2b}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.i2b, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.i2b">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_i2b() {
        return new Instruction(Opcode.i2b);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#i2c}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.i2c, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.i2c">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_i2c() {
        return new Instruction(Opcode.i2c);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#i2d}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.i2d, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.i2d">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_i2d() {
        return new Instruction(Opcode.i2d);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#i2f}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.i2f, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.i2f">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_i2f() {
        return new Instruction(Opcode.i2f);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#i2l}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.i2l, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.i2l">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_i2l() {
        return new Instruction(Opcode.i2l);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#i2s}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.i2s, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.i2s">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_i2s() {
        return new Instruction(Opcode.i2s);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iadd}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iadd, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iadd">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iadd() {
        return new Instruction(Opcode.iadd);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iaload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iaload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iaload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iaload() {
        return new Instruction(Opcode.iaload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iand}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iand, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iand">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iand() {
        return new Instruction(Opcode.iand);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iastore() {
        return new Instruction(Opcode.iastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_m1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_m1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_m1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_m1() {
        return new Instruction(Opcode.iconst_m1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_0() {
        return new Instruction(Opcode.iconst_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_1() {
        return new Instruction(Opcode.iconst_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_2() {
        return new Instruction(Opcode.iconst_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_3() {
        return new Instruction(Opcode.iconst_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_4}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_4, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_4() {
        return new Instruction(Opcode.iconst_4);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iconst_5}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iconst_5, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iconst_i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iconst_5() {
        return new Instruction(Opcode.iconst_5);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#idiv}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.idiv, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.idiv">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_idiv() {
        return new Instruction(Opcode.idiv);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_acmpeq}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_acmpeq, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_acmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_acmpeq(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_acmpeq, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_acmpne}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_acmpne, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_acmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_acmpne(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_acmpne, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_icmpeq}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_icmpeq, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_icmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_icmpeq(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_icmpeq, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_icmpne}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_icmpne, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_icmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_icmpne(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_icmpne, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_icmplt}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_icmplt, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_icmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_icmplt(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_icmplt, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_icmpge}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_icmpge, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_icmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_icmpge(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_icmpge, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_icmpgt}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_icmpgt, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_icmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_icmpgt(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_icmpgt, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#if_icmple}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.if_icmple, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_icmp_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_if_icmple(InstructionAddressOperand op) {
        return new Instruction(Opcode.if_icmple, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifeq}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifeq, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifeq(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifeq, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifne}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifne, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifne(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifne, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iflt}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iflt, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iflt(InstructionAddressOperand op) {
        return new Instruction(Opcode.iflt, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifge}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifge, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifge(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifge, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifgt}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifgt, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifgt(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifgt, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifle}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifle, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.if_cond">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifle(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifle, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifnonnull}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifnonnull, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ifnonnull">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifnonnull(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifnonnull, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ifnull}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ifnull, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ifnull">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ifnull(InstructionAddressOperand op) {
        return new Instruction(Opcode.ifnull, op);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iinc}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iinc, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iinc">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iinc() {
        return new Instruction(Opcode.iinc);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iload(int op) {
        return new Instruction(Opcode.iload, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iload_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iload_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iload_0() {
        return new Instruction(Opcode.iload_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iload_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iload_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iload_1() {
        return new Instruction(Opcode.iload_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iload_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iload_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iload_2() {
        return new Instruction(Opcode.iload_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iload_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iload_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iload_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iload_3() {
        return new Instruction(Opcode.iload_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#imul}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.imul, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.imul">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_imul() {
        return new Instruction(Opcode.imul);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ineg}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ineg, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ineg">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ineg() {
        return new Instruction(Opcode.ineg);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#instanceof_}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.instanceof_, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.instanceof">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_instanceof(DataType op) {
        return new Instruction(Opcode.instanceof_, new TypeOperand(op));
    }

    /*
    /**
     * Returns a new Instruction with opcode {@link Opcode#invokedynamic}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.invokedynamic, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.invokedynamic">JVM Specification entry for this instruction</a>.
     */
    /*
    public static Instruction new_invokedynamic() {
        return new Instruction(Opcode.invokedynamic);
    }
    */

    /**
     * Returns a new Instruction with opcode {@link Opcode#invokeinterface}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.invokeinterface, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.invokeinterface">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_invokeinterface(Method op) {
        return new Instruction(Opcode.invokeinterface, new MethodOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#invokespecial}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.invokespecial, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.invokespecial">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_invokespecial(Method op) {
        return new Instruction(Opcode.invokespecial, new MethodOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#invokestatic}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.invokestatic, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.invokestatic">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_invokestatic(Method op) {
        return new Instruction(Opcode.invokestatic, new MethodOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#invokevirtual}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.invokevirtual, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.invokevirtual">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_invokevirtual(Method op) {
        return new Instruction(Opcode.invokevirtual, new MethodOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ior}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ior, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ior">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ior() {
        return new Instruction(Opcode.ior);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#irem}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.irem, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.irem">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_irem() {
        return new Instruction(Opcode.irem);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ireturn}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ireturn, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ireturn">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ireturn() {
        return new Instruction(Opcode.ireturn);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ishl}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ishl, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ishl">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ishl() {
        return new Instruction(Opcode.ishl);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ishr}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ishr, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ishr">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ishr() {
        return new Instruction(Opcode.ishr);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#istore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.istore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.istore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_istore(int op) {
        return new Instruction(Opcode.istore, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#istore_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.istore_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.istore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_istore_0() {
        return new Instruction(Opcode.istore_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#istore_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.istore_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.istore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_istore_1() {
        return new Instruction(Opcode.istore_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#istore_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.istore_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.istore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_istore_2() {
        return new Instruction(Opcode.istore_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#istore_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.istore_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.istore_n">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_istore_3() {
        return new Instruction(Opcode.istore_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#isub}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.isub, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.isub">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_isub() {
        return new Instruction(Opcode.isub);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#iushr}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.iushr, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.iushr">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_iushr() {
        return new Instruction(Opcode.iushr);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ixor}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ixor, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ixor">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ixor() {
        return new Instruction(Opcode.ixor);
    }

    /*
    /**
     * Returns a new Instruction with opcode {@link Opcode#jsr}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.jsr, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.jsr">JVM Specification entry for this instruction</a>.
     */
    /*
    public static Instruction new_jsr(InstructionAddressOperand op) {
        return new Instruction(Opcode.jsr, op);
    }
    */

    /**
     * Returns a new Instruction with opcode {@link Opcode#l2d}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.l2d, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.l2d">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_l2d() {
        return new Instruction(Opcode.l2d);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#l2f}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.l2f, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.l2f">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_l2f() {
        return new Instruction(Opcode.l2f);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#l2i}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.l2i, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.l2i">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_l2i() {
        return new Instruction(Opcode.l2i);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ladd}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ladd, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ladd">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ladd() {
        return new Instruction(Opcode.ladd);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#laload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.laload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.laload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_laload() {
        return new Instruction(Opcode.laload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#land}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.land, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.land">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_land() {
        return new Instruction(Opcode.land);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lastore() {
        return new Instruction(Opcode.lastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lcmp}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lcmp, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lcmp">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lcmp() {
        return new Instruction(Opcode.lcmp);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lconst_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lconst_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lconst_0">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lconst_0() {
        return new Instruction(Opcode.lconst_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lconst_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lconst_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lconst_1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lconst_1() {
        return new Instruction(Opcode.lconst_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldc}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldc, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldc">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldc(int op) {
        return new Instruction(Opcode.ldc, new IntOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldc}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldc, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldc">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldc(float op) {
        return new Instruction(Opcode.ldc, new FloatOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldc}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldc, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldc">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldc(String op) {
        return new Instruction(Opcode.ldc, new StringOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldc}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldc, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldc">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldc(ClassType op) {
        return new Instruction(Opcode.ldc, new ClassLiteralOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldc2_w}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldc2_w, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldc2_w">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldc2_w(long op) {
        return new Instruction(Opcode.ldc2_w, new LongOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldc2_w}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldc2_w, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldc2_w">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldc2_w(double op) {
        return new Instruction(Opcode.ldc2_w, new DoubleOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#ldiv}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ldiv, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ldiv">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_ldiv() {
        return new Instruction(Opcode.ldiv);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lload(int op) {
        return new Instruction(Opcode.lload, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lload_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lload_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lload_0">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lload_0() {
        return new Instruction(Opcode.lload_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lload_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lload_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lload_1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lload_1() {
        return new Instruction(Opcode.lload_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lload_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lload_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lload_2">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lload_2() {
        return new Instruction(Opcode.lload_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lload_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lload_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lload_3">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lload_3() {
        return new Instruction(Opcode.lload_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lmul}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lmul, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lmul">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lmul() {
        return new Instruction(Opcode.lmul);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lneg}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lneg, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lneg">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lneg() {
        return new Instruction(Opcode.lneg);
    }

    /*
    /**
     * Returns a new Instruction with opcode {@link Opcode#lookupswitch}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lookupswitch, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lookupswitch">JVM Specification entry for this instruction</a>.
     */
    /*
    public static Instruction new_lookupswitch() {
        return new Instruction(Opcode.lookupswitch);
    }
    */

    /**
     * Returns a new Instruction with opcode {@link Opcode#lor}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lor, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lor">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lor() {
        return new Instruction(Opcode.lor);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lrem}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lrem, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lrem">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lrem() {
        return new Instruction(Opcode.lrem);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lreturn}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lreturn, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lreturn">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lreturn() {
        return new Instruction(Opcode.lreturn);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lshl}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lshl, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lshl">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lshl() {
        return new Instruction(Opcode.lshl);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lshr}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lshr, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lshr">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lshr() {
        return new Instruction(Opcode.lshr);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lstore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lstore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lstore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lstore(int op) {
        return new Instruction(Opcode.lstore, new UnsignedByteOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lstore_0}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lstore_0, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lstore_0">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lstore_0() {
        return new Instruction(Opcode.lstore_0);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lstore_1}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lstore_1, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lstore_1">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lstore_1() {
        return new Instruction(Opcode.lstore_1);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lstore_2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lstore_2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lstore_2">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lstore_2() {
        return new Instruction(Opcode.lstore_2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lstore_3}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lstore_3, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lstore_3">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lstore_3() {
        return new Instruction(Opcode.lstore_3);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lsub}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lsub, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lsub">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lsub() {
        return new Instruction(Opcode.lsub);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lushr}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lushr, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lushr">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lushr() {
        return new Instruction(Opcode.lushr);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#lxor}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.lxor, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.lxor">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_lxor() {
        return new Instruction(Opcode.lxor);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#monitorenter}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.monitorenter, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.monitorenter">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_monitorenter() {
        return new Instruction(Opcode.monitorenter);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#monitorexit}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.monitorexit, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.monitorexit">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_monitorexit() {
        return new Instruction(Opcode.monitorexit);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#multianewarray}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.multianewarray, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.multianewarray">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_multianewarray(ArrayType op) {
        return new Instruction(Opcode.multianewarray, new TypeOperand(op.getBaseType()), new UnsignedByteOperand(op.getDimension()));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#new_}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.new_, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.new">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_new(ClassType op) {
        return new Instruction(Opcode.new_, new TypeOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#newarray}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.newarray, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.newarray">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_newarray(PrimitiveType op) {
        return new Instruction(Opcode.newarray, new ArrayTypeOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#nop}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.nop, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.nop">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_nop() {
        return new Instruction(Opcode.nop);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#pop}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.pop, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.pop">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_pop() {
        return new Instruction(Opcode.pop);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#pop2}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.pop2, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.pop2">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_pop2() {
        return new Instruction(Opcode.pop2);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#putfield}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.putfield, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.putfield">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_putfield(Field field) {
        if (field.getModifiers().contains(Modifier.STATIC))
            throw new InternalCompilerException("putfield on static field " + field);
        return new Instruction(Opcode.putfield, new FieldOperand(field));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#putstatic}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.putstatic, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.putstatic">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_putstatic(Field field) {
        if (!field.getModifiers().contains(Modifier.STATIC))
            throw new InternalCompilerException("putstatic on non-static field " + field);
        return new Instruction(Opcode.putstatic, new FieldOperand(field));
    }

    /*
    /**
     * Returns a new Instruction with opcode {@link Opcode#ret}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.ret, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.ret">JVM Specification entry for this instruction</a>.
     */
    /*
    public static Instruction new_ret() {
        return new Instruction(Opcode.ret);
    }
    */

    /**
     * Returns a new Instruction with opcode {@link Opcode#return_}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.return_, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.return">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_return() {
        return new Instruction(Opcode.return_);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#saload}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.saload, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.saload">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_saload() {
        return new Instruction(Opcode.saload);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#sastore}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.sastore, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.sastore">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_sastore() {
        return new Instruction(Opcode.sastore);
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#sipush}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.sipush, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.sipush">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_sipush(short op) {
        return new Instruction(Opcode.sipush, new ShortOperand(op));
    }

    /**
     * Returns a new Instruction with opcode {@link Opcode#swap}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.swap, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.swap">JVM Specification entry for this instruction</a>.
     */
    public static Instruction new_swap() {
        return new Instruction(Opcode.swap);
    }

    /*
    /**
     * Returns a new Instruction with opcode {@link Opcode#tableswitch}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.tableswitch, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.tableswitch">JVM Specification entry for this instruction</a>.
     */
    /*
    public static Instruction new_tableswitch() {
        return new Instruction(Opcode.tableswitch);
    }
    */

    /*
    /**
     * Returns a new Instruction with opcode {@link Opcode#wide}.
     * This method does the same as using the constructor (<code>new Instruction(Opcode.wide, ...)</code>)
     * but enforces operand types. See the <a href="https://docs.oracle.com/javase/specs/jvms/se13/html/jvms-6.html#jvms-6.5.wide">JVM Specification entry for this instruction</a>.
     */
    /*
    public static Instruction new_wide() {
        return new Instruction(Opcode.wide);
    }
    */
}
