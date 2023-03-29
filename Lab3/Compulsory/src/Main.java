import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // am creat o listă de noduri (care poate include atat obiecte de tip Person, cat si company)

        List<Node> nodes = new ArrayList<>();

        nodes.add(new Person("Amalia"));
        nodes.add(new Person("Nicola"));
        nodes.add(new Company("Apple"));
        nodes.add(new Person("Maya"));
        nodes.add(new Company("Centric"));


        Collections.sort(nodes, Comparator.comparing(Node::getName));

          // le ordonez in ordine alfabetica

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            System.out.println(node.getName());
        }
    }
}
