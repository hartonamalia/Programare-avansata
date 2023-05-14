import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Network {


    private List<Node> nodes;

    public Network()
    {
        this.nodes = new ArrayList<>();
    }
    public  Network(List<Node> nodes)
    {
        this.nodes = nodes;
    }

    public void addNode(Node node)
    {
        nodes.add(node);
    }
    public List<Node> getNodes()
    {
        return nodes;
    }

    /**
     * functia computeImportance afiseaza pe ecran cate conexiuni are obiectul in sine
     */
    public void computeImportance()
    {
        for(Node node : nodes)
        {
            System.out.println(node.getName() + " are conexiuni cu " + node.getImportance());
        }
    }

    /**
     * functia sortNodesByImportance sorteaza cu ajutorul clasei Collections si a unei functii lambda nodurile din lista
     */
    public void sortNodesByImportance()
    {
        Collections.sort(nodes,(a,b) -> b.getImportance() - a.getImportance());
    }

    /**
     * initial sortam lista dupa importanta nodurilor ce este data de numarul de conexiuni
     * apoi pargurgem lista si in functie de clasa obiectului afisam informatii despre obiect si cu cine are conexiuni
     */
    public void printNetwork() {
        sortNodesByImportance();
        for (Node node : nodes) {
            System.out.println(node.getName() + " are conexiuni cu " + node.getImportance());
            if (node instanceof Person) {
                Person persoana = (Person) node;
                System.out.println("Ziua de nastere: " + persoana.getBirthDate());
                if (node instanceof Programmer) {
                    Programmer programmer = (Programmer) node;
                    System.out.println("    Language: " + programmer.getProgrammingLanguage());
                } else if (node instanceof Designer) {
                    Designer designer = (Designer) node;
                    System.out.println("    Design area: " + designer.getDesignDomain());
                }
            } else if (node instanceof Company) {
                Company companie = (Company) node;
                System.out.println("Compania " + companie.getName() + " este in domeniul " + companie.getDomain() + " si are " + companie.getImportance() + " conexiuni.");

            }
            Map<Node, String> relatii = node.getRelationships();
            if (!relatii.isEmpty()) {
                System.out.println("Relatii:");
                for (Map.Entry<Node, String> entry : relatii.entrySet()) {
                    System.out.println(entry.getKey().getName() + " - " + entry.getValue());
                }
            }
        }
    }
}
