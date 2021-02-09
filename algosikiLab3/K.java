import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class K {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Writer wr = new PrintWriter(System.out);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        Pair[] dp = new Pair[1 << n];
        dp[0] = new Pair(1, 0);
        for (int i = 1; i < 1 << n; i++) {
            dp[i] = new Pair(Integer.MAX_VALUE, 0);
        }
        for (int x = 0; x < 1 << n; x++) {
            for (int i = 0; i < n; i++) {
                if ((x & (1 << i)) == 0) {
                    int count;
                    int weight;
                    if (w[i] + dp[x].weight <= s) {
                        count = dp[x].count;
                        weight = dp[x].weight + w[i];
                    } else {
                        count = dp[x].count + 1;
                        weight = w[i];
                    }
                    if (dp[x + (1 << i)].count > count) {
                        dp[x + (1 << i)] = new Pair(count, weight);
                        dp[x + (1 << i)].parent = x;
                    } else if (dp[x + (1 << i)].count == count && dp[x + (1 << i)].weight > weight) {
                        dp[x + (1 << i)] = new Pair(count, weight);
                        dp[x + (1 << i)].parent = x;
                    }
                }
            }
        }
        ArrayList<Integer> way = new ArrayList<>();
        int[] bags = new int[dp[(1 << n) - 1].count + 2];
        int[] sumbags = new int[dp[(1 << n) - 1].count + 2];
        int curbag = 1;
        int current = -1 + (1 << n);
        way.add(current);
        while (current != 0) {
            way.add(dp[current].parent);
            current = dp[current].parent;
        }
        for (int i = way.size() - 2; i >= 0; i--) {
            if (dp[way.get(i)].count > (way.get(i + 1) == 0 ? 0 : dp[way.get(i + 1)].count)) {
                bags[curbag] = way.get(i + 1) - sumbags[curbag - 1];
                sumbags[curbag] = way.get(i + 1);
                curbag++;
            } else if (dp[way.get(i)].count == dp[way.get(i + 1)].count) {
                bags[curbag] = way.get(i + 1) - sumbags[curbag - 1];
                sumbags[curbag] = way.get(i + 1);
            }
        }
        if (dp[way.get(0)].count >= dp[way.get(1)].count) {
            bags[curbag] = way.get(0) - sumbags[curbag - 1];
        }
        if (dp[way.get(0)].count == 1) {
            bags[curbag] = (1 << n) - 1;
        }
        wr.write(Integer.toString(dp[(1 << n) - 1].count));
        wr.write('\n');
        for (int i = 2; i < bags.length; i++) {
            wr.write(Integer.toString(Integer.bitCount(bags[i])));
            for (int j = 0; j < n; j++) {
                if ((bags[i] & (1 << j)) != 0) {
                    wr.write(" " + (j + 1));
                }
            }
            wr.write('\n');
        }
        wr.close();
    }
}

class Pair {
    int count;
    int weight;
    int parent = 0;

    public Pair(int c, int w) {
        weight = w;
        count = c;
    }
}
