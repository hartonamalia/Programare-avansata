package org.example;

import java.util.List;
import java.util.Random;

public class Robot implements Runnable{
    private String name;// numele robotului
    private boolean running;// daca robotul ruleaza sau nu
    private boolean paused;
    private int row;// stocheaza poz curenta a robotului
    private int col;
    private Random random; // pt a genera nr aleatoare
    private int tokensPlaced; // nr de tokens plasate de robot pe harta
    private int direction;
    Exploration explore;// un obiect de tip "Exploration", utilizat pt a efectua explorarea hartii de  robot
    public Robot(String name,int row, int col,Exploration explore) {
        this.name = name;
        this.running = true;
        this.paused = false;
        this.random = new Random();
        this.explore = explore;
        direction = 0;
        this.row = row;
        this.col = col;
        tokensPlaced = 0;
        explore.getMap().visit(row, col, this); // marcheaza cel unde e robotul ca vizitata

    }
    public void setExploration(Exploration explore) {
        this.explore = explore;
    }

    public String getName() {
        return name;
    }

    public Exploration getExplore() {
        return explore;
    }
    public List<Token> extractTokens(){
        return explore.getMem().extractTokens(explore.getMapSize());
    } //extrage nr de tokens egal cu dim hartii
    public void addToken(int number){
        tokensPlaced += number;
    }
    public int getTokensPlaced(){
        return tokensPlaced;
    } // nr de tokens plasate de robot pe harta
    public void stopRunning() {
        this.running = false;
    }
    public void pauseRobot(){
        this.paused = true;
        synchronized (this){
            notify();// notifica firul de exec al robotului ca a fost pus pe pauza
        }
    }

    /**
     * Metoda pune un robot pe pauza pentru o durata definita de timp
     * @param durationMillis durata pauzei
     */
    public void pauseRobotForDuration(long durationMillis) {
        this.paused = true;
        if (durationMillis > 0) {
            new Thread(() -> {
                //init un fir de exec separat care asteapta o anumita durata de timp in milisec si apoi reia exec
                try {
                    Thread.sleep(durationMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resumeRobot();
            }).start();
        }

    }

    /**
     * Metoda semnaleaza ca un robot nu mai este pe pauza
     */
    public void resumeRobot(){
        this.paused = false;
        synchronized (this){
            notify();
        }
    }

    /**
     * Metoda muta robotul cu valori de -1, 0, 1 intr-un mod sistematic, ceea ce poate insemna un pas in spate, sa ramana pe loc sau sa mearga in fata(difera de la linie la coloana)
     */
    private void move() {
        int n = explore.getMapSize();
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[direction]; // calc poz noii celule pe care se va poz robotul
            int newCol = col + dc[direction];

            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !explore.getMap().isVisited(newRow, newCol)) {// noua cel este in int hartii si nu a fost viz inca, actualizez
                row = newRow;
                col = newCol;
                return;
            } else {
                direction = (direction + 1) % 4;//se roteste directia robotului la stanga, folosind modulo pt a asigura rotatia circulara in toate directiile
            }
        }
    }


    /**
     * Metoda verifica daca procesul este functional si daca nu este pus pe pauza, muta robotii si exploreaza harta marcand celulele vizitate, fiecare robot are un thread
     */
    @Override
    public void run() {
        while (running) {
            if (!paused) {
                move();
                try {
                    Thread.sleep(5000); // o intarziere de 5 sec, ininate ca rob sa continue
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                explore.getMap().visit(row, col, this);// marchez cel curenta ca fiind viz

                if (explore.getMap().allCellsVisited()) {// verif daca toate cel au fost viz
                    {
                        System.out.println("Terminat");
                        running = false;
                    }
                }
                try {
                    Thread.sleep(random.nextInt(1000) + 500);//introduc o intarziere aleatoare intre 0,5 si 1,5 sec inainte ca rob sa inceapa urm iteratie
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                synchronized (this){
                    try {
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
