import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task21 {
    static long[][] dp;
    static Scanner s;
    static BufferedWriter w;
    static ArrayList<Integer> in;

    public static void main(String[] args) throws IOException {
        //s = new Scanner(new FileInputStream("input.txt"));
        //w = new BufferedWriter(new FileWriter("output.txt"));
        s = new Scanner(new FileInputStream("num2part.in"));
        w = new BufferedWriter(new FileWriter("num2part.out"));
        in = new ArrayList<>();
        int sum = s.nextInt();
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
        long no = s.nextLong();
//        if (no == 0){
//            w.write("1");
//            for (int i = 1; i < sum; i++){
//                w.write("+1");
//            }
//            w.close();
//            return;
//        }
        while (no >= 0 && curSum < sum) {
            for (int i = prev; i <= sum - curSum; i++) {
                if (dp[sum - i - curSum][i] <= no && no > 0 ) {
                    no -= dp[sum - i - curSum][i];
                } else if (sum - curSum - i >= i || sum - curSum == i) {
                    in.add(i);
                    curSum += i;
                    prev = i;
                    break;
                }
            }
        }
        //in.add(sum - curSum);
        w.write(Integer.toString(in.get(0)));
        for (int i = 1; i < in.size(); i++) {
            w.write("+");
            w.write(Integer.toString(in.get(i)));
        }
        w.close();
    }
}
