package cmpt355.project.ast.node.statement;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.jvm.Opcode;
import cmpt355.project.language.PrimitiveType;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Optional;

/**
 * AST node representing an if or if/else statement.
 */
public class IfStatement extends Statement {

   private Expression condition;
   private Statement trueBlock;
   private Statement falseBlock;

   public IfStatement(Expression condition, Statement trueBlock) {
      this(condition, trueBlock, null);
   }

   public IfStatement(Expression condition, Statement trueBlock, Statement falseBlock) {
      this.setCondition(condition);
      this.setTrueBlock(trueBlock);
      this.setFalseBlock(falseBlock);
   }

   @Override
   public String toString() {
      return getFalseBlock().map(b -> "if-else").orElse("if");
   }

   @Override
   public List<? extends AstNode> children() {
      return Lists.<AstNode>builder()
            .add(getCondition())
            .add(getTrueBlock())
            .addIfPresent(getFalseBlock())
            .build();
   }

   @Override
   public void validateType() throws DataTypeException {
      var conditionType = condition.getType();
      if (conditionType != PrimitiveType.BOOLEAN)
         throw new DataTypeException("If statement condition of type " + conditionType);
   }

   public Expression getCondition() {
      return condition;
   }

   public void setCondition(Expression condition) {
      this.condition = reparentNonNull(condition);
   }

   public Statement getTrueBlock() {
      return trueBlock;
   }

   public void setTrueBlock(Statement trueBlock) {
      this.trueBlock = reparentNonNull(trueBlock);
   }

   public Optional<Statement> getFalseBlock() {
      return Optional.ofNullable(falseBlock);
   }

   public void setFalseBlock(Statement falseBlock) {
      this.falseBlock = reparent(falseBlock);
   }

   @Override
   public void generateCode(CompileContext context) {
      var endIfLabel = context.newLabel("endif");

      condition.generateCode(context);
      if (falseBlock == null) { // simple if
         context.addCode(new Instruction(Opcode.ifeq, endIfLabel));
         trueBlock.generateCode(context);
      } else { // if-else
         var falseTopLabel = context.newLabel("falseBlock");
         context.addCode(new Instruction(Opcode.ifeq, falseTopLabel));
         trueBlock.generateCode(context);
         context.addCode(new Instruction(Opcode.goto_, endIfLabel));
         falseTopLabel.mark();
         falseBlock.generateCode(context);

      }
      endIfLabel.mark();
   }
}
