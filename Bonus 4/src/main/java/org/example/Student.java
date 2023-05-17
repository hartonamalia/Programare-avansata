package org.example;
import java.util.List;
import java.util.Map;

public class Student implements Comparable<Student>{

    private String nume;
    private List<Project> proiecte;

    public Student(String name,List<Project> proiecte) {
        this.nume = name;
        this.proiecte = proiecte;
    }

    public List<Project> getProiecte() {
        return proiecte;
    }

    public String getName() {
        return nume;
    }

    @Override
    public int compareTo(Student student) {
        return this.nume.compareTo(student.nume);
    }

    @Override
    public String toString() {
        return "Studentul " + nume;
    }
}
