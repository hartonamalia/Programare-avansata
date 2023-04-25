package org.example;

import java.util.Arrays;
import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;//matrice de celule

    public ExplorationMap(int n) {
        //Constr clasei primeste un param @n care rep dim hartii si init matricea
        matrix = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = new Cell();// pt fiecare celula din matrice se creeaza un nou obiect "Cell"
            }
        }
    }

    /**
     * Metoda simuleaza miscarea unui robot verificand daca celula nu este cumva vizitata, robotul colecteaza tokens si este afisat un mesaj pe ecran
     * @param row
     * @param col
     * @param robot
     * @return
     */
    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if (!matrix[row][col].isVisited()) {
                List<Token> tokens = robot.extractTokens();
                matrix[row][col].setTokens(tokens);
                robot.addToken(tokens.size());
                System.out.printf("%s visited (%d, %d)%n", robot.getName(), row, col);
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return matrix.length;
    }

    /**
     * Metoda verifica daca toate celulele au fost vizitate
     * @return
     */
    public boolean allCellsVisited() {
        // Metoda verifica daca toate celulele au fost vizitate
        for (Cell[] cells : matrix) {
            for (Cell cell : cells) {
                if (!cell.isVisited()) { // daca gaseste o celula care a fost vizitata
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }

    //verif daca o celulă din matricea "matrix" a fost viz sau nu, bazandu-se pe coord rand si col date ca param
    public boolean isVisited(int r, int c) {
        return matrix[r][c].isVisited();
    }
}
