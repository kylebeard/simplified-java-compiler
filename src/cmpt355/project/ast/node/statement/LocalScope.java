package cmpt355.project.ast.node.statement;

import cmpt355.project.ast.SymbolHolder;
import cmpt355.project.ast.SymbolTable;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.language.LocalVariable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class LocalScope extends Statement implements SymbolHolder, SymbolTable {

    private Map<String, LocalVariable> locals = new HashMap<>();

    public void defineLocalVariable(LocalVariable variable) {
        locals.put(variable.getName(), variable);
    }

    public Collection<LocalVariable> getDefinedLocalVariables() {
        return locals.values();
    }

    @Override
    public SymbolTable getSymbolTable() {
        return this;
    }

    @Override
    public Optional<LocalVariable> lookupVariable(String name) {
        LocalVariable var = locals.getOrDefault(name, null);
        return Optional.ofNullable(var);
    }
}
