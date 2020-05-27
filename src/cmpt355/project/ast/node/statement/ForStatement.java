package cmpt355.project.ast.node.statement;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.Label;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.jvm.Opcode;
import cmpt355.project.language.PrimitiveType;
import cmpt355.project.util.Lists;

import java.util.List;

public class ForStatement extends LocalScope implements Loop {

   private List<Statement> initialization;
   private Expression condition;
   private List<Statement> update;
   private Statement body;
   private Label breakLabel, continueLabel;

   public ForStatement(List<Statement> initialization,
                       Expression condition,
                       List<Statement> update,
                       Statement body) {
      this.setInitialization(initialization);
      this.setCondition(condition);
      this.setUpdate(update);
      this.setBody(body);
   }

   @Override
   public List<? extends AstNode> children() {
      return Lists.<AstNode>builder()
            .addAll(getInitialization())
            .addIfPresent(getCondition())
            .addAll(getUpdate())
            .add(getBody())
            .build();
   }

   @Override
   public void validateType() throws DataTypeException {
      if (condition == null)
         return;
      var conditionType = condition.getType();
      if (conditionType != PrimitiveType.BOOLEAN)
         throw new DataTypeException("For loop condition of type " + conditionType);
   }

   @Override
   public String toString() {
      return "for";
   }

   public List<Statement> getInitialization() {
      return initialization;
   }

   public void setInitialization(List<Statement> initialization) {
      this.initialization = reparentNonNull(initialization);
   }

   public Expression getCondition() {
      return condition;
   }

   public void setCondition(Expression condition) {
      this.condition = reparent(condition);
   }

   public List<Statement> getUpdate() {
      return update;
   }

   public void setUpdate(List<Statement> update) {
      this.update = reparentNonNull(update);
   }

   public Statement getBody() {
      return body;
   }

   public void setBody(Statement body) {
      this.body = reparentNonNull(body);
   }

   @Override
   public void generateCode(CompileContext context) {
      var topLabel = context.newLabel("forloop");
      var updateLabel = context.newLabel("update");
      var loopEndLabel = context.newLabel("endfor");
      this.continueLabel = updateLabel;
      this.breakLabel = loopEndLabel;

      // init only once at the beginning
      for (var init : initialization)
         init.generateCode(context);

      topLabel.mark();  // entry point for the loop

      // for loops don't always have conditions
      if (condition != null) {
         condition.generateCode(context);
         context.addCode(new Instruction(Opcode.ifeq, loopEndLabel));
      }
      body.generateCode(context);

      /* Always update at the end of each loop.
      Added a label for continue statements to jump to
      before branching back to the top. */
      updateLabel.mark();
      for (var updateCond : update)
         updateCond.generateCode(context);
      context.addCode(new Instruction(Opcode.goto_, topLabel));

      loopEndLabel.mark();
   }

   @Override
   public Label getBreakLabel() {
      return breakLabel;
   }

   @Override
   public Label getContinueLabel() {
      return continueLabel;
   }
}
