import java.io.*;
import java.util.Scanner;

public class Task11 {
    static Writer w;
    static Scanner r;
    static int n;
    static boolean[] used;
    static int[] perm;

    static void writeArray(int[] array, int len) throws IOException {
        int j = 0;
        for (int i : array) {
            j++;
            w.write(Integer.toString(i));
            w.write(" ");
            if (j == len) {
                break;
            }
        }
        w.write('\n');
    }

    static void generate(int len) throws IOException {
        if (len == 0){
            w.write('\n');
        }
        if (len >= 1) {
            writeArray(perm, len);
            if (len == n) {
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (len == 0 || !used[i] && i + 1 >= perm[len - 1]) {
                used[i] = true;
                perm[len] = i + 1;
                generate(len + 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("subsets.in"));
        w = new BufferedWriter(new FileWriter("subsets.out"));
        n = r.nextInt();
        perm = new int[n];
        used = new boolean[n];
        generate(0);
        w.close();
    }
}
