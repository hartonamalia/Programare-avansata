import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = new ArrayList<>();
        List<Road> roads = new ArrayList<>();

        City bucuresti = new City("Bucuresti", 2000000);
        City iasi = new City("Iasi", 400000);
        Airport otopeni = new Airport("Henri Coanda", 2);
        GasStation mol = new GasStation("MOL", 6.50);

        locations.add(bucuresti);
        locations.add(iasi);
        locations.add(otopeni);
        locations.add(mol);

        roads.add(new Road(bucuresti, iasi));
        roads.add(new Road(bucuresti, otopeni));
        roads.add(new Road(iasi, otopeni));

        for (Road road : roads) {
            System.out.println(road.getStart().getName() + " catre " + road.getEnd().getName());
        }

        System.out.println();

        for (Location location : locations) {
            System.out.println(location.getName());
        }

        System.out.println();

        if (isValidInstance(locations, roads)) {
            System.out.println("Instanta problemei este valida.");
        } else {
            System.out.println("Instanta problemei nu este valida.");
        }

        System.out.println();

        if (canReachDestination(bucuresti, otopeni, roads)) {
            System.out.println("Poti ajunge la " + otopeni.getName() + " din " + bucuresti.getName() + ".");
        } else {
            System.out.println("Poti ajunge la " + otopeni.getName() + " din " + bucuresti.getName() + ".");
        }
    }

    private static boolean isValidInstance(List<Location> locations, List<Road> roads) {
        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                if (locations.get(i).equals(locations.get(j))) {
                    return false;
                }
            }
        }
        for (int i = 0; i < roads.size(); i++) {
            for (int j = i + 1; j < roads.size(); j++) {
                if (roads.get(i).equals(roads.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canReachDestination(Location start, Location destination, List<Road> roads) {
        List<Location> visited = new ArrayList<>();
        visited.add(start);
        return canReachDestinationHelper(start, destination, roads, visited);
    }

    private static boolean canReachDestinationHelper(Location current, Location destination, List<Road> roads, List<Location> visited) {
        if (current.equals(destination)) {
            return true;
        }
        for (Road road : roads) {
            if (road.getStart().equals(current) && !visited.contains(road.getEnd())) {
                visited.add(road.getEnd());
                if (canReachDestinationHelper(road.getEnd(), destination, roads, visited)) {
                    return true;
                }
            } else if (road.getEnd().equals(current) && !visited.contains(road.getStart())) {
                visited.add(road.getStart());
                if (canReachDestinationHelper(road.getStart(), destination, roads, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}

