package cmpt355.project.ast.node.statement;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalCompilerException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.Typed;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.MethodDefinition;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.codegen.Helper;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;

import java.util.List;
import java.util.Optional;

import static cmpt355.project.language.PrimitiveType.*;

public class ReturnStatement extends Statement implements Typed {

   private Expression returnValue;

   public ReturnStatement() { }

   public ReturnStatement(Expression returnValue) {
      this.setReturnValue(returnValue);
   }

   @Override
   public List<Expression> children() {
      return getReturnValue().map(List::of).orElse(List.of());
   }

   @Override
   public void validateType() throws DataTypeException {
      Optional<MethodDefinition> maybeMethodDef = findAncestor(n -> n instanceof MethodDefinition);
      if (maybeMethodDef.isEmpty())
         throw new InternalParserException("Return statement not inside a method definition!");
      var methodDef = maybeMethodDef.get();
      var returnType = methodDef.getReturnType().getType();
      if (returnType == DataType.VOID && returnValue != null)
         throw new DataTypeException("Returning value from void method");
      else if (returnType != DataType.VOID && returnValue == null)
         throw new DataTypeException("Return without value from non-void method");
      else if (returnValue != null && !returnType.isSupertypeOf(returnValue.getType()))
         throw new DataTypeException("Cannot return value of type " + returnValue.getType() + " from method with return type " + returnType);
   }

   @Override
   public DataType getType() {
      return (returnValue == null) ? DataType.VOID : returnValue.getType();
   }

   @Override
   public String toString() {
      return "return";
   }

   public Optional<Expression> getReturnValue() {
      return Optional.ofNullable(returnValue);
   }

   public void setReturnValue(Expression returnValue) {
      this.returnValue = reparent(returnValue);
   }

   @Override
   public void generateCode(CompileContext context) {
      // code assumed to be valid at this point so checking the optional is not needed
      var methodDef = (MethodDefinition) findAncestor(n -> n instanceof MethodDefinition).get();
      var methodReturnType = methodDef.getReturnType().getType();
      var returnValueType = this.getType();

      // no return value == return type of method is void
      if (returnValue == null)
         context.addCode(Instruction.new_return());
      else {

         // generate the code for the expression being returned, leaving
         // the value of it on top of the stack
         returnValue.generateCode(context);

         if (returnValueType.isReferenceType()) {
            context.addCode(Instruction.new_checkcast(returnValueType));
            context.addCode(Instruction.new_areturn());
         } else {
            var primReturnValType = (PrimitiveType) returnValueType;
            var primMethodReturnType = (PrimitiveType) methodReturnType;

            // if returnValue's type doesnt match the methods return type,
            // convert it so they match. (also validateType() already checked if the
            // methods return type is a supertype of returnValue's type,
            // so I don't need to do that here
            if (primMethodReturnType != primReturnValType)
               Helper.convertTypes(context, primReturnValType, primMethodReturnType);

            switch (primMethodReturnType.toString()) {
               case "int", "byte", "short", "char", "boolean" -> context.addCode(Instruction.new_ireturn());
               case "float" -> context.addCode(Instruction.new_freturn());
               case "long" -> context.addCode(Instruction.new_lreturn());
               case "double" -> context.addCode(Instruction.new_dreturn());
            }
         }
      }
   }


}



