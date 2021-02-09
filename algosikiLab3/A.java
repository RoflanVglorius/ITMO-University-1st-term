/*
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner();
        Writer w = new PrintWriter(System.out);
        int n = s.nextInt();
        int k = s.nextInt();
        int[] ways = new int[n];
        int[] prev = new int[n];
        for (int i = 1; i < n - 1; i++) {
            ways[i] = s.nextInt();
        }
        for (int i = 1; i < n; i++) {
            int result = Integer.MIN_VALUE;
            for (int j = Math.max(i - k, 0); j < i; j++) {
                if (ways[j] + ways[i] > result) {
                    result = ways[j] + ways[i];
                    prev[i] = j;
                }
            }
            ways[i] = result;
        }
        ArrayList<Integer> way = new ArrayList<>();
        int cur = prev[n - 1];
        way.add(n);
        while (cur != 0) {
            way.add(cur + 1);
            cur = prev[cur];
        }
        way.add(1);
        w.write(Integer.toString(ways[n - 1]));
        w.write('\n');
        w.write(Integer.toString(way.size() - 1));
        w.write('\n');
        for (int i = way.size() - 1; i >= 0; i--){
            w.write(Integer.toString(way.get(i)) + ' ');
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
