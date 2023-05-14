import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    /**
     * Variabila time folosita in gasirea punctelor de articulatie
     */
    private static int time;

    /**
     *  Functie care gaseste toate punctele de articulatie
     *
     * @param nodes lista de noduri
     * @param V     numarul de noduri
     * @return lista cu punctele de articulatie
     */
    public static ArrayList<Node> findArticulationPoints(List<Node> nodes, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        int time = 0, par = -1;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                findArticulationPointsUtil(nodes, i, visited, disc, low, par, isAP);
            }
        }
        ArrayList<Node> articulationPoints = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (isAP[i]) {
                articulationPoints.add(nodes.get(i));
                count++;
            }
        }
        return articulationPoints;
    }

    /**
     *  Functie recursiva care gaseste toate punctele de articulatie
     *
     * @param nodes   lista de noduri
     * @param u       nod curent
     * @param visited vector de noduri vizitate
     * @param disc    vector de timp
     * @param low     vector
     * @param par     parintele nodului curent
     * @param isAP    vector de puncte de articulatie
     */
    private static void findArticulationPointsUtil(List<Node> nodes, int u, boolean[] visited, int[] disc, int[] low,
                                                   int par, boolean[] isAP) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (Map.Entry<Node, String> entry : nodes.get(u).getRelationships().entrySet()) {
            int v = nodes.indexOf(entry.getKey());
            if (!visited[v]) {
                children++;
                findArticulationPointsUtil(nodes, v, visited, disc, low, u, isAP);
                low[u] = Math.min(low[u], low[v]);
                if (par != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            } else if (v != par) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (par == -1 && children > 1) {
            isAP[u] = true;
        }
    }
}

