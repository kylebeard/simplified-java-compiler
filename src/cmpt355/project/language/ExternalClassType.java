package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.util.Lists;

import java.util.*;

/**
 * A class representing a class type external to the compiler.
 */
public final class ExternalClassType extends ClassType {

    public static final ExternalClassType
            OBJECT = new ExternalClassType(Object.class),
            CLASS = new ExternalClassType(Class.class),
            STRING = new ExternalClassType(String.class),
            ENUM = new ExternalClassType(Enum.class),
            BYTE = new ExternalClassType(Byte.class),
            SHORT = new ExternalClassType(Short.class),
            INTEGER = new ExternalClassType(Integer.class),
            LONG = new ExternalClassType(Long.class),
            FLOAT = new ExternalClassType(Float.class),
            DOUBLE = new ExternalClassType(Double.class),
            CHARACTER = new ExternalClassType(Character.class),
            BOOLEAN = new ExternalClassType(Boolean.class),
            SYSTEM = new ExternalClassType(System.class);

    private Class<?> javaClass = null;

    /**
     * Returns an <code>ExternalClassType</code> for the class with the given qualified name.
     * @throws DataTypeException if there is no class of this name
     */
    public ExternalClassType(QualifiedIdentifier className) throws DataTypeException {
        super(className);
        try {
            javaClass = Class.forName(className.toString());
        } catch (ClassNotFoundException ex) {
            throw new DataTypeException(ex);
        }
    }

    /**
     * Returns an <code>ExternalClassType</code> for the given Java class.
     * @throws IllegalArgumentException if <code>javaClass</code> does not represent a class (or interface) type
     */
    public ExternalClassType(Class<?> javaClass) {
        super(QualifiedIdentifier.from(javaClass.getCanonicalName()));
        if (!(javaClass.isArray() || javaClass.isPrimitive() || javaClass.isSynthetic()))
            this.javaClass = javaClass;
        else
            throw new IllegalArgumentException(javaClass.getSimpleName() + " does not represent a class or interface type");
    }

    /**
     * Returns <code>true</code> if this is a class type (as opposed to an interface type).
     */
    @Override
    public boolean isClass() {
        return !javaClass.isInterface() && !javaClass.isPrimitive() && !javaClass.isArray();
    }

    /**
     * Returns <code>true</code> if this is an interface type (as opposed to a class type).
     */
    @Override
    public boolean isInterface() {
        return javaClass.isInterface();
    }

    @Override
    public Modifiers getModifiers() {
        return Modifiers.fromExternal(javaClass.getModifiers());
    }

    /**
     * Returns <code>this</code> as an <code>ExternalClassType</code> is already resolved.
     */
    @Override
    public ExternalClassType resolve(AstNode context) {
        return this;
    }

    @Override
    public Optional<ClassType> getSupertype() {
        if (this.equals(OBJECT))
            return Optional.empty();
        var superclass = javaClass.getSuperclass();
        if (superclass == null)
            return Optional.empty();
        return Optional.of(new ExternalClassType(superclass));
    }

    @Override
    public Optional<Field> findField(String name) {
        try {
            return Optional.of(Field.fromExternal(javaClass.getField(name)));
        } catch (NoSuchFieldException ex) {
            return Optional.empty();
        }
    }

    /**
     * Returns all methods of the given name from this class/interface and all superclasses and superinterfaces. If
     * <code>name</code> is {@link Method#CONSTRUCTOR_NAME}, superclass methods are not included as constructors are not
     * inherited.
     */
    @Override
    public List<Method> findMethods(String name) {
        if (name.equals(Method.CONSTRUCTOR_NAME))
            // Constructors are simpler than other methods - they are not inherited so we don't have to scan
            // superclasses for candidates.
            // If we were going to handle chaining to superclass constructors, we would need to work harder here!
            return Lists.map(
                    Arrays.asList(javaClass.getDeclaredConstructors()),
                    Method::fromExternal
            );
        else {
            // Java doesn't provide us a nice way of getting the relevant methods (see comment in addMethods()) so we
            // need to recursively scan over all superclasses/interfaces for possibilities
            List<java.lang.reflect.Method> javaMethods = new ArrayList<>();
            addMethods(name, javaClass, javaMethods, new HashSet<>());

            return Lists.map(javaMethods, Method::fromExternal);
        }
    }

    private void addMethods(String methodName, Class<?> clazz,
                            List<java.lang.reflect.Method> methods, Set<List<Class<?>>> existingOverrides) {
        // Iterate over all this class's declared methods, looking for ones whose names match the requested.
        // We have to do this recursively (over the class and all superclasses/superinterfaces) because
        // Class.getDeclaredMethods() does not return inherited methods and Class.getMethods() only returns public ones.
        // We do *not* want to have both a class's declared method and another method that it overrides in the list,
        // because then we will have a conflict in overload selection (two equally good methods, making it impossible to
        // select either one).

        // First, scan over all methods declared in this particular class; add any whose name matches and whose
        // parameter types have not yet been seen. (We're using Class.getDeclaredMethods() so that non-public methods
        // are included.)
        for (var method : clazz.getDeclaredMethods()) {
            if (!method.getName().equals(methodName) || method.isBridge() || method.isSynthetic())
                continue;
            List<Class<?>> paramTypes = Arrays.asList(method.getParameterTypes());
            if (!existingOverrides.contains(paramTypes)) {
                methods.add(method);
                existingOverrides.add(paramTypes);
            }
        }

        // Recursively apply to the superclass (if any)
        var superclass = clazz.getSuperclass();
        if (superclass != null)
            addMethods(methodName, superclass, methods, existingOverrides);

        // Recursively apply to any interfaces implemented by this class
        for (var inter : clazz.getInterfaces())
            addMethods(methodName, inter, methods, existingOverrides);
    }

    private Class<?> getJavaClass() {
        return javaClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        cmpt355.project.language.ExternalClassType classType = (cmpt355.project.language.ExternalClassType)o;
        return getClassName().equals(classType.getClassName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassName());
    }
}
