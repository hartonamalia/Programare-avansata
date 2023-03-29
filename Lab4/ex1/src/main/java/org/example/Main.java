package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        /**
         * Am folosit stream pentru a genera studenti si proiecte
         */
        var students = IntStream.rangeClosed(0,2).mapToObj(i -> new Student("S" + i,new ArrayList<>())).toArray(Student[]::new);
        var projects = IntStream.rangeClosed(0,2).mapToObj(i -> new Project("P" + i)).toArray(Project[]::new);


        students[0].getProiecte().addAll(Arrays.asList(projects));
        students[1].getProiecte().addAll(Arrays.asList(projects[0],projects[1]));
        students[2].getProiecte().addAll(Arrays.asList(projects[0]));


        List<Student> listaStudenti = new LinkedList<>(Arrays.asList(students));

        List<Student> newSortedList = listaStudenti.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList());
        newSortedList.forEach(System.out::println);

        /**
         * Am stocat proiectele intr-un TreeSet, dar nu le-am mai sortat
         */
        Set<Project> listaProiecte = new TreeSet<>(Arrays.asList(projects));
        listaProiecte.forEach(System.out::println);
    }

}