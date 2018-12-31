import java.util.Scanner;

class Matrix {
    int row;
    int col;
    Matrix(int r, int c) {
        row = r;
        col = c;
    }
}

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);

        Matrix[] matrices = readMatrices(scanner);
        int[][] memo = init2D(matrices.length);
        int[][] path = init2D(matrices.length);
        int result = mcm(0, matrices.length - 1, matrices, memo, path);
        System.out.println("Minimum cost is: " + result);
        findBestOrder(0, matrices.length - 1, path);
        System.out.println();
    }

    static int[][] init2D(int n) {
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return memo;
    }

    static Matrix[] readMatrices(Scanner scanner) {
        int n = scanner.nextInt();
        Matrix[] matrices = new Matrix[n];

        for (int i =0; i < n; i++) {
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            matrices[i] = new Matrix(r, c);
        }
        return matrices;
    }

    static void findBestOrder(int i, int j, int[][] path) {
        if (i == j) {
            System.out.print("A" + i);
            return;
        }

        System.out.print("(");
        int k = path[i][j];
        findBestOrder(i, k, path);  // print left side
        System.out.print(" x ");
        findBestOrder(k + 1, j, path);  // print right side
        System.out.print(")");
    }

    static int mcm (int i, int j, Matrix[] matrices, int[][] memo, int[][] path) {
        if (i == j) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int best = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
            int c = mcm(i, k, matrices, memo, path) + mcm(k + 1, j, matrices, memo, path)
                    + matrices[i].row * matrices[k].col * matrices[j].col;
            if (c < best) {
                best = c;
                path[i][j] = k;
            }
        }
        memo[i][j] = best;
        return best;
    }


}
