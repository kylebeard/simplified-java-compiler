package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.*;
import cmpt355.project.ast.node.statement.Block;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.*;

/**
 * AST node representing the definition of a method in a class/interface.
 */
public class MethodDefinition extends AstNode implements SymbolHolder, SymbolTable {

    private Method method;
    private Modifiers modifiers;
    private DataTypeNode returnType;
    private String name;
    private List<Parameter> parameters;
    private Block body;

    private MethodDefinition(Modifiers modifiers, DataTypeNode returnType, String name, List<Parameter> parameters, Block body) {
        this.setModifiers(modifiers);
        this.setReturnType(returnType);
        this.setName(name);
        this.setParameters(Collections.unmodifiableList(parameters));
        this.setBody(body);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        List<String> words = new ArrayList<>();
        words.add("method definition:");
        getModifiers().forEach(mod -> words.add(mod.toString()));
        words.add(getReturnType().getType().toString());
        words.add(getName());

        return String.join(" ", words);
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .addIfPresent(returnType)
                .addAll(parameters)
                .addIfPresent(body)
                .build();
    }

    @Override
    public void validateType() throws DataTypeException {
        // Nothing to do
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = Objects.requireNonNull(modifiers);
    }

    public DataTypeNode getReturnType() {
        return returnType;
    }

    public void setReturnType(DataTypeNode returnType) {
        this.returnType = reparentNonNull(returnType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = reparentNonNull(parameters);
    }

    public Optional<Block> getBody() {
        return Optional.ofNullable(body);
    }

    public void setBody(Block body) {
        this.body = reparent(body);
    }

    public Method getMethod() {
        if (method == null) {
            Optional<ClassDefinition> maybeClassDef = findAncestor(n -> n instanceof ClassDefinition);
            var classDef = maybeClassDef.orElseThrow(() -> new InternalParserException("Method definition not inside a class definition!"));

            method = new Method(
                    new InternalClassType(classDef),
                    modifiers,
                    (returnType == null) ? null : returnType.getType(),
                    Lists.map(parameters, Parameter::getType),
                    name
            );
        }

        return method;
    }

    @Override
    public SymbolTable getSymbolTable() {
        return this;
    }

    @Override
    public Optional<Parameter> lookupVariable(String varName) {
        return parameters.stream()
                .filter(p -> p.getName().equals(varName))
                .findAny();
    }

    public static final class Builder {

        private final Modifiers modifiers = new Modifiers();
        private DataTypeNode returnType;
        private String name;
        private final List<Parameter> parameters = new ArrayList<>();
        public Block body;

        private Builder() {}

        public Builder modifier(Modifier modifier) {
            modifiers.add(modifier);
            return this;
        }

        public Builder returnType(DataTypeNode type) {
            this.returnType = type;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parameter(Parameter param) {
            parameters.add(param);
            return this;
        }

        public Builder body(AstNode body) {
            this.body = (Block)body;
            return this;
        }

        public MethodDefinition build() {
            return new MethodDefinition(
                modifiers,
                returnType,
                name,
                parameters,
                body
            );
        }
    }
}
