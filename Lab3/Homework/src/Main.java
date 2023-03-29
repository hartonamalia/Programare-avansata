import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Person andrei = new Programmer("Java","Andrei", LocalDate.of(1988, 1, 1));
        Person maria = new Designer("UX","Maria", LocalDate.of(1990, 1, 1));
        Person mihai = new Designer("UX","Mihai", LocalDate.of(1990, 1, 1));
        Person marcel = new Programmer("C++","Marcel", LocalDate.of(1988, 1, 1));
        Company centric = new Company("Centric","IT");
        Company kfc = new Company("KFC","Food");

        centric.addRelationship(andrei, "angajat");
        centric.addRelationship(maria, "contractor");
        centric.addRelationship(marcel,"angajat");

        kfc.addRelationship(mihai,"manager");

        marcel.addRelationship(andrei,"frate");
        marcel.addRelationship(maria,"cumnata");
        andrei.addRelationship(marcel,"frate");
        andrei.addRelationship(maria, "sotie");
        maria.addRelationship(andrei, "sot");
        maria.addRelationship(marcel,"cumnata");

        List<Node> nodes = new ArrayList<>();
        nodes.add(andrei);
        nodes.add(maria);
        nodes.add(centric);
        nodes.add(mihai);
        nodes.add(marcel);
        nodes.add(kfc);

        Network network = new Network(nodes);
        network.computeImportance();
        network.printNetwork();

    }
}
