import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person implements Node, Comparable<Person>{
    private String name;
    private LocalDate birthDate;

    Map<Node, String> relationships;

    public Person(String name,LocalDate birthDate)
    {
        this.name = name;
        this.birthDate = birthDate;
        this.relationships = new HashMap<>();
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addRelationship(Node node, String value)
    {
        relationships.put(node,value);
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getImportance(){
        return relationships.size();
    }

     //Am dat override functiei din interfata Node
    @Override
    public String getName() {
        return name;
    }


    /*
     - compară două obiecte Persoană pe baza numelui lor
     - parametrul  other obiectul Persoană cu care comparam
     - returneaza 0,dacă ambele obiecte au nume nule, -1 sau 1, daca unu din obiecte este null sau comparatia lexicografica intre cele doua obiecte dupa nume
     */
    @Override
    public int compareTo(Person other) {
        if (this.name == null && other.name == null) {
            return 0;
        } else if (this.name == null) {
            return -1;
        } else if (other.name == null) {
            return 1;
        } else {
            return this.name.compareTo(other.name);
        }
    }
}