package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exploration {
    private final SharedMemory mem;// memoria intre roboti
    private final ExplorationMap map;//harta de explorare
    private final List<Robot> robots;//lista de roboti care exploreaza harta

    public Exploration(int n) {
        this.mem = new SharedMemory(n);
        this.map = new ExplorationMap(n);
        this.robots = new ArrayList<>();
    }
    public void addRobot(Robot robot)
    {
        robot.setExploration(this);
        robots.add(robot);

    }
    public int getMapSize(){
        return map.getSize();
    }
    public SharedMemory getMem() {
        return mem;
    } //memoria comuna intre roboti

    public ExplorationMap getMap() {
        return map;
    }//ret harta de explorare @map

    /**
     * Metoda pune robotii in miscare, fiecarui robot corespunzandu-i un thread
     */
    public void start(){
        for(Robot robot : robots){
            new Thread(robot).start();
        }
    }

    /**
     * Metoda pune robotii inapoi in miscare dupa ce acestia au fost pusi pe pauza
     */
    public void resumeRobots()
    {
        for(Robot robot : robots)
            robot.resumeRobot();
    }

    /**
     * Metoda pune toti robotii pe pauza pentru o perioada nedeterminata de timp
     */
    public void pauseRobots(long timePaused){
        for(Robot robot : robots)
            if(timePaused == 0) {
                robot.pauseRobot();
            }
            else{
                robot.pauseRobotForDuration(timePaused);
            }

    }
    /**
     * Metoda repune unu anumit robot dat dupa nume in miscare, sau afiseaza nu exista daca numele nu este unul valid
     */
    public void resumeRobotByName(String name){
        for(Robot robot : robots){
            if(robot.getName().equals(name)){
                robot.resumeRobot();
                return;
            }
        }
        System.out.println("Robotul nu exista!");
    }
    /**
     * Metoda pune un anumit robot pe pauza identificat prin nume si pentru o perioada definita de timp data de timePaused sau nedefinita daca timePaused este 0
     */
    public void pauseRobotByName(String name,long timePaused){
        for(Robot robot : robots){
            if(robot.getName().equals(name)){
                if(timePaused == 0) {
                    robot.pauseRobot();
                    return;
                }
                else{
                    robot.pauseRobotForDuration(timePaused);
                    return;
                }
            }
        }
        System.out.println("Robotul nu exista!");
    }

    /**
     * Metoda afiseaza pe ecran cati tokens a plasat fiecare robot
     */
    public void printTokensByRobot(){
        for(Robot robot : robots){
            System.out.printf("%s a plasat %d tokens\n",robot.getName(),robot.getTokensPlaced());
        }
    }

    /**
     * Metoda opreste toti robotii din lista
     */
    public void stop() {
        for (Robot robot : robots) {
            robot.stopRunning();
        }
    }

    /**
     * Metoda porneste timer-ul, ce are propriul thread de tip daemon ( nu va impiedica inchiderea prog)
     * @param timeLimit
     */
    public void startTimekeeper(long timeLimit) {
        TimeKeeper timekeeper = new TimeKeeper(timeLimit, this); // timelimit rep limita de timp in milisec pt timer
        Thread timekeeperThread = new Thread(timekeeper);
        timekeeperThread.setDaemon(true);
        timekeeperThread.start();
    }

}
