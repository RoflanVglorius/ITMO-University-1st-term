import java.util.Random;
import java.util.Scanner;

public class Simple { //Наивная реализация
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n = 10000;
        int m = sc.nextInt();
        byte[][] input = new byte[n][m];
        byte[][] result = new byte[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                input[i][j] = (byte) random.nextInt(9);
            }
        }
        long l = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][i] = input[i][j];
            }
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
