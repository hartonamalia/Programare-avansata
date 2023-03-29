
public class Person implements Node, Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person p) {
        return this.name.compareTo(p.name);
    }
}
