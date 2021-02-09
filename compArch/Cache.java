import java.util.Random;
import java.util.Scanner;

public class Cache { //Кэш-эффективный алгоритм

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Random random = new Random();
        byte[][] input = new byte[n][m];
        byte[][] result = new byte[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                input[i][j] = (byte) random.nextInt(9);
            }
        }
        long l = System.currentTimeMillis();
        for (int i = 0; i < n; i += 32) { //проходим по матрице, транспонируя её блоками 32 на 32
            for (int j = 0; j < m; j += 32) {
                for (int k = i; k < Math.min(n, i + 32); k++) {
                    for (int s = j; s < Math.min(m, j + 32); s++) {
                        result[s][k] = input[k][s];
                    }
                }
            }
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
 /*   static int[][] input;
    static int[][] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Random random = new Random();
        input = new int[n][m];
        result = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                input[i][j] = random.nextInt(9);
            }
        }
        long l = System.currentTimeMillis();
        transpose(0, 0, n, m);
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void transpose(int rowInd, int colInd, int height, int width) {
        if (height == 1 && width == 1) {
            result[colInd][rowInd] = input[rowInd][colInd];
        } else if (height >= width) {
            int separate = height / 2;
            transpose(rowInd, colInd, separate, width);
            transpose(rowInd + separate, colInd, height - separate, width);
        } else {
            int separate = width / 2;
            transpose(rowInd, colInd, height, separate);
            transpose(rowInd, colInd + separate, height, width - separate);
        }
    }
}


*/