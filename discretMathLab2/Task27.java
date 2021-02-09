import java.io.*;
import java.util.Scanner;

public class Task27 {
    static Scanner s;
    static Writer w;

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("nextbrackets.in"));
        w = new BufferedWriter(new FileWriter("nextbrackets.out "));
        StringBuilder str = new StringBuilder(s.nextLine());
        int open = 0;
        int close = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ')') {
                close++;
            } else {
                open++;
                if (open < close) {
                    str.setCharAt(i, ')');
                    for (int j = i + 1; j < str.length() - 1; j++) {
                        if (open > 0) {
                            str.setCharAt(j, '(');
                            open--;
                        } else {
                            str.setCharAt(j, ')');
                        }
                    }
                    break;
                }
            }
        }
        if (open != 0) {
            w.write("-");
        } else {
            w.write(str.toString());
        }
        w.close();
    }
}
