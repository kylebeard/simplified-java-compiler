package cmpt355.project.codegen;

import static cmpt355.project.jvm.Opcode.*;
import static cmpt355.project.language.PrimitiveType.*;

import cmpt355.project.InternalCompilerException;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.jvm.Opcode;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that provides helpful shortcuts for common operations in JVM assembly. Each {@code public static} method
 * takes a {@link CompileContext} parameter and adds the appropriate code to it.
 */
public final class Helper {

   private static final Map<String, Opcode> PRIMITIVE_CONVERSION_OPCODES = new HashMap<>();

   static {
      for (var opcode : new Opcode[]{
            i2b, i2c, i2s,
            i2l, i2f, i2d,
            l2i, l2f, l2d,
            f2i, f2l, f2d,
            d2i, d2l, d2f,

      }) {
         String s = opcode.getMnemonic();
         String key = "" + s.charAt(0) + s.charAt(2);
         PRIMITIVE_CONVERSION_OPCODES.put(key, opcode);
      }
   }

   // Cannot instantiate
   private Helper() {}

   private static char primitiveNumericCode(PrimitiveType p) {
      return (p.isEffectivelyInt()) ? 'i'
            : (p == LONG) ? 'l'
            : (p == FLOAT) ? 'f'
            : (p == DOUBLE) ? 'd'
            : '\0';
   }

   private static char primitiveToChar(PrimitiveType p) {
      return (p == BYTE) ? 'b'
            : (p == SHORT) ? 's'
            : (p == CHAR) ? 'c'
            : (p == INT) ? 'i'
            : (p == LONG) ? 'l'
            : (p == FLOAT) ? 'f'
            : (p == DOUBLE) ? 'd'
            : '\0';
   }

   /**
    * Converts between two primitive types using the instructions {@link Opcode#i2l}, {@link Opcode#f2i}, and friends.
    * If no conversion need be done (e.g. the types are the same, or they are represented the same on the JVM) no code
    * is added.
    * <p>
    * For example, {@code Helper.convertTypes(context, PrimitiveType.DOUBLE, PrimitiveType.LONG)} will add an
    * {@link Opcode#d2l} instruction to {@code context}.
    * {@code Helper.convertTypes(context, PrimitiveType.SHORT, PrimitiveType.INT)} will add no code (since
    * {@code short} and {@code int} values are represented the same on the JVM stack).
    *
    * @param context the context to add code to
    * @param from    the primitive type to convert from
    * @param to      the primitive type to convert to
    */
   public static void convertTypes(CompileContext context, PrimitiveType from, PrimitiveType to) {
      // Nothing to be done
      if (from == to
            || from.isEffectivelyInt() && to.isEffectivelyInt())
         return;

      String convCode = "" + primitiveNumericCode(from) + primitiveNumericCode(to);
      Opcode opcode = PRIMITIVE_CONVERSION_OPCODES.getOrDefault(convCode, null);
      if (opcode == null)
         throw new InternalCompilerException("No instruction for converting " + from + " to " + to);
      context.addCode(new Instruction(opcode));
   }

   /*
    * I needed to change the convertTypes method inorder to get typecasting working.
    * I didn't want to change the original method in case other parts of the code
    * use it. This method is used specifically in CastException::generateCode(CompileContext)
    */
   public static void convertTypes2(CompileContext context, PrimitiveType from, PrimitiveType to) {
      // I don't know a better way to do nothing with a c2i cast. Putting a char on the stack
      // automatically converts it to an int, so no instruction should be called
      if (from == to || (from == CHAR && to == INT))
         return;

      String convCode = "" + primitiveToChar(from) + primitiveToChar(to);
      Opcode opcode = PRIMITIVE_CONVERSION_OPCODES.getOrDefault(convCode, null);
      if (opcode == null)
         throw new InternalCompilerException("No instruction for converting " + from + " to " + to);
      context.addCode(new Instruction(opcode));
   }

   /**
    * Adds the appropriate {@code pop} instruction to {@code context} according to {@code type}.
    * <ul>
    *     <li>If {@code type} is {@link DataType#VOID}, does nothing;</li>
    *     <li>if {@code type} is a category 2 type ({@link PrimitiveType#LONG} or
    *          {@link PrimitiveType#DOUBLE}), adds a {@link Opcode#pop2} instruction;</li>
    *     <li>otherwise, {@code type} is a category 1 type, so a {@link Opcode#pop} is added.</li>
    * </ul>
    *
    * @param context the context to add code to
    * @param type    the data type of the value to pop
    */
   public static void pop(CompileContext context, DataType type) {
      if (type == DataType.VOID)
         return;
      else if (type == LONG || type == DOUBLE)
         context.addCode(Instruction.new_pop2());
      else
         context.addCode(Instruction.new_pop());
   }

   /**
    * Adds the appropriate {@code dup} instruction to {@code context} according to {@code type}.
    * <ul>
    *     <li>If {@code type} is a category 2 type ({@link PrimitiveType#LONG} or
    *          {@link PrimitiveType#DOUBLE}), adds a {@link Opcode#dup2} instruction;</li>
    *     <li>otherwise, {@code type} is a category 1 type, so a {@link Opcode#dup} is added.</li>
    * </ul>
    *
    * @param context the context to add code to
    * @param type    the data type of the value to duplicate
    */
   public static void dup(CompileContext context, DataType type) {
      if (type == LONG || type == DOUBLE)
         context.addCode(Instruction.new_dup2());
      else
         context.addCode(Instruction.new_dup());
   }

   /**
    * Adds the appropriate {@code dup_x1} instruction to {@code context} according to {@code type}.
    * <ul>
    *     <li>If {@code type} is a category 2 type ({@link PrimitiveType#LONG} or
    *          {@link PrimitiveType#DOUBLE}), adds a {@link Opcode#dup2_x1} instruction;</li>
    *     <li>otherwise, {@code type} is a category 1 type, so a {@link Opcode#dup_x1} is added.</li>
    * </ul>
    *
    * @param context the context to add code to
    * @param type    the data type of the value to duplicate
    */
   public static void dup_x1(CompileContext context, DataType type) {
      if (type == LONG || type == DOUBLE)
         context.addCode(Instruction.new_dup2_x1());
      else
         context.addCode(Instruction.new_dup_x1());
   }

   /**
    * Adds the appropriate {@code dup_x2} instruction to {@code context} according to {@code type}.
    * <ul>
    *     <li>If {@code type} is a category 2 type ({@link PrimitiveType#LONG} or
    *          {@link PrimitiveType#DOUBLE}), adds a {@link Opcode#dup2_x2} instruction;</li>
    *     <li>otherwise, {@code type} is a category 1 type, so a {@link Opcode#dup_x2} is added.</li>
    * </ul>
    *
    * @param context the context to add code to
    * @param type    the data type of the value to duplicate
    */
   public static void dup_x2(CompileContext context, DataType type) {
      if (type == LONG || type == DOUBLE)
         context.addCode(Instruction.new_dup2_x2());
      else
         context.addCode(Instruction.new_dup_x2());
   }

   /**
    * Adds the appropriate {@code istore} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#istore_0}...{@link Opcode#istore_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#istore} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void istore(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_istore_0();
         case 1 -> Instruction.new_istore_1();
         case 2 -> Instruction.new_istore_2();
         case 3 -> Instruction.new_istore_3();
         default -> Instruction.new_istore(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code lstore} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#lstore_0}...{@link Opcode#lstore_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#lstore} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void lstore(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_lstore_0();
         case 1 -> Instruction.new_lstore_1();
         case 2 -> Instruction.new_lstore_2();
         case 3 -> Instruction.new_lstore_3();
         default -> Instruction.new_lstore(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code fstore} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#fstore_0}...{@link Opcode#fstore_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#fstore} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void fstore(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_fstore_0();
         case 1 -> Instruction.new_fstore_1();
         case 2 -> Instruction.new_fstore_2();
         case 3 -> Instruction.new_fstore_3();
         default -> Instruction.new_fstore(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code dstore} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#dstore_0}...{@link Opcode#dstore_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#dstore} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void dstore(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_dstore_0();
         case 1 -> Instruction.new_dstore_1();
         case 2 -> Instruction.new_dstore_2();
         case 3 -> Instruction.new_dstore_3();
         default -> Instruction.new_dstore(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code astore} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#astore_0}...{@link Opcode#astore_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#astore} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void astore(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_astore_0();
         case 1 -> Instruction.new_astore_1();
         case 2 -> Instruction.new_astore_2();
         case 3 -> Instruction.new_astore_3();
         default -> Instruction.new_astore(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code iload} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#iload_0}...{@link Opcode#iload_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#iload} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void iload(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_iload_0();
         case 1 -> Instruction.new_iload_1();
         case 2 -> Instruction.new_iload_2();
         case 3 -> Instruction.new_iload_3();
         default -> Instruction.new_iload(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code lload} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#lload_0}...{@link Opcode#lload_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#lload} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void lload(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_lload_0();
         case 1 -> Instruction.new_lload_1();
         case 2 -> Instruction.new_lload_2();
         case 3 -> Instruction.new_lload_3();
         default -> Instruction.new_lload(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code fload} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#fload_0}...{@link Opcode#fload_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#fload} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void fload(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_fload_0();
         case 1 -> Instruction.new_fload_1();
         case 2 -> Instruction.new_fload_2();
         case 3 -> Instruction.new_fload_3();
         default -> Instruction.new_fload(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code dload} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#dload_0}...{@link Opcode#dload_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#dload} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void dload(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_dload_0();
         case 1 -> Instruction.new_dload_1();
         case 2 -> Instruction.new_dload_2();
         case 3 -> Instruction.new_dload_3();
         default -> Instruction.new_dload(varIndex);
      });
   }

   /**
    * Adds the appropriate {@code aload} instruction to {@code context} according to the variable index.
    * <ul>
    *     <li>If {@code varIndex} is 0, 1, 2, or 3, adds an {@link Opcode#aload_0}...{@link Opcode#aload_3}
    *          instruction;</li>
    *     <li>otherwise adds an {@link Opcode#aload} instruction.</li>
    * </ul>
    *
    * @param context  the context to add code to
    * @param varIndex the index of the variable in the local variable array
    */
   public static void aload(CompileContext context, int varIndex) {
      context.addCode(switch (varIndex) {
         case 0 -> Instruction.new_aload_0();
         case 1 -> Instruction.new_aload_1();
         case 2 -> Instruction.new_aload_2();
         case 3 -> Instruction.new_aload_3();
         default -> Instruction.new_aload(varIndex);
      });
   }
}
