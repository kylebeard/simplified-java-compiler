package cmpt355.project.ast.node.statement;

import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.CompileContext;

import java.util.Collections;
import java.util.List;

public class Block extends LocalScope {

    private List<Statement> statements;

    public Block(List<Statement> statements) {
        setStatements(statements);
    }

    @Override
    public List<Statement> children() {
        return statements;
    }

    @Override
    public String toString() {
        return String.format("Block of %d statements", statements.size());
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = Collections.unmodifiableList(reparentNonNull(statements));
    }

    @Override
    public void generateCode(CompileContext context) {
        statements.forEach(statement -> statement.generateCode(context));
    }
}
