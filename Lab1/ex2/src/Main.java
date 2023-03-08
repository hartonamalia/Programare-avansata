public class Main {
    public static void main(String[] args) {
        int n = validareNumar(args);
        int [][] matrice = creareMatrice(n);
        if(n<30000)
        {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                StringBuilder linie = new StringBuilder();
                StringBuilder coloana = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    linie.append(matrice[i][j]);
                    coloana.append(matrice[j][i]);

                }
                System.out.println(linie);
                System.out.println(coloana);
            }
            long t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
        }
        else
        {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                StringBuilder linie = new StringBuilder();
                StringBuilder coloana = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    linie.append(matrice[i][j]);
                    coloana.append(matrice[j][i]);
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
        }
    }
    public  static int validareNumar(String[] args)
    {
        if (args.length == 0) {
            System.out.println("Nu ati introdus niciun numar n.");
            System.exit(1);
        }

        int n = -1;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Argumentul introdus nu este un int: " + args[0]);
            System.exit(1);
        }
        if(n<0)
        {
            System.out.println("Argumentul introdus nu poate fi negativ: " + args[0]);
            System.exit(1);
        }
        return n;
    }
    public static int[][] creareMatrice(int n)
    {
        int[][] matrice = new int[n][n];
        for (int i = 0; i < n; i++)
            matrice[0][i] = (i + 1);
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n; j++) {
                matrice[i][j] = matrice[i - 1][(j + 1) % n];
            }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.printf("%d ", matrice[i][j]);
            System.out.println();
        }

        return matrice;
    }
}