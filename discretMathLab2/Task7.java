import java.io.*;
import java.util.Scanner;

public class Task7 {
    static Writer w;
    static Scanner r;
    static int n;
    static boolean[] used;
    static int[] perm;

    static void writeArray(int[] array) throws IOException{
        for (int i : array){
            w.write(Integer.toString(i));
            w.write(" ");
        }
        w.write('\n');
    }
    static void generate(int len) throws IOException {
        if (len == n) {
            writeArray(perm);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[len] = i + 1;
                generate(len + 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("permutations.in"));
        w = new BufferedWriter(new FileWriter("permutations.out"));
        n = r.nextInt();
        perm = new int[n];
        used = new boolean[n];
        generate(0);
        w.close();
    }
}
