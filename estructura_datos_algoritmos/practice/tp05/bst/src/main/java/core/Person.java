package core;

public class Person implements Comparable<Person>{

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Legajo: " + id);
        s.append("\n");
        s.append("Nombre: " + name);

        return s.toString();
    }

}
