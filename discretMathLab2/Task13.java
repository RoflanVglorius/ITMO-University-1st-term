import java.io.*;
import java.util.Scanner;

public class Task13 {
    static Scanner s;
    static Writer w;
    static boolean[] used;
    static int[] perm;
    static int n;
    static long[] fact;

    static void generate(long k) throws IOException {
        for (int i = 0; i < n; i++) {
            long toSkip = k / fact[n - i - 1];
            k %= fact[n - i - 1];
            int index = 0;
            for (int j = 0; j < n; j++) {
                if (!used[j]) {
                    index++;
                    if (index > toSkip) {
                        perm[i] = j + 1;
                        used[j] = true;
                        break;
                    }
                }
            }
        }
        writeArray(perm);
        w.close();
    }

    static void writeArray(int[] array) throws IOException {
        for (int i : array) {
            w.write(Integer.toString(i));
            w.write(" ");
        }
        w.write('\n');
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
        long k = s.nextLong();
        used = new boolean[n];
        perm = new int[n];
        countFact(n);
        generate(k);
    }
}
