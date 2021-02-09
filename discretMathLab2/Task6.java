import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task6 {
    static Scanner s;
    static Writer w;
    static int n;
    static int[] a;
    static int count = 0;
    static ArrayList<int[]> arrayList = new ArrayList<>();

    static void generate(int len) {
        if (n == 0) {
            return;
        }
        if (len + 1 == n) {
            arrayList.add(Arrays.copyOf(a, a.length));
            count++;
            return;
        }
        a[len + 1] = 0;
        generate(len + 1);
        if (len == -1 || a[len] != 1) {
            a[len + 1] = 1;
            generate(len + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("vectors.in"));
        w = new BufferedWriter(new FileWriter("vectors.out"));
        n = s.nextInt();
        a = new int[n];
        generate(-1);
        w.write(Integer.toString(count) + '\n');
        for (int[] ar : arrayList) {
            for (int num : ar) {
                w.write(Integer.toString(num));
            }
            w.write('\n');
        }
        w.close();
    }
}
