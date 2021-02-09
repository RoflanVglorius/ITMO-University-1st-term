import java.io.*;
import java.util.Scanner;

public class Task14 {
    static Scanner s;
    static Writer w;
    static boolean[] used;
    static int[] perm;
    static int n;
    static long[] fact;

    static void generate(int[] perm) throws IOException {
        long no = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < perm[i] - 1; j++) {
                if (!used[j]) {
                    no += fact[n - i - 1];
                }
            }
            used[perm[i] - 1] = true;
        }
        w.write(Long.toString(no));
        w.close();
    }

    public static void countFact(int n) {
        fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("input.txt"));
        w = new BufferedWriter(new FileWriter("output.txt"));
        n = s.nextInt();
        used = new boolean[n];
        int[] perm = new int[n];
        int i = 0;
        while (s.hasNextInt()) {
            perm[i] = s.nextInt();
            i++;
        }
        countFact(n);
        generate(perm);
    }
}
