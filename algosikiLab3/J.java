import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        if (n > m) {
            int buff = n;
            n = m;
            m = buff;
        }
        long[][] dp = new long[1 << n][m];
        for (int i = 0; i < 1 << n; i++) {
            dp[i][0] = 1;
        }

        for (int col = 1; col < m; col++) {
            for (int i = 0; i < 1 << n; i++) {
                a:
                for (int j = 0; j < 1 << n; j++) {
                    StringBuilder buff = new StringBuilder(Integer.toBinaryString(i & j));
                    StringBuilder buff2 = new StringBuilder(Integer.toBinaryString(i | j));
                    if (buff2.length() < n - 1 || buff2.length() < n && buff2.charAt(0) == '0') {
                        continue;
                    }
                    int count0 = 0;
                    int count1 = 0;
                    for (char c : buff.toString().toCharArray()) {
                        if (c == '1') {
                            count1++;
                        } else {
                            count1 = 0;
                        }
                        if (count1 > 1) {
                            continue a;
                        }
                    }
                    for (char c : buff2.toString().toCharArray()) {
                        if (c == '0') {
                            count0++;
                        } else {
                            count0 = 0;
                        }
                        if (count0 > 1) {
                            continue a;
                        }
                    }
                    dp[i][col] += dp[j][col - 1];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 1 << n; i++) {
            res += dp[i][m - 1];
        }
        System.out.println(res);
    }
}
