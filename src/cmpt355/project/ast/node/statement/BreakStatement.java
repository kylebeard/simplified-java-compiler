package cmpt355.project.ast.node.statement;

import cmpt355.project.SyntaxException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;

import java.util.List;
import java.util.Optional;

public class BreakStatement extends Statement {

    @Override
    public List<? extends AstNode> children() {
        return List.of();
    }

    @Override
    public String toString() {
        return "break";
    }

    @Override
    public void generateCode(CompileContext context) {
        Optional<Loop> maybeLoop = findAncestor(p -> p instanceof Loop);
        Loop loop = maybeLoop.orElseThrow(() -> new RuntimeException(new SyntaxException("Break statement not inside loop!")));
        context.addCode(Instruction.new_goto(loop.getBreakLabel()));
    }
}
