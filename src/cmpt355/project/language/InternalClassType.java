package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.ClassDefinition;
import cmpt355.project.util.Lists;

import java.util.*;

/**
 * A class representing the type of a class internal to the compiler, i.e. a class type being compiled.
 */
public final class InternalClassType extends ClassType {

    private ClassDefinition def;

    public InternalClassType(ClassDefinition def) {
        super(def.getQualifiedName());
        this.def = def;
    }

    public ClassDefinition getDefinition() {
        return def;
    }

    @Override
    public boolean isClass() {
        return def.getDefinitionType() == ClassDefinition.Type.CLASS;
    }

    @Override
    public boolean isInterface() {
        return def.getDefinitionType() == ClassDefinition.Type.INTERFACE;
    }

    @Override
    public Modifiers getModifiers() {
        return def.getModifiers();
    }

    @Override
    public InternalClassType resolve(AstNode context) {
        return this;
    }

    @Override
    public Optional<ClassType> getSupertype() {
        if (def.getDefinitionType() == ClassDefinition.Type.CLASS) {
            if (def.getExtending().size() == 1)
                return Optional.of((ClassType)def.getExtending().get(0).getType());
            else
                return Optional.of(ExternalClassType.OBJECT);
        } else
            return Optional.empty();
    }

    @Override
    public Optional<Field> findField(String name) {
        for (var field : def.getFields())
            if (field.getName().equals(name))
                return Optional.of(field.getField());
        return Optional.empty();
    }

    @Override
    public List<Method> findMethods(String name) {
        List<Method> methods = new ArrayList<>();
        Set<List<DataType>> existingOverrides = new HashSet<>();
        def.getMethods().forEach(mdef -> {
            var method = mdef.getMethod();
            if (method.getName().equals(name)) {
                methods.add(method);
                existingOverrides.add(method.getParameterTypes());
            }
        });

        if (!name.equals(Method.CONSTRUCTOR_NAME)) {
            Lists.forEach(def.getExtending(), ct -> addMethods(name, (ClassType) ct.getType(), methods, existingOverrides));
            Lists.forEach(def.getImplementing(), ct -> addMethods(name, (ClassType) ct.getType(), methods, existingOverrides));
        } else if (methods.size() == 0)
            // If we're looking for a constructor and there are none defined in the class, synthesize a default ctor
            return List.of(new Method(
                    this,
                    Modifiers.of(Modifier.PUBLIC),
                    DataType.VOID,
                    List.of(),
                    Method.CONSTRUCTOR_NAME
            ));

        return methods;
    }

    private void addMethods(String methodName, ClassType clazz,
                            List<Method> methods, Set<List<DataType>> existingOverrides) {
        clazz.findMethods(methodName).forEach(method -> {
            List<DataType> paramTypes = method.getParameterTypes();
            if (!existingOverrides.contains(paramTypes)) {
                methods.add(method);
                existingOverrides.add(paramTypes);
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalClassType that = (InternalClassType) o;
        return def.equals(that.def);
    }

    @Override
    public int hashCode() {
        return Objects.hash(def);
    }
}
