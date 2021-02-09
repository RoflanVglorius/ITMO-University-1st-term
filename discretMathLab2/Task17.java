import java.io.*;
import java.util.Scanner;

public class Task17 {
    static Scanner s;
    static Writer w;
    static long[][] dp;

    public static void generate(int n, long k, int balance) throws IOException {
        StringBuilder str = new StringBuilder();
        for (int i = 2 * n - 1; i >= 0; i--) {
            if (dp[i][balance + 1] < k) {
                k -= dp[i][balance + 1];
                str.append(')');
                balance--;
            } else {
                str.append('(');
                balance++;
            }
        }
        w.write(str.toString());
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("num2brackets.in"));
        w = new BufferedWriter(new FileWriter("num2brackets.out"));
        int n = s.nextInt();
        long k = s.nextLong();
        dp = new long[2 * n + 1][2 * n + 1];
        dp[0][0] = 1;
        for (int i = 1; i < 2 * n + 1; i++) {
            for (int j = 0; j < 2 * n + 1; j++) {
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j < 2 * n) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
            }
        }
        generate(n, k + 1, 0);
        w.close();
    }
}
