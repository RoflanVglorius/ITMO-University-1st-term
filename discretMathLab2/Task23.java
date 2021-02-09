import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Task23 {
    static Scanner s;
    static Writer w;

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("nextvector.in"));
        w = new BufferedWriter(new FileWriter("nextvector.out"));
        String inp = s.nextLine();
        int len = inp.length();
        BigInteger vector = new BigInteger(inp, 2);
        BigInteger next = vector.add(BigInteger.ONE);
        BigInteger prev = vector.subtract(BigInteger.ONE);
        StringBuilder pstr = new StringBuilder(prev.toString(2));
        StringBuilder nstr = new StringBuilder(next.toString(2));
        if (prev.compareTo(BigInteger.ZERO) >= 0) {
            while (pstr.length() < len) {
                pstr.insert(0, 0);
            }
            w.write(pstr.toString());
        } else {
            w.write("-");
        }
        w.write('\n');
        if (nstr.length() > len) {
            w.write("-");
        } else {
            while (nstr.length() < len) {
                nstr.insert(0, 0);
            }
            w.write(nstr.toString());
        }
        w.close();
    }
}
