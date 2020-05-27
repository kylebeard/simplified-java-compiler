package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.SymbolHolder;
import cmpt355.project.ast.SymbolTable;
import cmpt355.project.ast.Typed;
import cmpt355.project.ast.node.statement.Block;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.*;

/**
 * An AST node representing the definition of a class (or an interface). Throughout this class, the word "class" is used
 * to generically refer to either a class or an interface, since the two are syntactically almost the same.
 */
public class ClassDefinition extends AstNode implements SymbolHolder, SymbolTable, Typed {

    /**
     * An enum describing the type (class or interface definition)
     */
    public enum Type {
        CLASS, INTERFACE;

        /**
         * Returns "class" or "interface"
         */
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private Type type;
    private Modifiers modifiers;
    private QualifiedIdentifier pkg;
    private String name;
    private List<DataTypeNode> extending;
    private List<DataTypeNode> implementing;
    private List<FieldDeclaration> fields;
    private List<MethodDefinition> methods;

    /**
     * See {@link ClassDefinition.Builder}
     */
    private ClassDefinition(Type type,
                            Modifiers modifiers,
                            QualifiedIdentifier pkg,
                            String name,
                            List<ClassType> extending,
                            List<ClassType> implementing,
                            List<FieldDeclaration> fields,
                            List<MethodDefinition> methods) {
        this.setType(type);
        this.setModifiers(modifiers);
        this.setPackage(pkg);
        this.setName(name);
        this.setExtending(Lists.map(extending, DataTypeNode::new));
        this.setImplementing(Lists.map(implementing, DataTypeNode::new));
        this.setFields(fields);
        this.setMethods(methods);
    }

    @Override
    public SymbolTable getSymbolTable() {
        return this;
    }

    @Override
    public Optional<Variable> lookupVariable(String name) {
        return fields.stream()
                .filter(field -> field.getName().equals(name))
                .findAny()
                .map(FieldDeclaration::getField);
    }

    @Override
    public Optional<ClassType> lookupClass(String className) {
        if (className.equals(name)
                || className.equals(getQualifiedName().toString()))
            return Optional.of(new InternalClassType(this));
        return Optional.empty();
    }

    @Override
    public String toString() {
        List<String> words = new ArrayList<>();
        getModifiers().forEach(mod -> words.add(mod.toString()));
        words.add(getDefinitionType().toString());
        words.add(getName());

        if (getExtending().size() > 0) {
            words.add("extends");
            extending.forEach(ext -> words.add(ext.toString()));
        }

        if (getImplementing().size() > 0) {
            words.add("implements");
            implementing.forEach(imp -> words.add(imp.toString()));
        }

        return String.join(" ", words);
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .addAll(getExtending())
                .addAll(getImplementing())
                .addAll(getFields())
                .addAll(getMethods())
                .build();
    }

    @Override
    public DataType getType() {
        return new InternalClassType(this);
    }

    @Override
    public void validateType() throws DataTypeException {
        if (type == Type.CLASS) {
            if (extending.size() > 1)
                throw new DataTypeException("Class " + name + " cannot extend multiple classes");
            if (extending.size() == 1) {
                var superclass = (ClassType)extending.get(0).getType();
                if (!superclass.isClass())
                    throw new DataTypeException("Class " + name + " extends non-class " + superclass.getClassName());
                var superMods = superclass.getModifiers();
                if (superMods.contains(Modifier.FINAL))
                    throw new DataTypeException("Class " + name + " extends final class " + superclass.getClassName());
                else if (!superMods.contains(Modifier.PUBLIC) && !superclass.getPackage().equals(pkg.toString()))
                    throw new DataTypeException("Class " + name + " extends non-public class " + superclass.getClassName() + " in another package");
            }

            for (var interfaceNode : implementing) {
                var inter = (ClassType)interfaceNode.getType();
                if (!inter.isInterface())
                    throw new DataTypeException("Class " + name + " implements non-interface " + inter.getClassName());
                    // Interfaces can't be final, so no need to check for that!
                else if (!inter.getModifiers().contains(Modifier.PUBLIC) && !inter.getPackage().equals(pkg.toString()))
                    throw new DataTypeException("Class " + name + " implements non-public interface " + inter.getClassName() + " in another package");
            }

            // If there is no constructor, add a default one
            boolean hasExplicitConstructor = methods.stream()
                    .anyMatch(def -> def.getMethod().isConstructor());
            if (!hasExplicitConstructor) {
                var ctor = MethodDefinition.builder()
                        .name(Method.CONSTRUCTOR_NAME)
                        .returnType(new DataTypeNode(DataType.VOID))
                        .modifier(Modifier.PUBLIC)
                        .body(new Block(List.of()))
                        .build();
                addMethod(ctor);
            }
        } else if (type == Type.INTERFACE) {
            if (implementing.size() > 0)
                throw new DataTypeException("Interface " + name + " cannot implement other interfaces");
            for (var interfaceNode : extending) {
                var inter = (ClassType)interfaceNode.getType();
                if (!inter.isInterface())
                    throw new DataTypeException("Interface " + name + " extends non-interface " + inter.getClassName());
                else if (!inter.getModifiers().contains(Modifier.PUBLIC) && !inter.getPackage().equals(pkg.toString()))
                    throw new DataTypeException("Interface " + name + " extends non-public interface " + inter.getClassName() + " in another package");
            }
        }
    }

    /**
     * Returns a {@link ClassDefinition.Builder} to be used in creating a ClassDefinition.
     */
    public static Builder builder() {
        return new Builder();
    }

    public Type getDefinitionType() {
        return type;
    }

    public void setType(Type type) {
        Objects.requireNonNull(type);
        this.type = type;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        Objects.requireNonNull(modifiers);
        this.modifiers = modifiers;
    }

    public Optional<QualifiedIdentifier> getPackage() {
        return Optional.ofNullable(pkg);
    }

    public void setPackage(QualifiedIdentifier pkg) {
        this.pkg = pkg;
    }

    public String getName() {
        return name;
    }

    public QualifiedIdentifier getQualifiedName() {
        return getPackage().map(pkg -> pkg.appending(name)).orElse(new QualifiedIdentifier(name));
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public List<DataTypeNode> getExtending() {
        return extending;
    }

    public void setExtending(List<DataTypeNode> extending) {
        this.extending = reparentNonNull(extending);
    }

    public List<DataTypeNode> getImplementing() {
        return implementing;
    }

    public void setImplementing(List<DataTypeNode> implementing) {
        this.implementing = reparentNonNull(implementing);
    }

    public List<FieldDeclaration> getFields() {
        return fields;
    }

    public void setFields(List<FieldDeclaration> fields) {
        this.fields = reparentNonNull(fields);
    }

    public List<MethodDefinition> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodDefinition> methods) {
        this.methods = reparentNonNull(methods);
    }

    public void addMethod(MethodDefinition method) {
        var newMethods = new ArrayList<>(methods);
        newMethods.add(method);
        setMethods(newMethods);
    }

    /**
     * A helper class (following the <a href="https://dzone.com/articles/design-patterns-the-builder-pattern">builder</a>
     * pattern) for creating a ClassDefinition.
     *
     * <p>For example, the following code will create a ClassDefinition:</p>
     * <pre><code>
     *     ClassDefinition classDef = ClassDefinition.builder()
     *          .type(ClassDefinition.Type.CLASS)
     *          .modifier(Modifier.PUBLIC)
     *          .modifier(Modifier.FINAL)
     *          .name("ExampleClass")
     *          .extending(new QualifiedIdentifier("Object"))
     *          .implementing(new QualifiedIdentifier("Comparable"))
     *          .build();
     * </code></pre>
     */
    public static final class Builder {
        private Type type;
        private final Modifiers modifiers = new Modifiers();
        private QualifiedIdentifier pkg;
        private String name;
        private final List<ClassType> extending = new ArrayList<>();
        private final List<ClassType> implementing = new ArrayList<>();
        private final List<FieldDeclaration> fields = new ArrayList<>();
        private final List<MethodDefinition> methods = new ArrayList<>();
        private Block initializer;
        private Block staticInitializer;

        private Builder() {}

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder modifier(Modifier modifier) {
            modifiers.add(modifier);
            return this;
        }

        public Builder inPackage(QualifiedIdentifier pkg) {
            this.pkg = pkg;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder extending(ClassType id) {
            extending.add(id);
            return this;
        }

        public Builder implementing(ClassType id) {
            implementing.add(id);
            return this;
        }

        public Builder field(AstNode var) {
            fields.add((FieldDeclaration)var);
            return this;
        }

        public Builder method(AstNode method) {
            methods.add((MethodDefinition)method);
            return this;
        }

        public ClassDefinition build() {
            if (type == null
                    || name == null)
                throw new IllegalStateException("Must specify type and name");
            return new ClassDefinition(type,
                    modifiers,
                    pkg,
                    name,
                    extending,
                    Collections.unmodifiableList(implementing),
                    Collections.unmodifiableList(fields),
                    Collections.unmodifiableList(methods));
        }
    }
}
