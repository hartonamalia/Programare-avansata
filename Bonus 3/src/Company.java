import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company implements Node, Comparable<Company>{
    private String name;
    private String domain;

    Map<Node, String> relationships;
    public Company(String name, String domain)
    {
        this.name = name;
        this.relationships = new HashMap<>();
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void addRelationship(Node node, String value)
    {
        relationships.put(node,value);
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }

    public int getImportance()
    {
        return relationships.size();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Am dat override functiei din interfata Node
     * @return numele companiei
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Compară două obiecte Company pe baza numelui lor.
     * @param other obiectul Company cu care comparam
     * @return 0 dacă ambele obiecte au nume nule, -1 sau 1 daca unu din obiecte este null sau comparatia lexicografica intre cele doua obiecte dupa nume.
     */
    @Override
    public int compareTo(Company other) {
        if(this.name == null && other.name == null)
            return 0;
        if(this.name == null)
            return -1;
        if(other.name == null)
            return 1;
        return this.name.compareTo(other.name);
    }
}