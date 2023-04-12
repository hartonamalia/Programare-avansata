package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem; // memoria intre roboti
    private final ExplorationMap map; //harta de explorare
    private final List<Robot> robots; //lista de roboti care exploreaza harta

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
    }

    public ExplorationMap getMap() {
        return map;
    } //ret harta de explorare @map
    
    
    /*
     * Metoda pune robotii in miscare, fiecarui robot corespunzandu-i un thread
     */
    public void start() {
        for (Robot robot : robots) {
            new Thread(robot).start();
        }
    }
}