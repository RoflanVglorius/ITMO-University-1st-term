import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class I {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        if(n == 1 && m == 1){
            System.out.println(0);
            return;
        }
        char[][] input = new char[n][m];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextLine().toCharArray();
        }
        long[][] dp = new long[m + 1][1 << n];
        int[] mask = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                if (input[j][i - 1] == 'X') {
                    mask[i] += 1 << j;
                }
            }
        }
        dp[0][0] = 1;
        for (int c = 0; c < m; c++) {
            for (int i = 0; i < (1 << n); i++) {
                if ((mask[c] & i) != mask[c]) {
                    continue;
                }
                for (int j = 0; j < (1 << n); j++) {
                    if (c == m - 1 && j != 0) {
                        continue;
                    }
                    int z = i - mask[c];
                    int y = j + mask[c + 1];
                    if ((mask[c + 1] & j) != 0 || (z & y) != 0) {
                        continue;
                    }
                    int buffer = ((1 << n) - (z | y) - 1) % 3 == 0 ? ((1 << n) - (z | y) - 1) / 3 : -1;
                    if (buffer == -1 || (buffer & (buffer << 1)) != 0) {
                        continue;
                    }
                    dp[c + 1][y] += dp[c][i];
                }
            }
        }
        System.out.println(Long.toString(dp[m][mask[m]]));
    }
}
