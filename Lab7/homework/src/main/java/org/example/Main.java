package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 5;//dim hartii
        long timeLimit = 100_000;//lim de timp setata la 100 sec
        Exploration explore = new Exploration(n);
        Robot wallE = new Robot("Wall-E",0,0,explore);
        Robot r2d2 = new Robot("R2D2",n-1,n-1,explore);
        Robot optimus = new Robot("Optimus Prime",0,n-1,explore);

        explore.addRobot(wallE);
        explore.addRobot(r2d2);
        explore.addRobot(optimus);
        explore.start();//incep explorarea cu limita de timp stabilita
        explore.startTimekeeper(timeLimit);
        Scanner scanner = new Scanner(System.in);// obiectul "scanner" citeste comenzile de la utilizator
        while (!explore.getMap().allCellsVisited()) {
            System.out.print("Alegeti o comanda:\n1.Start All\n2.Start by id\n3.Pause All\n4.Pause by id\n5.Exit\n");
            int command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case 1 -> explore.resumeRobots(); // a relua toti rob
                case 2 -> {
                    // a relua un rob dupa nume
                    System.out.println("Introduceti numele robotului: ");
                    String resumeName = scanner.nextLine();
                    explore.resumeRobotByName(resumeName);
                }
                case 3 -> {
                    // pt a opri toti rob pt o per de timp
                    System.out.println("Introduceti durata pauzei in milisecunde(0 pentru pauza ce necesita resume): ");
                    long timePausedAll = scanner.nextLong();
                    explore.pauseRobots(timePausedAll);
                }
                case 4 -> {
                    // pt a opri un rob dupa nume pt per de timp
                    System.out.println("Introduceti numele robotului: ");
                    String pauseName = scanner.nextLine();
                    System.out.println("Introduceti durata pauzei in milisecunde(0 pentru pauza ce necesita resume): ");
                    long timePaused = scanner.nextLong();
                    explore.pauseRobotByName(pauseName, timePaused);
                }
                case 5 -> System.exit(0); // pt a iesi din program1
                case 6 -> explore.printTokensByRobot();// afis informatii despre nr de tokens
                default -> System.out.println("Comanda invalida");
            }

        }
        explore.printTokensByRobot();
    }

}