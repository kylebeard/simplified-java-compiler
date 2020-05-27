package test;

public final class Badger {

    private final String name;

    public Badger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void badge() {
        System.out.println("Badger badger: " + getName());
    }

    public static void main(String[] args) {
        Badger b = new Badger("Zefrum");
        b.badge();
    }
}
