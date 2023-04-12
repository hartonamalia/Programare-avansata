package org.example;

import java.util.*;

public class SharedMemory {
    private final Queue<Token> tokens; // colectia de token

    public SharedMemory(int n)
    {
        // adaug tokens la colectie si le dau  shuffle

        tokens = new LinkedList<>();
        for(int i = 1; i <= n*n*n;i++){
            tokens.add(new Token(i));
        }
        Collections.shuffle((List<?>) tokens);

    }

    /*
     * Metoda extrage din lista de tokens
     * @param nr
     * @return
     */
    public synchronized  List<Token> extractTokens(int nr) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < nr; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.poll()); // extrage primul elem din coada
        }
        return extracted;
    }
}












