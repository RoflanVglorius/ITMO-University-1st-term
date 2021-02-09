import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task22 {
    static long[][] dp;
    static Scanner s;
    static BufferedWriter w;
    static ArrayList<Integer> in;

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("input.txt"));
        w = new BufferedWriter(new FileWriter("output.txt"));
        in = new ArrayList<>();
        int sum = 0;
        StringBuilder str = new StringBuilder();
        for (char c : s.nextLine().toCharArray()) {
            if (c == '+') {
                in.add(Integer.parseInt(str.toString()));
                sum += in.get(in.size() - 1);
                str.setLength(0);
            } else {
                str.append(c);
            }
        }
        in.add(Integer.parseInt(str.toString()));
        sum += in.get(in.size() - 1);
        dp = new long[sum + 1][sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i <= sum; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i][j] = dp[i - j][j] + dp[i][j + 1];
            }
        }
        int curSum = 0;
        int prev = 1;
        long no = 0;
        for (int i = 0; i < in.size(); i++) {
            for (int j = prev; j < in.get(i); j++) {
                no += dp[sum - curSum - j][j];
            }
            prev = in.get(i);
            curSum += prev;
        }
        w.write(Long.toString(no));
        w.close();
    }
}
