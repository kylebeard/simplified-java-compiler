package cmpt355.project.language;

import cmpt355.project.util.Lists;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * A set of {@link Modifier}s.
 */
public class Modifiers extends AbstractSet<Modifier> {

    private final int n = Modifier.values().length;
    private final BitSet bits = new BitSet(n);

    public Modifiers() {}

    public Modifiers(Modifiers source) {
        this.addAll(source);
    }

    @Override
    public Iterator<Modifier> iterator() {
        return new Iterator<>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                while (i < n && !bits.get(i))
                    ++i;
                return (i < n);
            }

            @Override
            public Modifier next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return Modifier.values()[i++];
            }
        };
    }

    @Override
    public int size() {
        return bits.cardinality();
    }

    public static Modifiers of(Modifier... modifiers) {
        var mods = new Modifiers();
        mods.addAll(Arrays.asList(modifiers));
        return mods;
    }

    /**
     * Creates a <code>Modifiers</code> set from integer flags as defined in {@link java.lang.reflect.Modifier}.
     */
    public static Modifiers fromExternal(int externalModifiers) {
        var m = new Modifiers();

        BiConsumer<Integer, Modifier> f = (k, mod) -> {
            if ((externalModifiers & k) != 0)
                m.add(mod);
        };

        f.accept(java.lang.reflect.Modifier.PUBLIC, Modifier.PUBLIC);
        f.accept(java.lang.reflect.Modifier.PROTECTED, Modifier.PROTECTED);
        f.accept(java.lang.reflect.Modifier.PRIVATE, Modifier.PRIVATE);
        f.accept(java.lang.reflect.Modifier.ABSTRACT, Modifier.ABSTRACT);
        f.accept(java.lang.reflect.Modifier.FINAL, Modifier.FINAL);
        f.accept(java.lang.reflect.Modifier.NATIVE, Modifier.NATIVE);
        f.accept(java.lang.reflect.Modifier.STATIC, Modifier.STATIC);
        f.accept(java.lang.reflect.Modifier.STRICT, Modifier.STRICTFP);
        f.accept(java.lang.reflect.Modifier.SYNCHRONIZED, Modifier.SYNCHRONIZED);
        f.accept(java.lang.reflect.Modifier.TRANSIENT, Modifier.TRANSIENT);
        f.accept(java.lang.reflect.Modifier.VOLATILE, Modifier.VOLATILE);

        return m;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Modifiers))
            return false;
        var m = (Modifiers)o;
        return bits.equals(m.bits);
    }

    @Override
    public int hashCode() {
        return bits.hashCode();
    }


    @Override
    public boolean contains(Object o) {
        if (o instanceof Modifier) {
            var m = (Modifier)o;
            return bits.get(m.ordinal());
        } else
            return false;
    }

    @Override
    public boolean add(Modifier modifier) {
        boolean changed = !bits.get(modifier.ordinal());
        bits.set(modifier.ordinal());
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Modifier) {
            var m = (Modifier)o;
            boolean changed = bits.get(m.ordinal());
            bits.clear(m.ordinal());
            return changed;
        } else
            return false;
    }

    public boolean intersects(Modifiers other) {
        return bits.intersects(other.bits);
    }

    @Override
    public void clear() {
        bits.clear();
    }

    @Override
    public String toString() {
        if (size() == 0)
            return "";
        Modifier[] mods = Modifier.values();
        var sb = new StringBuilder(bits.cardinality() * 10);
        boolean first = true;
        for (int i = 0; i < bits.length(); ++i) {
            if (bits.get(i)) {
                if (!first) {
                    sb.append(' ');
                }
                sb.append(mods[i].toString().toLowerCase());
                first = false;
            }
        }

        return sb.toString();
    }
}
