import java.util.Arrays;
import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = s.nextInt();
            }
        }
        int[][] way = new int[1 << n][n];
        int[][] par = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                if ((i & (1 << (n - j - 1))) != 0) {
                    for (int k = 0; k < n; k++) {
                        if (way[i - (1 << (n - j - 1))][k] + cost[j][k] < min && k != j && (((i - (1 << (n - j - 1))) & (1 << (n - k - 1))) != 0)) {
                            min = way[i - (1 << (n - j - 1))][k] + cost[j][k];
                            par[i][j] = k;
                        }
                    }
                    if (min != Integer.MAX_VALUE)
                        way[i][j] = min;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minInd = 0;
        for (int i = 0; i < n; i++) {
            if (way[(1 << n) - 1][i] <= min) {
                min = way[(1 << n) - 1][i];
                minInd = i;
            }
        }
        int cur = (1 << n) - 1;
        System.out.println(way[(1 << n) - 1][minInd]);
        System.out.print(minInd + 1);
        while (cur != (1 << (n - minInd - 1))) {
            int buff = cur - (1 << (n - minInd - 1));
            minInd = par[cur][minInd];
            cur = buff;
            System.out.print(" " + (minInd + 1));
        }
    }
}
