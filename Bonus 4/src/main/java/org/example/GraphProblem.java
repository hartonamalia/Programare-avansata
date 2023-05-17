package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class GraphProblem {
    public static void Matching() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);// mi am creat graful
        /**
         * Mi-am creat mai multe obiecte de tip student si proiect
         */
        Faker faker = new Faker();
        var studenti = IntStream.range(0, 5)
                .mapToObj(i -> new Student(faker.name().fullName(), new ArrayList<>()))
                .toArray(Student[]::new);
        var proiecte = IntStream.range(0,5)
                .mapToObj(i -> new Project(faker.book().title()))
                .toArray(Project[]::new);
        // Adăugare noduri în graf

        graph.addVertex(studenti[0].getName());
        graph.addVertex(studenti[1].getName());
        graph.addVertex(studenti[2].getName());
        graph.addVertex(studenti[3].getName());
        graph.addVertex(studenti[4].getName());

        graph.addVertex(proiecte[0].getName());
        graph.addVertex(proiecte[1].getName());
        graph.addVertex(proiecte[2].getName());
        graph.addVertex(proiecte[3].getName());
        graph.addVertex(proiecte[4].getName());

        // Adăugare muchii în graf
        graph.addEdge(studenti[0].getName(), proiecte[0].getName());
        graph.addEdge(studenti[0].getName(), proiecte[1].getName());
        graph.addEdge(studenti[0].getName(), proiecte[2].getName());
        graph.addEdge(studenti[0].getName(), proiecte[3].getName());
        graph.addEdge(studenti[0].getName(), proiecte[4].getName());
        graph.addEdge(studenti[1].getName(), proiecte[0].getName());
        graph.addEdge(studenti[1].getName(), proiecte[1].getName());
        graph.addEdge(studenti[1].getName(), proiecte[2].getName());
        graph.addEdge(studenti[1].getName(), proiecte[3].getName());
        graph.addEdge(studenti[2].getName(), proiecte[0].getName());
        graph.addEdge(studenti[2].getName(), proiecte[1].getName());
        graph.addEdge(studenti[2].getName(), proiecte[2].getName());
        graph.addEdge(studenti[3].getName(), proiecte[0].getName());
        graph.addEdge(studenti[3].getName(), proiecte[1].getName());
        graph.addEdge(studenti[4].getName(), proiecte[0].getName());
        /**
         * Am aplicat algoritmul Edmond-Karp din biblioteca JGraphT
         */
        // Aplicare algoritm Hopcroft-Karp pentru a gasi matchingul de cardinalitate maxima
        HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> hopcroftKarp = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, Set.of(studenti[0].getName(),studenti[1].getName(),studenti[2].getName(),studenti[3].getName(),studenti[4].getName()), Set.of(proiecte[0].getName(),proiecte[1].getName(),proiecte[2].getName(),proiecte[3].getName(),proiecte[4].getName()));
        MatchingAlgorithm.Matching<String, DefaultEdge> matching = hopcroftKarp.getMatching();
        System.out.println("Matching de cardinalitate maxima:");
        for (DefaultEdge edge : matching.getEdges()) {
            System.out.println(graph.getEdgeSource(edge) + " - " + graph.getEdgeTarget(edge));
        }

        //Scopul algoritmului Hopcroft-Karp este să găsească un set de muchii care să acopere cât mai multe noduri posibile,
        // fără ca două muchii să aibă un nod comun. Acest set de muchii reprezintă un matching de cardinalitate maximă în graf
    }
    }

