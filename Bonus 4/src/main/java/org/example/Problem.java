package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    private Map<Student,List<Project>> preferinteProiect;
    private List<Project> proiecte;

    public Problem(Map<Student,List<Project>> preferinteProiect,List<Project> proiecte)
    {
        this.preferinteProiect = preferinteProiect;
        this.proiecte = proiecte;
    }

    public Map<Student, List<Project>> getPreferinteProiect() {
        return preferinteProiect;
    }

    /**
     * Verificam daca instanta problemei este valida
     * @return false daca un student nu are preferinte deloc sau daca numarul de proiecte este mai mic decat numarul de studenti, altfel true
     */
    public boolean isValid()
    {
        for(Map.Entry<Student,List<Project>> entry : preferinteProiect.entrySet()){
            Student student = entry.getKey();
            List<Project> preferinte = entry.getValue();
            if(preferinte.size() <= 0)
            {
                return false;
            }
        }
        if(proiecte.size() < preferinteProiect.keySet().size())
        {
            return false;
        }
        return true;
    }

    /**
     * Se verifica mai intai daca problema este valida
     * Daca da, folosim un stream pentru a calcula preferinta medie
     * Folosim un stream pentru a parcurge map-ul si a afisa studentii care au preferinta mai mica decat cea medie
     */
    public void averagePref()
    {
        if(isValid() == false)
        {
            System.out.println("Problema nu este valida");
            System.exit(1);
        }
        double avgPref = preferinteProiect.values().stream()
                .mapToInt(List::size)
                .average()
                .orElse(0.0);
        System.out.println("Studentii care au un număr de preferințe mai mic decât numărul mediu de preferințe: ");
        preferinteProiect.entrySet().stream().filter(entry -> entry.getValue().size() < avgPref).map(Map.Entry::getKey).forEach(System.out::println);
    }
    /**
     * Se parcurge fiecare student din lista sortată. Pentru fiecare student, se accesează lista sa de preferințe din map-ul preferinteProiect.
     * Se parcurge fiecare proiect din lista de preferințe a studentului curent. Dacă acel proiect nu a fost deja asignat niciunui student în cuplaj, se adaugă o nouă pereche (student, proiect) în map-ul cuplaj.
     * În caz contrar, se parcurge fiecare pereche (student, proiect) din map-ul cuplaj și se verifică dacă proiectul respectiv este deja asignat altui student. Dacă acesta este cazul,
     * se verifică dacă preferința pentru proiectul respectiv este mai mare pentru studentul curent decât pentru studentul deja asignat.
     * Dacă da, se schimbă asignarea și se adaugă noua pereche (student, proiect) în cuplaj.
     * Altfel, se trece la următorul proiect din lista de preferințe a studentului curent.
     */
    public Map<Student, Project> gasesteCuplaj() {
        Map<Student, Project> cuplaj = new HashMap<>();
        List<Student> studenti = new ArrayList<>(preferinteProiect.keySet());
        Collections.sort(studenti, Comparator.comparing(s -> preferinteProiect.get(s).size()));
        //Collections.reverse(studenti);
        for (Student student : studenti) {
            List<Project> preferinte = preferinteProiect.get(student);

            for (Project proiect : preferinte) {
                if (!cuplaj.containsValue(proiect)) {
                    cuplaj.put(student, proiect);
                    break;
                } else {
                    for (Map.Entry<Student, Project> entry : cuplaj.entrySet()) {
                        if (entry.getValue().equals(proiect)) {
                            Student altStudent = entry.getKey();
                            if (preferinte.indexOf(proiect) < preferinte.indexOf(cuplaj.get(altStudent))) {
                                cuplaj.put(student, proiect);
                                cuplaj.remove(altStudent);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(cuplaj.keySet().size() != preferinteProiect.keySet().size())
        {
            System.out.println("Nu s-a putut face impartirea proiectelor pentru toti studentii!");
            System.exit(1);
        }
        return cuplaj;
    }
}
