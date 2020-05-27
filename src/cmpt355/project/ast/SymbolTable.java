package cmpt355.project.ast;

import cmpt355.project.language.ClassType;
import cmpt355.project.language.Method;
import cmpt355.project.language.Variable;

import java.util.Optional;

public interface SymbolTable {

    default Optional<? extends Variable> lookupVariable(String name) {
        return Optional.empty();
    }

    default Optional<? extends ClassType> lookupClass(String name) {
        return Optional.empty();
    }
}
