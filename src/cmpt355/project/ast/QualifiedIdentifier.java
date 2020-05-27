package cmpt355.project.ast;

import cmpt355.project.util.Lists;
import org.antlr.v4.runtime.Token;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a qualified identifier, that is, one or more identifiers separated by dots.
 */
public final class QualifiedIdentifier {

    public static final QualifiedIdentifier OBJECT = new QualifiedIdentifier("java", "lang", "Object");
    public static final QualifiedIdentifier JAVA_LANG = new QualifiedIdentifier("java", "lang");

    public final List<String> names;

    /**
     * Constructs a new QualifiedIdentifier from a list of names (identifiers).
     * @throws NullPointerException if names is {@code null}
     * @throws IllegalArgumentException if names is empty
     */
    public QualifiedIdentifier(List<String> names) {
        if (names == null)
            throw new IllegalArgumentException("names cannot be null");
        else if (names.size() == 0)
            throw new IllegalArgumentException("names cannot be empty");

        this.names = Collections.unmodifiableList(names);
    }

    /**
     * Constructs a new QualifiedIdentifier from one or more strings, for example
     * <pre><code>new QualifiedIdentifier("java", "util", "Scanner")</code></pre> for {@code java.util.Scanner}.
     * @throws IllegalArgumentException if no names are furnished
     */
    public QualifiedIdentifier(String... names) {
        this(Arrays.asList(names));
    }

    /**
     * Constructs a new QualifiedIdentifier from a string of dot-separated identifiers. For example,
     * {@code QualifiedIdentifier.from("java.util.Scanner")} has the same effect as
     * {@code new QualifiedIdentifier("java", "util", "Scanner")}.
     * @throws NullPointerException if source is {@code null}
     */
    public static QualifiedIdentifier from(String source) {
        return new QualifiedIdentifier(source.split("\\."));
    }

    public static QualifiedIdentifier from(List<Token> tokens) {
        return new QualifiedIdentifier(Lists.map(tokens, Token::getText));
    }

    /**
     * Returns the length of this QualifiedIdentifier, i.e., how many identifiers it contains. This will always be at
     * least 1.
     */
    public int length() {
        return names.size();
    }

    /**
     * Returns the first name in this QualifiedIdentifier (for x.y.z, returns x). Whenever qi is a QualifiedIdentifier
     * and qi.length() &gt; 1, it is true that qi.head().appending(qi.tail()) is equal to qi.
     * @see #tail()
     */
    public String head() {
        return names.get(0);
    }

    /**
     * Returns a QualifiedIdentifier of the first n names (for x.y.z, .head(2) returns x.y). Whenever qi is a
     * QualifiedIdentifier and 1 &lt; n &lt; qi.length(), it is true that qi.head(n).appending(qi.tail(n)) is equal to
     * qi.
     * @throws IllegalArgumentException if count is less than 1 or greater than the number of names
     * @see #tail(int)
     */
    public QualifiedIdentifier head(int count) {
        if (count > names.size())
            throw new IllegalArgumentException("count out of range");
        else if (count == names.size())
            return this;
        else
            return new QualifiedIdentifier(names.subList(0, count));
    }

    /**
     * Returns the tail of this QualifiedIdentifier after the head is removed, that is, all names but the first (for
     * x.y.z, returns y.z). Whenever qi is a QualifiedIdentifier and qi.length() &gt; 1, it is true that
     * qi.head().appending(qi.tail()) is equal to qi.
     * @throws IllegalStateException if there is only one name
     * @see #head()
     */
    public QualifiedIdentifier tail() {
        return new QualifiedIdentifier(names.subList(1, names.size()));
    }

    /**
     * Returns the tail of this QualifiedIdentifier after the head of {@code count} names is removed (for x.y.z,
     * .tail(2) returns z). Whenever qi is a QualifiedIdentifier and 1 &lt; n &lt; qi.length(), it is true that
     * qi.head(n).appending(qi.tail(n)) is equal to qi.
     * @throws IllegalArgumentException if count < 0 or count ≥ this.length()
     */
    public QualifiedIdentifier tail(int count) {
        if (count + 1 > names.size())
            throw new IllegalArgumentException("count out of range");
        else if (count == 0)
            return this;
        else
            return new QualifiedIdentifier(names.subList(count, names.size()));
    }

    /**
     * Returns the last name (identifier) in this QualifiedIdentifier (for x.y.z, returns the string z).
     */
    public String last() {
        return names.get(names.size() - 1);
    }

    /**
     * Returns a new QualifiedIdentifier composed of all names of this identifier followed by all those of id. If qi1
     * is a.b.c and qi2 is x.y, the result is a.b.c.x.y.
     * @throws NullPointerException if id is {@code null}
     */
    public QualifiedIdentifier appending(QualifiedIdentifier id) {
        return new QualifiedIdentifier(Lists.<String>builder().addAll(names).addAll(id.names).build());
    }

    /**
     * Returns new QualifiedIdentifier obtained by adding a single additional name to this one. If qi is a.b.c, then
     * qi.appending("onetwothree" is a.b.c.onetwothree.
     * @throws NullPointerException if name is {@code null}
     */
    public QualifiedIdentifier appending(String name) {
        return new QualifiedIdentifier(Lists.<String>builder().addAll(names).add(name).build());
    }

    @Override
    public int hashCode() {
        return names.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QualifiedIdentifier))
            return false;
        return names.equals(((QualifiedIdentifier)obj).names);
    }

    /**
     * Returns the string obtained by joining the names together with dots between them (i.e.
     * {@code new QualifiedIdentifier("java", "util", "Scanner").toString()} returns "java.util.Scanner".
     */
    @Override
    public String toString() {
        return String.join(".", names);
    }

    /**
     * Returns the string obtained by joining the names together with forward slashes between them (i.e.
     * {@code new QualifiedIdentifier("java", "util", "Scanner").toString()} returns "java/util/Scanner".
     */
    public String toJvmString() {
        return String.join("/", names);
    }
}
