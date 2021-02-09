import java.io.*;
import java.util.Scanner;

public class Task9 {
    static Scanner s;
    static Writer w;
    static int n;

    static void generate(int balance, String str) throws IOException {
        if (str.length() == 2 * n) {
            w.write(str);
            w.write('\n');
            return;
        }
        if (balance + 1 < 2 * n - str.length()) {
            generate(balance + 1, str + '(');
        }
        if (balance > 0) {
            generate(balance - 1, str + ')');
        }
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("brackets.in"));
        w = new BufferedWriter(new FileWriter("brackets.out"));
        n = s.nextInt();
        generate(0, "");
        w.close();
    }
}
