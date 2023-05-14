import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void findArticulationPoints() {



        Person george = new Programmer("Java","George", LocalDate.of(1980, 1, 1));
        Person denis = new Designer("UX","Denis", LocalDate.of(1990, 1, 1));
        Person rares = new Designer("UX","Rares", LocalDate.of(1990, 1, 1));
        Person maria = new Designer("UX","Rares", LocalDate.of(1990, 1, 1));
        Person sebastian = new Programmer("C++","Sebastian", LocalDate.of(1980, 1, 1));
        Company intel = new Company("Intel","IT");
        Company amd = new Company("Amd","IT");


        george.addRelationship(denis,"frate");
        denis.addRelationship(sebastian,"frate");
        denis.addRelationship(maria, "sotie");
        maria.addRelationship(rares, "sot");
        List<Node> nodes = new ArrayList<>();
        nodes.add(denis);//0
        nodes.add(maria);//1
        nodes.add(george);//2
        nodes.add(sebastian);//3
        nodes.add(rares);//4
        Network network = new Network(nodes);
        ArrayList<Node> articulationPoints = Graph.findArticulationPoints(network.getNodes(),network.getNodes().size() );
        /**
         * Verificam daca  sunt doar 2 puncte de articulatie si daca acestea sunt nodurile denis si maria
         */
        assertEquals(2, articulationPoints.size());
        assertTrue(articulationPoints.contains(denis));
        assertTrue(articulationPoints.contains(maria));

    }
}