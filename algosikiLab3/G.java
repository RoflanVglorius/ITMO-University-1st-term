import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class G {
    static char[] str;
    static Map<Character, Character> comp;
    static int[][] part;
    static int[][] dp;
    static Writer w;

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        w = new PrintWriter(System.out);
        comp = new HashMap<>(Map.of('(', ')', '[', ']', '{', '}',
                ')', '(', ']', '[', '}', '{'));
        str = s.nextLine().toCharArray();
        part = new int[str.length][str.length];
        dp = new int[str.length][str.length];
        for (int i = 0; i < str.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < str.length; j++) {
                int min = Integer.MAX_VALUE;
                int index = Integer.MIN_VALUE;
                if (check(i, j))
                    min = dp[i + 1][j - 1];
                for (int k = j; k > i; k--) {
                    if (dp[i][k - 1] + dp[k][j] <= min) {
                        min = dp[i][k - 1] + dp[k][j];
                        index = k;
                    }
                }
                dp[i][j] = min;
                part[i][j] = index;
            }
        }
        findRight(0, str.length - 1);
        w.close();
    }

    private static boolean check(int i, int j) {
        return str[j] == ')' && str[i] == '(' || str[j] == '}' && str[i] == '{' || str[j] == ']' && str[i] == '[';
    }

    private static void findRight(int left, int right) throws IOException {
        if (right - left + 1 == dp[left][right]) {
            return;
        }
        if (dp[left][right] == 0) {
            for (int i = left; i <= right; i++) {
                w.write(str[i]);
            }
            return;
        }
        if (part[left][right] < 0) {
            w.write(str[left]);
            findRight(left + 1, right - 1);
            w.write(str[right]);
            return;
        }
        findRight(left, part[left][right] - 1);
        findRight(part[left][right], right);
    }
}
