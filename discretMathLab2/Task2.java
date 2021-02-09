import java.io.*;
import java.util.Scanner;

public class Task2 {
    static Writer w;
    static Scanner r;

    public static void main(String[] args) throws IOException {
        w = new BufferedWriter(new FileWriter("gray.out"));
        r = new Scanner(new FileInputStream("gray.in"));
        int n = r.nextInt();
        for (int i = 0; i < 1 << n; i++) {
            StringBuilder buff = new StringBuilder(Integer.toBinaryString(i ^ (i / 2)));
            while (buff.length() < n) {
                buff.insert(0, 0);
            }
            w.write(buff.toString());
            w.write('\n');
        }
        w.close();
        r.close();
    }
}
