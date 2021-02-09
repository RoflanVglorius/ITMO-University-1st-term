import java.io.*;
import java.util.Scanner;

public class Task18 {
    static Scanner s;
    static Writer w;
    static long[][] dp;
    static StringBuilder str;
    static long no = 0;


    public static void generate(int n, int balance) throws IOException {
        for (int i = n - 1; i >= 0; i--) {
            if (str.charAt(n - 1 - i) == ')') {
                no += dp[i][balance + 1];
                balance--;
            } else {
                balance++;
            }
        }
        w.write(Long.toString(no));
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("brackets2num.in"));
        w = new BufferedWriter(new FileWriter("brackets2num.out"));
        str = new StringBuilder(s.nextLine());
        dp = new long[str.length() + 1][str.length() + 1];
        dp[0][0] = 1;
        for (int i = 1; i < str.length() + 1; i++) {
            for (int j = 0; j < str.length() + 1; j++) {
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j < str.length()) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
            }
        }
        generate(str.length(), 0);
        w.close();
    }
}
