import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long[][] d = new long[n][10];
        for (int i = 0; i < 10; i++) {
            if (i == 8 || i == 0) {
                d[0][i] = 0;
            } else {
                d[0][i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            d[i][0] += (d[i - 1][4] + d[i - 1][6]) % 1000000000;
            d[i][1] += (d[i - 1][8] + d[i - 1][6]) % 1000000000;
            d[i][2] += (d[i - 1][7] + d[i - 1][9]) % 1000000000;
            d[i][3] += (d[i - 1][4] + d[i - 1][8]) % 1000000000;
            d[i][4] += (d[i - 1][3] + d[i - 1][9] + d[i - 1][0]) % 1000000000;
            d[i][6] += (d[i - 1][1] + d[i - 1][7] + d[i - 1][0]) % 1000000000;
            d[i][7] += (d[i - 1][2] + d[i - 1][6]) % 1000000000;
            d[i][8] += (d[i - 1][1] + d[i - 1][3]) % 1000000000;
            d[i][9] += (d[i - 1][4] + d[i - 1][2]) % 1000000000;
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += d[n-1][i];
            sum = sum  % 1000000000;
        }
        System.out.println(sum % 1000000000);
    }
}
