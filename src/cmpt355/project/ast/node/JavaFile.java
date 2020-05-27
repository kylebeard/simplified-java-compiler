package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.language.*;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.SymbolHolder;
import cmpt355.project.ast.SymbolTable;
import cmpt355.project.util.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * AST node representing a source (.java) file. This is likely to act as the root of the AST.
 */
public class JavaFile extends AstNode implements SymbolHolder, SymbolTable {

    private QualifiedIdentifier pkg;
    private List<Import> imports;
    private List<ClassDefinition> classDefinitions;

    public JavaFile(QualifiedIdentifier pkg,
                    List<Import> imports,
                    List<ClassDefinition> classDefinitions) {
        this.setPkg(pkg);
        this.setImports(Collections.unmodifiableList(imports));
        this.setClassDefinitions(Collections.unmodifiableList(classDefinitions));
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .addAll(getImports())
                .addAll(getClassDefinitions())
                .build();
    }

    @Override
    public void validateType() {
        // Nothing to do
    }

    @Override
    public String toString() {
        if (pkg != null)
            return "file: package " + pkg;
        else
            return "file";
    }

    public Optional<QualifiedIdentifier> getPkg() {
        return Optional.ofNullable(pkg);
    }

    public void setPkg(QualifiedIdentifier pkg) {
        this.pkg = pkg;
    }

    public List<Import> getImports() {
        return imports;
    }

    public void setImports(List<Import> imports) {
        this.imports = reparentNonNull(imports);
    }

    public List<ClassDefinition> getClassDefinitions() {
        return classDefinitions;
    }

    public void setClassDefinitions(List<ClassDefinition> classDefinitions) {
        this.classDefinitions = reparentNonNull(classDefinitions);
    }

    @Override
    public SymbolTable getSymbolTable() {
        return this;
    }

    @Override
    public Optional<ClassType> lookupClass(String name) {
        if (name.indexOf('.') >= 0) {
            // This is a qualified class name
            try {
                return Optional.of(new ExternalClassType(Class.forName(name)));
            } catch (ClassNotFoundException ex) {
                return Optional.empty();
            }
        } else {

            String prefix = getPkg().map(qid -> qid.toString() + ".").orElse("");
            for (var classDef : classDefinitions) {
                var className = classDef.getName();
                if (name.equals(className) || name.equals(prefix + className))
                    return Optional.of(new InternalClassType(classDef));
            }

            // See if the name is among the explicit imports
            for (var imp : imports) {
                if (!imp.isWildcard() && imp.getId().last().equals(name)) {
                    try {
                        var classType = new ExternalClassType(imp.getId());
                        return Optional.of(classType);
                    } catch (DataTypeException ex) {
                        // Do nothing
                    }
                }
            }

            // See if we can resolve the name from among the wildcard imports
            for (var imp : imports) {
                if (imp.isWildcard()) {
                    var tryName = imp.getId().appending(name);
                    try {
                        var classType = new ExternalClassType(tryName);
                        return Optional.of(classType);
                    } catch (DataTypeException ex) {
                        // Do nothing
                    }
                }
            }

            // See if we can resolve the name in java.lang
            try {
                var classType = new ExternalClassType(QualifiedIdentifier.JAVA_LANG.appending(name));
                return Optional.of(classType);
            } catch (DataTypeException ex) {
                // Do nothing
            }

            return Optional.empty();
        }
    }
}
