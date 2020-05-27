package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.util.*;

import java.util.*;
import java.util.function.Predicate;

public class OverloadSelector {

    private List<Method> overloadSet;
    private PredicateEx<Method, DataTypeException> requirement = m -> true;
    private PredicateEx<Method, DataTypeException> disqualifier = m -> false;

    public OverloadSelector() {}

    public OverloadSelector(Collection<? extends Method> overloadSet) {
        this.overloadSet = new ArrayList<>(overloadSet);
    }

    public void require(PredicateEx<Method, DataTypeException> requirement) {
        this.requirement = this.requirement.and(requirement);
    }

    public void disqualifyIf(PredicateEx<Method, DataTypeException> disqualifier) {
        this.disqualifier = this.disqualifier.or(disqualifier);
    }

    public void requireAccessibleFrom(ClassType type) {
        require(m -> {
            var methodClass = m.getContainingClass();
            var modifiers = m.getModifiers();
            if (type.equals(methodClass))
                return true;
            else if (type.getPackage().equals(methodClass.getPackage()))
                return !modifiers.contains(Modifier.PRIVATE);
            else if (methodClass.isSupertypeOf(type))
                return modifiers.contains(Modifier.PUBLIC) || modifiers.contains(Modifier.PROTECTED);
            else
                return modifiers.contains(Modifier.PUBLIC);
        });
    }

    public void requireModifier(Modifier mod) {
        require(m -> m.getModifiers().contains(mod));
    }

    public void addOverload(Method method) {
        overloadSet.add(method);
    }

    public Set<Method> select(List<DataType> argumentTypes) throws DataTypeException {
        // Candidates are only the ones that...
        //   • have all required modifiers;
        //   • have none of the disallowed modifiers
        //   • have parameter types compatible with the argument types
        var candidates = Lists.filter(overloadSet,
                m -> requirement.test(m)
                    && !disqualifier.test(m)
                    && isCandidate(m.getParameterTypes(), argumentTypes));

        var order = new PartialOrdering<Method>() {
            @Override
            public boolean lessThan(Method m1, Method m2) {
                var params1 = m1.getParameterTypes();
                var params2 = m2.getParameterTypes();
                for (var p : Lists.zip(params1, params2)) {
                    if (!p.right.isSupertypeOf(p.left))
                        return false;
                }

                return true;
            }
        };

        var roots = Trees.partialOrderingRoots(candidates, order);
        return new HashSet<>(roots);
    }

    /** Determine whether a method is a candidate based on the data types of its parameters. */
    private boolean isCandidate(List<DataType> parameterTypes, List<DataType> argumentTypes) throws DataTypeException {
        if (parameterTypes.size() != argumentTypes.size())
            return false;

        for (var paramArgTypes : Lists.zip(parameterTypes, argumentTypes)) {
            if (!paramArgTypes.left.isSupertypeOf(paramArgTypes.right))
                return false;
        }

        return true;
    }
}
