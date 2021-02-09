/*
import java.io.*;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner();
        Writer w = new PrintWriter(System.out);
        int n = s.nextInt();
        int m = s.nextInt();
        int[][] ways = new int[n][m];
        char[][] prev = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ways[i][j] = s.nextInt();
            }
        }
        for (int i = 1; i < n; i++) {
            ways[i][0] += ways[i - 1][0];
            prev[i][0] = 'D';
        }

        for (int j = 1; j < m; j++) {
            ways[0][j] += ways[0][j - 1];
            prev[0][j] = 'R';
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                ways[i][j] += Math.max(ways[i - 1][j], ways[i][j - 1]);
                prev[i][j] = ways[i - 1][j] > ways[i][j - 1] ? 'D' : 'R';
            }
        }
        w.write(Integer.toString(ways[n - 1][m - 1]) + '\n');
        StringBuilder res = new StringBuilder();
        int i = n - 1;
        int j = m - 1;
        while (i != 0 || j != 0) {
            res.append(prev[i][j]);
            if (prev[i][j] == 'D') {
                i--;
            } else {
                j--;
            }
        }
        for (int k = res.length() - 1; k >= 0; k--) {
            w.write(Character.toUpperCase(res.charAt(k)));
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
