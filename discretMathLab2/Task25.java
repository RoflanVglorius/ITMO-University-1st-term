import java.io.*;
import java.util.Scanner;

public class Task25 {
    static Scanner s;
    static Writer w;

    static void write(int[] array) throws IOException {
        for (int i : array) {
            w.write(Integer.toString(i));
            w.write(' ');
        }
        w.write('\n');
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("nextchoose.in"));
        w = new BufferedWriter(new FileWriter("nextchoose.out"));
        int n = s.nextInt();
        int k = s.nextInt();
        int[] c = new int[k];
        for (int i = 0; i < k; i++) {
            c[i] = s.nextInt();
        }
        if (c[k - 1] < n) {
            c[k - 1]++;
            write(c);
            w.close();
            return;
        }
        int ind = -1;
        for (int i = k - 2; i >= 0; i--) {
            if (c[i + 1] - c[i] >= 2) {
                ind = i;
                c[i]++;
                break;
            }
        }
        if (ind == -1){
            w.write("-1");
        } else {
            for (int i = ind + 1; i < k; i++) {
                c[i] = c[i - 1] + 1;
            }
            write(c);
        }
        w.close();
    }
}
