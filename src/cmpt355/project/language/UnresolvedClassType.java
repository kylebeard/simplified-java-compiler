package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.node.AstNode;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a class type that has not yet been resolved to a specific class. Class names in Java, such as
 * <code>List</code>, can refer to different classes depending on the context (e.g. <code>java.util.List</code>, or
 * <code>java.awt.List</code>, or the class being compiled if its name is <code>List</code>) and it is impossible to
 * determine which until symbol tables are set up. This class can be used to hold these class types until they can be
 * resolved.
 *
 * Since the type is unresolved, methods returning information about the type will throw an exception.
 */
public final class UnresolvedClassType extends ClassType {

    public UnresolvedClassType(QualifiedIdentifier className) {
        super(className);
    }

    private <T> T throwUnresolved() throws InternalParserException {
        throw new InternalParserException("Type " + getClassName() + " is unresolved");
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public boolean isClass() {
        return throwUnresolved();
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public boolean isInterface() {
        return throwUnresolved();
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public Modifiers getModifiers() {
        return throwUnresolved();
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public Optional<ClassType> getSupertype() {
        return throwUnresolved();
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public boolean isSupertypeOf(DataType other) {
        return throwUnresolved();
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public Optional<Field> findField(String name) {
        return throwUnresolved();
    }

    /**
     * Throws an {@link InternalParserException} (the type is unresolved so this information is not yet available).
     */
    @Override
    public List<Method> findMethods(String name) {
        return throwUnresolved();
    }

    /**
     * Attempts to resolve this class type in the context given. This method will either
     * <ul>
     *     <li>return an {@link InternalClassType} if the type refers to a class being compiled; or</li>
     *     <li>return an {@link ExternalClassType} if the type refers to an external class; or</li>
     *     <li>throws an {@link DataTypeException} if the type cannot be resolved to a class.</li>
     * </ul>
     */
    @Override
    public ClassType resolve(AstNode context) throws DataTypeException {
        var maybeClassType = context.findClass(getClassName().toString());
        if (maybeClassType.isPresent())
            return maybeClassType.get();
        else
            return new ExternalClassType(getClassName());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UnresolvedClassType))
            return false;
        var uct = (UnresolvedClassType)o;
        return super.getClassName().equals(uct.getClassName());
    }

    @Override
    public int hashCode() {
        return super.getClassName().hashCode();
    }
}
