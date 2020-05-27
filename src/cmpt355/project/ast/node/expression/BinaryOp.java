package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalCompilerException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.Label;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.jvm.Opcode;
import cmpt355.project.language.DataType;
import cmpt355.project.language.ExternalClassType;
import cmpt355.project.language.Method;
import cmpt355.project.language.PrimitiveType;

import static cmpt355.project.jvm.Opcode.*;
import static cmpt355.project.language.ExternalClassType.STRING;
import static cmpt355.project.language.PrimitiveType.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class BinaryOp extends Expression {

   // + (special handling needed because of string concatenation)
   public static class AddOp extends ArithmeticOp {

      private static final Method
            OBJECT_TO_STRING = ExternalClassType.OBJECT.findMethods("toString").get(0),
            BYTE_TO_STRING,
            SHORT_TO_STRING,
            INT_TO_STRING,
            LONG_TO_STRING,
            FLOAT_TO_STRING,
            DOUBLE_TO_STRING,
            CHAR_TO_STRING,
            BOOLEAN_TO_STRING,
            STRING_CONCAT;

      static {
         try {
            BYTE_TO_STRING = Method.fromExternal(Byte.class.getMethod("toString", byte.class));
            SHORT_TO_STRING = Method.fromExternal(Short.class.getMethod("toString", short.class));
            INT_TO_STRING = Method.fromExternal(Integer.class.getMethod("toString", int.class));
            LONG_TO_STRING = Method.fromExternal(Long.class.getMethod("toString", long.class));
            FLOAT_TO_STRING = Method.fromExternal(Float.class.getMethod("toString", float.class));
            DOUBLE_TO_STRING = Method.fromExternal(Double.class.getMethod("toString", double.class));
            CHAR_TO_STRING = Method.fromExternal(Character.class.getMethod("toString", char.class));
            BOOLEAN_TO_STRING = Method.fromExternal(Boolean.class.getMethod("toString", boolean.class));
            STRING_CONCAT = Method.fromExternal(String.class.getMethod("concat", String.class));
         } catch (NoSuchMethodException ex) {
            throw new InternalCompilerException(ex);
         }
      }

      public AddOp(String op, Expression left, Expression right) {
         super("+", left, right);
         if (!"+".equals(op))
            throw new IllegalArgumentException("op must be +");
      }

      @Override
      public DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (leftType.equals(STRING) || rightType.equals(STRING))
            return STRING;
         else
            return super.computeType(leftType, rightType);
      }

      @Override
      public void generateCode(CompileContext context) {
         var destType = getType();
         if (destType instanceof PrimitiveType)
            super.generateCode(context);
         else if (destType.equals(STRING)) {
            DataType leftType = leftType();
            DataType rightType = rightType();

            getLeft().generateCode(context);
            generateToString(context, leftType);
            getRight().generateCode(context);
            generateToString(context, rightType);
            context.addCode(Instruction.new_invokevirtual(STRING_CONCAT));
         } else
            throw new InternalCompilerException("Do not know how to generate code for " + leftType() + "+" + rightType());
      }

      private void generateToString(CompileContext context, DataType fromType) {
         if (fromType.isReferenceType()) {
            // Reference types could be null, which requires special handling (can't call .toString() on a null
            // value!).
            Label nullLabel = context.newLabel("ifnull"),
                  endLabel = context.newLabel("endifnull");
            context.addCode(Instruction.new_dup());
            context.addCode(Instruction.new_ifnull(nullLabel));
            // It's not null - call Object.toString()
            context.addCode(Instruction.new_invokevirtual(OBJECT_TO_STRING));
            context.addCode(Instruction.new_goto(endLabel));
            nullLabel.mark();
            context.addCode(Instruction.new_pop());
            // It's null - just push the string "null"
            context.addCode(Instruction.new_ldc("null"));
            endLabel.mark();
         } else if (fromType == BYTE)
            context.addCode(Instruction.new_invokestatic(BYTE_TO_STRING));
         else if (fromType == SHORT)
            context.addCode(Instruction.new_invokestatic(SHORT_TO_STRING));
         else if (fromType == INT)
            context.addCode(Instruction.new_invokestatic(INT_TO_STRING));
         else if (fromType == LONG)
            context.addCode(Instruction.new_invokestatic(LONG_TO_STRING));
         else if (fromType == FLOAT)
            context.addCode(Instruction.new_invokestatic(FLOAT_TO_STRING));
         else if (fromType == DOUBLE)
            context.addCode(Instruction.new_invokestatic(DOUBLE_TO_STRING));
         else if (fromType == CHAR)
            context.addCode(Instruction.new_invokestatic(CHAR_TO_STRING));
         else if (fromType == BOOLEAN)
            context.addCode(Instruction.new_invokestatic(BOOLEAN_TO_STRING));
         else
            throw new InternalCompilerException("Don't know how to convert type " + fromType + " to string");
      }
   }

   // -, *, /, %
   public static class ArithmeticOp extends BinaryOp {
      public ArithmeticOp(String op, Expression left, Expression right) {
         super(op, left, right);
      }

      @Override
      public DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (!(leftType instanceof PrimitiveType)
               || !(rightType instanceof PrimitiveType)
               || leftType == BOOLEAN || rightType == BOOLEAN)
            throwIncompatibleTypes(leftType, rightType);
         return ((PrimitiveType) leftType).binaryNumericPromotion((PrimitiveType) rightType);
      }

      @Override
      public void generateCode(CompileContext context) {
         PrimitiveType leftType = (PrimitiveType) leftType(),
               rightType = (PrimitiveType) rightType(),
               type = (PrimitiveType) getType();
         getLeft().generateCode(context);
         Helper.convertTypes(context, leftType, leftType.binaryNumericPromotion(rightType));
         getRight().generateCode(context);
         Helper.convertTypes(context, rightType, rightType.binaryNumericPromotion(leftType));

         if (type == INT)
            context.addCode(new Instruction(switch (getOp()) {
               case "+" -> iadd;
               case "-" -> isub;
               case "*" -> imul;
               case "/" -> idiv;
               case "%" -> irem;
               default -> null;
            }));
         else if (type == LONG)
            context.addCode(new Instruction(switch (getOp()) {
               case "+" -> ladd;
               case "-" -> lsub;
               case "*" -> lmul;
               case "/" -> ldiv;
               case "%" -> lrem;
               default -> null;
            }));
         else if (type == FLOAT)
            context.addCode(new Instruction(switch (getOp()) {
               case "+" -> fadd;
               case "-" -> fsub;
               case "*" -> fmul;
               case "/" -> fdiv;
               case "%" -> frem;
               default -> null;
            }));
         else if (type == DOUBLE)
            context.addCode(new Instruction(switch (getOp()) {
               case "+" -> dadd;
               case "-" -> dsub;
               case "*" -> dmul;
               case "/" -> ddiv;
               case "%" -> drem;
               default -> null;
            }));
         else
            throw new InternalCompilerException("Don't know how to compile " + getLeft().getType() + getOp() + getRight().getType());

      }
   }

   // <<, >>, >>>
   public static class ShiftOp extends BinaryOp {
      public ShiftOp(String op, Expression left, Expression right) {
         super(op, left, right);
      }

      @Override
      public DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (!(leftType instanceof PrimitiveType)
               || !(rightType instanceof PrimitiveType))
            throwIncompatibleTypes(leftType, rightType);
         PrimitiveType leftPType = ((PrimitiveType) leftType).unaryNumericPromotion(),
               rightPType = ((PrimitiveType) rightType).unaryNumericPromotion();
         if (!leftPType.isIntegral() || !rightPType.isIntegral())
            throwIncompatibleTypes(leftType, rightType);
         return leftPType;
      }

      @Override
      public void generateCode(CompileContext context) {
         PrimitiveType leftType = (PrimitiveType) leftType(),
               rightType = (PrimitiveType) rightType(),
               type = (PrimitiveType) getType();
         getLeft().generateCode(context);
         Helper.convertTypes(context, leftType, leftType.unaryNumericPromotion());
         getRight().generateCode(context);
         Helper.convertTypes(context, rightType, INT);

         if (type == INT)
            context.addCode(new Instruction(switch (getOp()) {
               case "<<" -> ishl;
               case ">>" -> ishr;
               case ">>>" -> iushr;
               default -> null;
            }));
         else if (type == LONG)
            context.addCode(new Instruction(switch (getOp()) {
               case "<<" -> lshl;
               case ">>" -> lshr;
               case ">>>" -> lushr;
               default -> null;
            }));
         else
            throw new InternalCompilerException("Don't know how to compile " + getLeft().getType() + getOp() + getRight().getType());
      }
   }

   // <, >, <=, >= (and ==/!= via subclass EqualityOp)
   public static class ComparisonOp extends BinaryOp {
      public ComparisonOp(String op, Expression left, Expression right) {
         super(op, left, right);
      }

      @Override
      DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (!(leftType instanceof PrimitiveType)
               || !(rightType instanceof PrimitiveType)
               || leftType == BOOLEAN || rightType == BOOLEAN)
            throwIncompatibleTypes(leftType, rightType);
         return BOOLEAN;
      }

      @Override
      public void generateCode(CompileContext context) {
         PrimitiveType leftType = (PrimitiveType) leftType(),
               rightType = (PrimitiveType) rightType();
         PrimitiveType cmpType = leftType.binaryNumericPromotion(rightType);

         getLeft().generateCode(context);
         Helper.convertTypes(context, leftType, cmpType);
         getRight().generateCode(context);
         Helper.convertTypes(context, rightType, cmpType);

         Label trueCase = context.newLabel("cmp_true"),
               end = context.newLabel("cmp_end");

         if (cmpType.isEffectivelyInt()) {
            var opcode = switch (getOp()) {
               case "==" -> if_icmpeq;
               case "!=" -> if_icmpne;
               case "<" -> if_icmplt;
               case ">" -> if_icmpgt;
               case "<=" -> if_icmple;
               case ">=" -> if_icmpge;
               default -> throw new InternalCompilerException("Unsupported comparison for int: " + getOp());
            };
            context.addCode(new Instruction(opcode, trueCase));
         } else {
            Opcode cmpOpcode = (cmpType == LONG) ? lcmp
                  : (cmpType == FLOAT) ? fcmpg
                  : (cmpType == DOUBLE) ? dcmpg
                  : null;
            context.addCode(new Instruction(cmpOpcode));
            Opcode ifOpcode = switch (getOp()) {
               case "==" -> ifeq;
               case "!=" -> ifne;
               case "<" -> iflt;
               case ">" -> ifgt;
               case "<=" -> ifle;
               case ">=" -> ifge;
               default -> throw new InternalCompilerException("Unsupported comparison for " + cmpType + ": " + getOp());
            };
            context.addCode(new Instruction(ifOpcode, trueCase));
         }

         // We will be executing *either* a push of 0 or a push of 1, so otherwise we overcount
         context.adjustStackSize(-1);

         context.addCode(Instruction.new_iconst_0()); // push false
         context.addCode(Instruction.new_goto(end));
         trueCase.mark();
         context.addCode(Instruction.new_iconst_1());
         end.mark();
      }
   }

   // ==, !=
   public static class EqualityOp extends ComparisonOp {
      public EqualityOp(String op, Expression left, Expression right) {
         super(op, left, right);
      }

      @Override
      DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (leftType instanceof PrimitiveType && rightType instanceof PrimitiveType) {
            if ((leftType == BOOLEAN || rightType == BOOLEAN) && (leftType != rightType))
               throwIncompatibleTypes(leftType, rightType);
         } else if (leftType.isReferenceType() && !rightType.isReferenceType()
               || !leftType.isReferenceType() && rightType.isReferenceType())
            throwIncompatibleTypes(leftType, rightType);

         return BOOLEAN;
      }

      @Override
      public void generateCode(CompileContext context) {
         if (leftType().isReferenceType()) {
            Label trueLabel = context.newLabel("cmp_true"),
                  end = context.newLabel("cmp_end");
            getLeft().generateCode(context);
            getRight().generateCode(context);
            context.addCode(new Instruction("==".equals(getOp()) ? if_acmpeq : if_acmpne, trueLabel));

            // We will be executing *either* a push of 0 or a push of 1, so otherwise we overcount
            context.adjustStackSize(-1);

            context.addCode(Instruction.new_iconst_0());
            context.addCode(Instruction.new_goto(end));
            trueLabel.mark();
            context.addCode(Instruction.new_iconst_1());
            end.mark();
         } else
            super.generateCode(context);
      }
   }

   // &, |, ^
   public static class BitwiseOp extends BinaryOp {
      public BitwiseOp(String op, Expression left, Expression right) {
         super(op, left, right);
      }

      @Override
      DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (!(leftType instanceof PrimitiveType) || !(rightType instanceof PrimitiveType))
            throwIncompatibleTypes(leftType, rightType);
         if (leftType == BOOLEAN || rightType == BOOLEAN) {
            if (leftType == rightType)
               return BOOLEAN;
            else
               throwIncompatibleTypes(leftType, rightType);
         }

         var pType = ((PrimitiveType) leftType).binaryNumericPromotion((PrimitiveType) rightType);
         if (!pType.isIntegral())
            throwIncompatibleTypes(leftType, rightType);
         return pType;
      }

      @Override
      public void generateCode(CompileContext context) {
         PrimitiveType leftType = (PrimitiveType) leftType(),
               rightType = (PrimitiveType) rightType(),
               type = (PrimitiveType) getType();

         getLeft().generateCode(context);
         Helper.convertTypes(context, leftType, type);
         getRight().generateCode(context);
         Helper.convertTypes(context, rightType, type);

         if (type == INT || type == BOOLEAN) {
            context.addCode(switch (getOp()) {
               case "&" -> Instruction.new_iand();
               case "|" -> Instruction.new_ior();
               case "^" -> Instruction.new_ixor();
               default -> throw new InternalCompilerException("Unsupported operation for int: " + getOp());
            });
         } else if (type == LONG) {
            context.addCode(switch (getOp()) {
               case "&" -> Instruction.new_land();
               case "|" -> Instruction.new_lor();
               case "^" -> Instruction.new_lxor();
               default -> throw new InternalCompilerException("Unsupported operation for long: " + getOp());
            });
         } else
            throw new InternalCompilerException("Binary integral promotion to " + type + " unsupported for operation: " + getOp());
      }
   }

   // &&, ||
   public static class ConditionalOp extends BinaryOp {
      public ConditionalOp(String op, Expression left, Expression right) {
         super(op, left, right);
      }

      @Override
      DataType computeType(DataType leftType, DataType rightType) throws DataTypeException {
         if (leftType != BOOLEAN || rightType != BOOLEAN)
            throwIncompatibleTypes(leftType, rightType);
         return BOOLEAN;
      }

      @Override
      public void generateCode(CompileContext context) {
         getLeft().generateCode(context);
         if ("&&".equals(getOp())) {
            Label firstTrue = context.newLabel("and_first_true"),
                  end = context.newLabel("and_end");
            context.addCode(Instruction.new_ifgt(firstTrue));
            context.addCode(Instruction.new_iconst_0());
            context.addCode(Instruction.new_goto(end));
            firstTrue.mark();
            getRight().generateCode(context);
            end.mark();
         } else /* "||".equals(getOp()) */ {
            Label firstFalse = context.newLabel("or_first_false"),
                  end = context.newLabel("or_end");
            context.addCode(Instruction.new_ifeq(firstFalse));
            context.addCode(Instruction.new_iconst_1());
            context.addCode(Instruction.new_goto(end));
            firstFalse.mark();
            getRight().generateCode(context);
            end.mark();
         }
      }
   }

   private String op;
   private Expression left;
   private Expression right;
   private DataType computedType;

   @FunctionalInterface
   private interface BinaryOpConstructor {
      BinaryOp construct(String op, Expression left, Expression right);
   }

   private static final Map<String, BinaryOpConstructor> opConstructors = new HashMap<>();

   BinaryOp(String op, Expression left, Expression right) {
      setOp(op);
      setLeft(left);
      setRight(right);
   }

   public String getOp() {
      return op;
   }

   public void setOp(String op) {
      Objects.requireNonNull(op);
      this.op = op;
   }

   public Expression getLeft() {
      return left;
   }

   public void setLeft(Expression left) {
      this.left = reparentNonNull(left);
   }

   public Expression getRight() {
      return right;
   }

   public void setRight(Expression right) {
      this.right = reparentNonNull(right);
   }

   private static void addOpConstructors(BinaryOpConstructor ctor, String... ops) {
      for (var op : ops)
         opConstructors.put(op, ctor);
   }

   private static Map<String, BinaryOpConstructor> getOpConstructors() {
      if (opConstructors.isEmpty()) {
         addOpConstructors(AddOp::new, "+");
         addOpConstructors(ArithmeticOp::new, "-", "*", "/", "%");
         addOpConstructors(ShiftOp::new, "<<", ">>", ">>>");
         addOpConstructors(ComparisonOp::new, "<", ">", "<=", ">=");
         addOpConstructors(EqualityOp::new, "==", "!=");
         addOpConstructors(BitwiseOp::new, "&", "|", "^");
         addOpConstructors(ConditionalOp::new, "&&", "||");
      }
      return opConstructors;
   }

   public static BinaryOp of(String op, Expression left, Expression right) {
      var ctor = getOpConstructors().getOrDefault(op, null);
      if (ctor == null)
         throw new InternalParserException("Invalid binary op: " + op);
      return ctor.construct(op, left, right);
   }

   void throwIncompatibleTypes(DataType leftType, DataType rightType) throws DataTypeException {
      throw new DataTypeException(String.format("Incompatible types: %s %s %s", leftType, op, rightType));
   }

   DataType leftType() {
      return left.getType();
   }

   DataType rightType() {
      return right.getType();
   }

   abstract DataType computeType(DataType leftType, DataType rightType) throws DataTypeException;

   @Override
   public final DataType getType() {
      return computedType;
   }

   @Override
   public void validateType() throws DataTypeException {
      DataType leftType = leftType(),
            rightType = rightType();
      if (leftType == DataType.VOID || rightType == DataType.VOID)
         throwIncompatibleTypes(leftType, rightType);
      computedType = computeType(leftType(), rightType());
   }

   @Override
   public List<Expression> children() {
      return List.of(left, right);
   }

   @Override
   public String toString() {
      return op;
   }
}
