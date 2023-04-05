package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        /*
         * Am folosit faker si stream pentru a crea studenti si proiecte
         */
        var studenti = IntStream.range(0, 5)
                .mapToObj(i -> new Student(faker.name().fullName(), new ArrayList<>()))
                .toArray(Student[]::new);
        var proiecte = IntStream.range(0,5)
                .mapToObj(i -> new Project(faker.book().title()))
                .toArray(Project[]::new);

        //Am adaugat proiectele in lista de preferinte a studentilor

        studenti[0].getProiecte().addAll(Arrays.asList(proiecte));
        studenti[1].getProiecte().addAll(Arrays.asList(proiecte[0],proiecte[1],proiecte[2],proiecte[3]));
        studenti[2].getProiecte().addAll(Arrays.asList(proiecte[0],proiecte[1],proiecte[2]));
        studenti[3].getProiecte().addAll(Arrays.asList(proiecte[0],proiecte[1]));
        studenti[4].getProiecte().addAll(Arrays.asList(proiecte[0]));

        //Am creat un map de preferinte pentru studenti

        Map<Student, List<Project>> prefMap = new HashMap<>();

        prefMap.put(studenti[0],studenti[0].getProiecte());
        prefMap.put(studenti[1],studenti[1].getProiecte());
        prefMap.put(studenti[2],studenti[2].getProiecte());
        prefMap.put(studenti[3],studenti[3].getProiecte());
        prefMap.put(studenti[4],studenti[4].getProiecte());



        Problem problem = new Problem(prefMap,Arrays.asList(proiecte));

        problem.averagePref();



        problem.gasesteCuplaj();// atribui proiectele studentilor in functie de preferinte
        Map<Student, Project> solutie = problem.gasesteCuplaj();// pentru perechile student-proiect
        System.out.println("Repartizarea proiectelor pentru studenti: ");
        solutie.entrySet().stream().forEach(entry -> System.out.println("   " + entry.getKey() + " -> " + entry.getValue()));



    }

}