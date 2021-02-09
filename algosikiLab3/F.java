import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class F  {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if (n == 0) {
            System.out.println(0);
            System.out.println(0 + " " + 0);
            return;
        }
        if (n == 1) {
            int buff = s.nextInt();
            System.out.println(buff);
            System.out.println(buff <= 100 ? "0 0" : "1 0");
            return;
        }
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = s.nextInt();
        }
        int[][] d = new int[n + 1][n + 1];
        d[0][0] = 0;
        for (int[] ar : d) {
            Arrays.fill(ar, Integer.MIN_VALUE);
        }
        d[0][0] = 0;
        int[][] par = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                if (cost[i] > 100) {
                    if (j > 0) {
                        if ((d[j - 1][i - 1] + cost[i] > d[j + 1][i - 1] || d[j - 1][i - 1] < 0) && d[j + 1][i - 1] >= 0) {
                            par[j][i] = j + 1;
                            d[j][i] = d[j + 1][i - 1];
                        } else if (d[j - 1][i - 1] >= 0) {
                            par[j][i] = j - 1;
                            d[j][i] = d[j - 1][i - 1] + cost[i];
                        }
                    } else if (d[j + 1][i - 1] >= 0) {
                        par[j][i] = j + 1;
                        d[j][i] = d[j + 1][i - 1];
                    }
                } else {
                    if ((d[j][i - 1] + cost[i] > d[j + 1][i - 1] || d[j][i - 1] < 0) && d[j + 1][i - 1] >= 0) {
                        par[j][i] = j + 1;
                        d[j][i] = d[j + 1][i - 1];
                    } else if (d[j][i - 1] >= 0) {
                        par[j][i] = j;
                        d[j][i] = d[j][i - 1] + cost[i];
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minInd = -1;
        for (int i = 0; i <= n; i++) {
            if (d[i][n] <= min && d[i][n] > 0) {
                min = d[i][n];
                minInd = i;
            }
        }
        System.out.println(min);
        int count = 0;
        int cur = minInd;
        ArrayList<Integer> way = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            if (par[cur][i] > cur) {
                way.add(i);
                count++;
            }
            cur = par[cur][i];
        }
        System.out.println(minInd + " " + count);
        for (int i = way.size() - 1; i >= 0; i--) {
            System.out.println(Integer.toString(way.get(i)));
        }
    }
}
