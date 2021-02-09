import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] trails = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] buffer = scanner.next().toCharArray();
            for (int j = 0; j < n; j++) {
                trails[i][j] = Character.getNumericValue(buffer[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < n; j++) {
                for (int k = i + 1; k < n; k++) {
                    if (trails[i][k] != 0) {
                        trails[i][j] = (trails[i][j] - trails[k][j] + 10) % 10;
                    }
                }
                result.append(trails[i][j]);
            }
            System.out.println(result.toString());
        }
    }
}
