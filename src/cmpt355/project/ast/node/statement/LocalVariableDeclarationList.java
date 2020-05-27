package cmpt355.project.ast.node.statement;

import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.CompileContext;

import java.util.List;

public class LocalVariableDeclarationList extends Statement {

    private List<LocalVariableDeclaration> declarations;

    public LocalVariableDeclarationList(List<LocalVariableDeclaration> declarations) {
        setDeclarations(declarations);
    }

    public List<LocalVariableDeclaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<LocalVariableDeclaration> declarations) {
        this.declarations = reparentNonNull(declarations);
    }

    @Override
    public List<LocalVariableDeclaration> children() {
        return declarations;
    }

    @Override
    public String toString() {
        return "local variable declarations";
    }

    @Override
    public void generateCode(CompileContext context) {
        declarations.forEach(decl -> decl.generateCode(context));
    }
}
