/*
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner();
        Writer w = new PrintWriter(System.out);
        int n = s.nextInt();
        int[] a = new int[n];
        int[] seq = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.nextInt();
        for (int i = 0; i < n; i++) {
            seq[i] = 1;
            for (int j = 0; j < i; j++) {
                if (seq[j] + 1 > seq[i] && a[j] < a[i])
                    dp[i] = j;
                if (a[j] < a[i])
                    seq[i] = Math.max(seq[j] + 1, seq[i]);
            }
        }
        int max = Integer.MIN_VALUE;
        int ind = 1;
        for (int i = 0; i < n; i++) {
            if (max < seq[i]) {
                max = seq[i];
                ind = i;
            }
        }
        ArrayList<Integer> way = new ArrayList<>();
        int cur = ind;
        while (seq[cur] != 1) {
            way.add(a[cur]);
            cur = dp[cur];
        }
        way.add(a[cur]);
        w.write(Integer.toString(max) + '\n');
        for (int i = way.size() - 1; i >= 0; i--) {
            w.write(way.get(i) + " ");
        }
        w.close();
    }
}

class Scanner {
    public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public StringTokenizer token;

    public Scanner() throws IOException {
        token = new StringTokenizer(reader.readLine());
    }

    public int nextInt() throws IOException {
        while (!token.hasMoreTokens()) {
            token = new StringTokenizer(reader.readLine());
        }
        return Integer.parseInt(token.nextToken());
    }
}*/
