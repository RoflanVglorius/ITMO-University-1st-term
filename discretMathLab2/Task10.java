import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task10 {

    static Scanner s;
    static Writer w;

    static void generate(int n, ArrayList<Integer> exp) throws IOException {
        if (n == 0) {
            write(exp);
        }
        for (int i = 0; i < n; i++) {
            if (exp.size() == 0 || (i + 1) >= exp.get(exp.size() - 1)) {
                exp.add(i + 1);
                generate(n - i - 1, exp);
                exp.remove(exp.size() - 1);
            }
        }
    }

    public static void write(ArrayList<Integer> exp) throws IOException {
        for (int i = 0; i < exp.size() - 1; i++) {
            w.write(Integer.toString(exp.get(i)));
            w.write("+");
        }
        w.write(Integer.toString(exp.get(exp.size() - 1)));
        w.write("\n");
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("partition.in"));
        w = new BufferedWriter(new FileWriter("partition.out"));
        generate(s.nextInt(), new ArrayList<>());
        w.close();
    }
}
