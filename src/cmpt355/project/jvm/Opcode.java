package cmpt355.project.jvm;

import java.util.Objects;

/**
 * An enum of the opcodes of JVM instructions.
 */
public enum Opcode {

    // With each opcode we pass the change in stack size of that opcode. (E.g. if the number is negative it makes the
    // stack smaller and if it is positive it makes the stack larger.) Some are variable depending on the instruction
    // operand(s), such as getfield which can push 1 or 2 values depending on what data type the field is; these specify
    // Opcode.SPECIAL_HANDLING and are computed in Instruction.computeStackDelta().
    aaload(-1),
    aastore(-3),
    aconst_null(1),
    aload(1),
    aload_0(1),
    aload_1(1),
    aload_2(1),
    aload_3(1),
    anewarray(0),
    areturn(-1),
    arraylength(0),
    astore(-1),
    astore_0(-1),
    astore_1(-1),
    astore_2(-1),
    astore_3(-1),
    athrow(0),
    baload(-1),
    bastore(-3),
    bipush(1),
    caload(-1),
    castore(-3),
    checkcast(0),
    d2f(-1),
    d2i(-1),
    d2l(0),
    dadd(-2),
    daload(0),
    dastore(-4),
    dcmpg(-3),
    dcmpl(-3),
    dconst_0(2),
    dconst_1(2),
    ddiv(-2),
    dload(2),
    dload_0(2),
    dload_1(2),
    dload_2(2),
    dload_3(2),
    dmul(-2),
    dneg(0),
    drem(-2),
    dreturn(-2),
    dstore(-2),
    dstore_0(-2),
    dstore_1(-2),
    dstore_2(-2),
    dstore_3(-2),
    dsub(-2),
    dup(1),
    dup_x1(1),
    dup_x2(1),
    dup2(2),
    dup2_x1(2),
    dup2_x2(2),
    f2d(1),
    f2i(0),
    f2l(1),
    fadd(-1),
    faload(-1),
    fastore(-3),
    fcmpg(-1),
    fcmpl(-1),
    fconst_0(1),
    fconst_1(1),
    fconst_2(1),
    fdiv(-1),
    fload(1),
    fload_0(1),
    fload_1(1),
    fload_2(1),
    fload_3(1),
    fmul(-1),
    fneg(0),
    frem(-1),
    freturn(-1),
    fstore(-1),
    fstore_0(-1),
    fstore_1(-1),
    fstore_2(-1),
    fstore_3(-1),
    fsub(-1),
    getfield(Opcode.SPECIAL_HANDLING),
    getstatic(Opcode.SPECIAL_HANDLING),

    /**
     * Should be named "goto", but this is a Java keyword.
     */
    goto_(0),
    goto_w(0),
    i2b(0),
    i2c(0),
    i2d(1),
    i2f(0),
    i2l(1),
    i2s(0),
    iadd(-1),
    iaload(-1),
    iand(-1),
    iastore(-3),
    iconst_m1(1),
    iconst_0(1),
    iconst_1(1),
    iconst_2(1),
    iconst_3(1),
    iconst_4(1),
    iconst_5(1),
    idiv(-1),
    if_acmpeq(-2),
    if_acmpne(-2),
    if_icmpeq(-2),
    if_icmpne(-2),
    if_icmplt(-2),
    if_icmpge(-2),
    if_icmpgt(-2),
    if_icmple(-2),
    ifeq(-1),
    ifne(-1),
    iflt(-1),
    ifge(-1),
    ifgt(-1),
    ifle(-1),
    ifnonnull(-1),
    ifnull(-1),
    iinc(0),
    iload(1),
    iload_0(1),
    iload_1(1),
    iload_2(1),
    iload_3(1),
    imul(-1),
    ineg(0),
    /**
     * Should be called "instanceof", but this is a Java keyword.
     */
    instanceof_(0),
    invokedynamic(Opcode.SPECIAL_HANDLING),
    invokeinterface(Opcode.SPECIAL_HANDLING),
    invokespecial(Opcode.SPECIAL_HANDLING),
    invokestatic(Opcode.SPECIAL_HANDLING),
    invokevirtual(Opcode.SPECIAL_HANDLING),
    ior(-1),
    irem(-1),
    ireturn(-1),
    ishl(-1),
    ishr(-1),
    istore(-1),
    istore_0(-1),
    istore_1(-1),
    istore_2(-1),
    istore_3(-1),
    isub(-1),
    iushr(-1),
    ixor(-1),
    jsr(1),
    jsr_w(1),
    l2d(0),
    l2f(-1),
    l2i(-1),
    ladd(-1),
    laload(0),
    land(-2),
    lastore(-4),
    lcmp(-3),
    lconst_0(2),
    lconst_1(2),
    ldc(1),
    ldc_w(1),
    ldc2_w(2),
    ldiv(-2),
    lload(2),
    lload_0(2),
    lload_1(2),
    lload_2(2),
    lload_3(2),
    lmul(-2),
    lneg(0),
    lookupswitch(-1),
    lor(-2),
    lrem(-2),
    lreturn(-2),
    lshl(-1),
    lshr(-1),
    lstore(-2),
    lstore_0(-2),
    lstore_1(-2),
    lstore_2(-2),
    lstore_3(-2),
    lsub(-2),
    lushr(-1),
    lxor(-2),
    monitorenter(-1),
    monitorexit(-1),
    multianewarray(Opcode.SPECIAL_HANDLING),
    /**
     * Should be called "new", but this is a Java keyword.
     */
    new_(1),
    newarray(0),
    nop(0),
    pop(-1),
    pop2(-2),
    putfield(Opcode.SPECIAL_HANDLING),
    putstatic(Opcode.SPECIAL_HANDLING),
    ret(0),
    /**
     * Should be called "return", but this is a Java keyword.
     */
    return_(0),
    saload(-1),
    sastore(-3),
    sipush(1),
    swap(0),
    tableswitch(-1),
    wide(0);

    public static final int SPECIAL_HANDLING = Integer.MAX_VALUE;

    private final int stackDelta;

    private Opcode(int stackDelta) {
        this.stackDelta = stackDelta;
    }

    public String getMnemonic() {
        final var str = super.toString();
        if (str.endsWith("_"))
            return str.substring(0, str.length() - 1);
        else
            return str;
    }

    /**
     * Returns the stack delta of this opcode, that is, how much larger or smaller the stack is after executing an
     * instruction of this opcode. (E.g. returns 1 for {@code iload} because a single {@code int} is pushed and
     * -2 for {@code dadd} because two {@code double}s (four stack positions) are popped and one {@code double} (two
     * stack positions) is pushed.) For opcodes that have a variable stack delta depending on the instruction operands,
     * {@link #SPECIAL_HANDLING} is returned.
     */
    public int getStackDelta() {
        return stackDelta;
    }
}
