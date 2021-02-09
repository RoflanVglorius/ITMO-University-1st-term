import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Task15 {
    static Writer w;
    static Scanner r;
    static int n;
    static int k;
    static int[] array;
    static BigInteger[] fact;
    static int length = 0;

    static void write(int[] array) throws IOException {
        for (int i : array) {
            w.write(Integer.toString(i));
            w.write(' ');
        }
        w.write('\n');
    }

    static void generate(int n, int k, long index, int symbol) throws IOException {
        while (k > 0) {
            long buff = fact[n - 1].divide(fact[n - k].multiply(fact[k - 1])).longValue();
            if (index >= buff) {
                index -= buff;
                symbol++;
            } else {
                array[length++] = symbol++;
                k--;
            }
            n--;
        }
        write(array);
    }

    public static void countFact(int n) {
        fact = new BigInteger[n + 1];
        fact[0] = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }
    }

    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("num2choose.in"));
        w = new BufferedWriter(new FileWriter("num2choose.out"));
        int n = r.nextInt();
        int k = r.nextInt();
        array = new int[k];
        countFact(n);
        generate(n, k, r.nextLong(), 1);
        w.close();
    }
}
