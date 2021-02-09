import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Task16 {
    static Writer w;
    static Scanner r;
    static int[] array;
    static BigInteger[] fact;

    static void generate(int n, int k) throws IOException {
        int prev = 0;
        int cur = 0;
        long no = 0;
        while (k > 0) {
            for (int i = prev + 1; i < array[cur]; i++) {
                no += fact[n - i].divide(fact[n - i + 1 - k].multiply(fact[k - 1])).longValue();
            }
            prev = array[cur];
            cur++;
            k--;
        }
        w.write(Long.toString(no));
    }

    public static void countFact(int n) {
        fact = new BigInteger[n + 1];
        fact[0] = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }
    }

    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("input.txt"));
        w = new BufferedWriter(new FileWriter("output.txt"));
        int n = r.nextInt();
        int k = r.nextInt();
        array = new int[k];
        int index = 0;
        while (r.hasNextInt()) {
            array[index] = r.nextInt();
            index++;
        }
        countFact(n);
        generate(n, k);
        w.close();
    }
}