import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*List<Node> nodes = new ArrayList<>();
        nodes.add(new Person("Denis", LocalDate.of(2023,3,9)));
        nodes.add(new Person("George",LocalDate.of(2023,3,9)));
        nodes.add(new Company("Acer"));
        nodes.add(new Company("Apple"));
        Collections.sort(nodes, Comparator.comparing(Node::getName));
        p1.addRelationship(p2,"frate");
        for (Node node : nodes) {
            System.out.println(node.getName());
        }
         */
        Person andrei = new Programmer("Java","Andrei", LocalDate.of(1980, 1, 1));
        Person ioana = new Designer("UX","Ioana", LocalDate.of(1990, 1, 1));
        Person mihai = new Designer("UX","Mihai", LocalDate.of(1990, 1, 1));
        Person marcel = new Programmer("C++","Marcel", LocalDate.of(1980, 1, 1));
        Company google = new Company("Google","IT");
        Company mc = new Company("McDonalds","Food");

        google.addRelationship(andrei, "angajat");
        google.addRelationship(ioana, "contractor");
        google.addRelationship(marcel,"angajat");

        mc.addRelationship(mihai,"manager");

        marcel.addRelationship(andrei,"frate");
        andrei.addRelationship(marcel,"frate");
        andrei.addRelationship(ioana, "sotie");
        ioana.addRelationship(andrei, "sot");

        List<Node> nodes = new ArrayList<>();
        nodes.add(andrei);//0
        nodes.add(ioana);//1
        nodes.add(google);//2
        nodes.add(mihai);//3
        nodes.add(marcel);//4
        nodes.add(mc);//5

        Network network = new Network(nodes);
        network.computeImportance();
        network.printNetwork();
        System.out.println();
        ArrayList<Node> puncteArticulatie = Graph.findArticulationPoints(network.getNodes(),network.getNodes().size());
        for(Node node : puncteArticulatie)
            System.out.println(node.getName());
        //System.out.println(graph.buildAdjacencyList(network.getNodes()));


    }
}
