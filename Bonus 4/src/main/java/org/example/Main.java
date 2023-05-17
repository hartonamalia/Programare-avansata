package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        /**
         * Am folosit faker si stream pentru a crea studenti si proiecte
         */
        var studenti = IntStream.range(0, 5)
                .mapToObj(i -> new Student(faker.name().fullName(), new ArrayList<>()))
                .toArray(Student[]::new);
        var proiecte = IntStream.range(0,5)
                .mapToObj(i -> new Project(faker.book().title()))
                .toArray(Project[]::new);
        /**
         * Am adaugat proiectele in lista de preferinte a studentilor
         */
        studenti[0].getProiecte().addAll(Arrays.asList(proiecte));
        studenti[1].getProiecte().addAll(Arrays.asList(proiecte[0],proiecte[1],proiecte[2],proiecte[3]));
        studenti[2].getProiecte().addAll(Arrays.asList(proiecte[0],proiecte[1],proiecte[2]));
        studenti[3].getProiecte().addAll(Arrays.asList(proiecte[0],proiecte[1]));
        studenti[4].getProiecte().addAll(Arrays.asList(proiecte[0]));

        /**
         * Am creat un map de preferinte pentru studenti
         */
        Map<Student, List<Project>> prefMap = new HashMap<>();

        prefMap.put(studenti[0],studenti[0].getProiecte());
        prefMap.put(studenti[1],studenti[1].getProiecte());
        prefMap.put(studenti[2],studenti[2].getProiecte());
        prefMap.put(studenti[3],studenti[3].getProiecte());
        prefMap.put(studenti[4],studenti[4].getProiecte());

        //prefMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        Problem problem = new Problem(prefMap,Arrays.asList(proiecte));
        //System.out.println(problem.isValid());
        //problem.averagePref();

        /**
         * Am apelat algoritmul Greedy si am testat si performanta implementarii
         */
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();
        problem.gasesteCuplaj();
        Map<Student, Project> solutie = problem.gasesteCuplaj();
        System.out.println("Repartizarea proiectelor pentru studenti: ");
        solutie.entrySet().stream().forEach(entry -> System.out.println("   " + entry.getKey() + " -> " + entry.getValue()));
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println(runningTime);
        System.out.println(usedMemoryAfter);
        System.out.println(memoryIncrease);



        /**
         * Am apelat algoritmul Edmond-Karp si am testat si performanta implementarii
         */
        System.gc();
        Runtime runtimeEdmondKarp = Runtime.getRuntime();
        long usedMemoryBeforeEdmondKarp =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTimeEdmondKarp = System.currentTimeMillis();
        GraphProblem graph = new GraphProblem();

        graph.Matching();
        long runningTimeEdmondKarp = System.currentTimeMillis() - initialTime;
        long usedMemoryAfterEdmondKarp =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncreaseEdmondKarp = usedMemoryAfter - usedMemoryBefore;
        System.out.println(runningTimeEdmondKarp);
        System.out.println(usedMemoryAfterEdmondKarp);
        System.out.println(memoryIncreaseEdmondKarp);
    }

}