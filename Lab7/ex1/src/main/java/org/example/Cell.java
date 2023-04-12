package org.example;

import java.util.ArrayList;
import java.util.List;


// celula din harta de explorare ca sa nu revina la aceleasi celule deja explorate
public class Cell {
    private List<Token> tokens; // elem pe care robotii le pot extrage dinc elula
    private boolean visited; //celula a fost vizitata sau nu

    public Cell() {
        tokens = new ArrayList<>(); // init lista cu o lista goala
        visited = false; // celula nu a fost inca vizitata
    }

    public boolean isVisited() {
        return visited;
    }

    /**
     * Fiecare celula reprezinta o lista de tokens
     * @param tokens
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
        this.visited = true; // celula a fost vizitata si contine aceste token-uri
    }

}