import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Task8 {
    static Writer w;
    static Scanner r;
    static int n;
    static int k;
    static int[] array;

    static void write(int[] array) throws IOException {
        for (int i : array) {
            w.write(Integer.toString(i));
            w.write(' ');
        }
        w.write('\n');
    }

    static void generate(int len, int prev) throws IOException {
        if (len == k) {
            write(array);
            return;
        }
        for (int i = prev + 1; i <= n - k + 1 + len; i++) {
            array[len] = i;
            generate(len + 1, i);
        }
    }


    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("input.txt"));
        w = new BufferedWriter(new FileWriter("output.txt"));
        n = r.nextInt();
        k = r.nextInt();
        array = new int[k];
        for (int i = 1; i <= n - k + 1; i++) {
            array[0] = i;
            generate(1, i);
        }
        w.close();
    }
}
