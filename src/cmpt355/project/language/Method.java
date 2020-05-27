package cmpt355.project.language;

import cmpt355.project.InternalParserException;
import cmpt355.project.util.Lists;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a method of a class, static or non-static.
 *
 * This class contains all information needed to call a method, which does not include the body of the method. For
 * purposes of this class a method consists of a name; a return type; a set of modifiers (public, static, abstract,
 * etc.); a list of parameter data types; and the class that it belongs to.
 */
public class Method {

    /**
     * The name of a constructor. Although in the Java languages constructors do not have names (or their names are the
     * same as the name of the class they belong to), internally all constructors have the name
     * <code>"&lt;init&gt;"</code>, the value of this field.
     */
    public static final String CONSTRUCTOR_NAME = "<init>";
    public static final String STATIC_INITIALIZER_NAME = "<clinit>";

    private ClassType containingClass;
    private Modifiers modifiers;
    private DataType returnType;
    private List<DataType> parameterTypes;
    private String name;

    public Method(
            ClassType containingClass,
            Modifiers modifiers,
            DataType returnType,
            List<DataType> parameterTypes,
            String name
    ) {
        setContainingClass(containingClass);
        setModifiers(modifiers);
        setReturnType(returnType);
        setParameterTypes(parameterTypes);
        setName(name);
    }

    public ClassType getContainingClass() {
        return containingClass;
    }

    public void setContainingClass(ClassType containingClass) {
        this.containingClass = Objects.requireNonNull(containingClass);
    }

    public boolean isConstructor() {
        return CONSTRUCTOR_NAME.equals(name);
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = Objects.requireNonNull(modifiers);
    }

    public DataType getReturnType() {
        return returnType;
    }

    public void setReturnType(DataType returnType) {
        this.returnType = Objects.requireNonNull(returnType);
    }

    public List<DataType> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<DataType> parameterTypes) {
        this.parameterTypes = Objects.requireNonNull(parameterTypes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getJvmSignature() {
        var sb = new StringBuilder(40).append('(');
        for (var param : parameterTypes)
            sb.append(param.toJvm());
        sb.append(')').append(returnType.toJvm());
        return sb.toString();
    }

    /**
     * Returns a <code>Method</code> object from the Java reflection API's definition of an
     * {@link java.lang.reflect.Executable} (a {@link java.lang.reflect.Method} or {@link java.lang.reflect.Constructor}).
     */
    public static Method fromExternal(java.lang.reflect.Executable external) {
        ClassType containingClass = new ExternalClassType(external.getDeclaringClass());
        Modifiers modifiers = Modifiers.fromExternal(external.getModifiers());
        DataType returnType;
        if (external instanceof java.lang.reflect.Constructor<?>)
            returnType = DataType.VOID;
        else if (external instanceof java.lang.reflect.Method)
            returnType = DataType.fromExternal(((java.lang.reflect.Method)external).getReturnType());
        else
            // Shouldn't happen, barring a major change in the Java language!
            throw new InternalParserException("Executable that is neither a constructor nor a method: " + external);

        List<DataType> parameterTypes = Lists.map(Arrays.asList(external.getParameterTypes()), DataType::fromExternal);
        String name = (external instanceof java.lang.reflect.Constructor<?>) ? Method.CONSTRUCTOR_NAME : external.getName();

        return new Method(
                containingClass,
                modifiers,
                returnType,
                parameterTypes,
                name
        );
    }
}
