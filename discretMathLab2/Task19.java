import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Task19 {
    static Scanner s;
    static Writer w;
    static long[][] dp;

    public static void generate(int n, long k, int balance) throws IOException {
        StringBuilder str = new StringBuilder();
        ArrayDeque<Character> toClose = new ArrayDeque<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            if (balance < n && dp[i][balance + 1] * Math.pow(2, (i - balance - 1) / 2) >= k) {
                toClose.push('(');
                str.append('(');
                balance++;
            } else {
                k -= balance < n ? dp[i][balance + 1] * Math.pow(2, (i - balance - 1) / 2) : 0;
                if (balance > 0 && dp[i][balance - 1] * Math.pow(2, (i - balance + 1) / 2) >= k && toClose.getFirst() == '(') {
                    toClose.pop();
                    str.append(')');
                    balance--;
                } else {
                    k -= balance > 0 && toClose.getFirst() == '(' ? dp[i][balance - 1] * Math.pow(2, (i - balance + 1) / 2) : 0;
                    if (balance < n && dp[i][balance + 1] * Math.pow(2, (i - balance - 1) / 2) >= k) {
                        toClose.push('[');
                        str.append('[');
                        balance++;
                    } else {
                        k -= balance < n ? dp[i][balance + 1] * Math.pow(2, (i - balance - 1) / 2) : 0;
                        toClose.pop();
                        str.append(']');
                        balance--;
                    }
                }
            }
        }
        w.write(str.toString());
    }

    public static void main(String[] args) throws IOException {
  //      s = new Scanner(new FileInputStream("input.txt"));
//        w = new BufferedWriter(new FileWriter("output.txt"));
        s = new Scanner(new FileInputStream("num2brackets2.in"));
      w = new BufferedWriter(new FileWriter("num2brackets2.out"));
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
