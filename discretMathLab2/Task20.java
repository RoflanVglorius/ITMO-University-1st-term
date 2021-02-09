import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Task20 {
    static Scanner s;
    static Writer w;
    static long[][] dp;
    static StringBuilder str;
    static long no = 0;


    public static void generate(int n, int balance) throws IOException {
        ArrayDeque<Character> toClose = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') {
                toClose.push('(');
                balance++;
                continue;
            }
            no += balance < n / 2 ? dp[n - 1 - i][balance + 1] * Math.pow(2, (n - 1 - i - balance - 1) / 2) : 0;
            if (str.charAt(i) == ')' && toClose.getFirst() == '(' && balance > 0) {
                balance--;
                toClose.pop();
                continue;
            }
            no += balance > 0 && toClose.getFirst() == '(' && str.charAt(i) != ')' ? dp[n - 1 - i][balance - 1] * Math.pow(2, (n - 1 - i - balance + 1) / 2) : 0;
            if (str.charAt(i) == '[') {
                balance++;
                toClose.push('[');
                continue;
            }
            no += balance < n / 2 ? dp[n - i - 1][balance + 1] * Math.pow(2, (n - 1 - i - balance - 1) / 2) : 0;
            balance--;
            toClose.pop();
        }
        w.write(Long.toString(no));
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("brackets2num2.in"));
        w = new BufferedWriter(new FileWriter("brackets2num2.out"));
//        s = new Scanner(new FileInputStream("input.txt"));
//        w = new BufferedWriter(new FileWriter("output.txt"));
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
