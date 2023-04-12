package org.example;

public class Main {
    public static void main(String args[]) {
        int n=4; //dim hartii
        var explore = new Exploration(n);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("Sophia"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
    }
}