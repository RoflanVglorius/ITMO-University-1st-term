import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class B {
    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c));
            }
        };
        boolean zero = false, one = false, lin = false, mon = false, dvoi = false;
        int oper = 0;
        Scanner scanner = new Scanner(System.in, check);
        int n = scanner.nextInt();
        scanner.skipLine();
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < (1 << 5); i++) {
            arguments.add(Integer.toBinaryString(i));
        }
        ArrayList<Integer> num = new ArrayList<>();
        ArrayList<String> f = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            num.add(scanner.nextInt());
            f.add(scanner.nextChar());
            scanner.skipLine();
        }
        for (oper = 0; oper < n; oper++) {
            if (num.get(oper) == 0 && oper < n - 1) {
                if (f.get(oper).toCharArray()[0] == '0') {
                    one = true;
                } else {
                    zero = true;
                }
                dvoi = true;
                oper++;
            } else if (num.get(oper) == 0) {
                if (f.get(oper).toCharArray()[0] == '0') {
                    one = true;
                } else {
                    zero = true;
                }
                dvoi = true;
                break;
            }
            char[] s = f.get(oper).toCharArray();
            if (s[0] == '1') {
                zero = true;
            }
            if (s[s.length - 1] == '0') {
                one = true;
            }
            for (int i = 0; i < s.length / 2; i++) {
                if (s[i] == s[s.length - 1 - i]) {
                    dvoi = true;
                    break;
                }
            }
            int[][] tr = new int[1 << num.get(oper)][1 << num.get(oper)];
            int j = 0;
            for (char c : s) {
                tr[j][0] = Character.valueOf(c);
                j++;
            }
            for (int i = 0; i < (1 << num.get(oper)) - 1; i++) {
                for (j = (1 << num.get(oper)) - 1; j > 0; j--) {
                    tr[j - 1][i + 1] = ((tr[j][i] + tr[j - 1][i]) % 2);
                }
            }
            int power = 1;
            for (int i = 0; i < (1 << num.get(oper)); i++) {
                if (i > power) {
                    power *= 2;
                }
                if (tr[0][i] == 1 && i != power) {
                    lin = true;
                    break;
                }
            }
            for (int i = 0; i < (1 << num.get(oper)); i++) {
                for (int k = i; k < (1 << num.get(oper)); k++) {
                    char[] ff = arguments.get(i).toCharArray();
                    char[] sf = arguments.get(k).toCharArray();
                    int t = ff.length - 1;
                    int x = sf.length - 1;
                    boolean checker = true;
                    while (t >= 0) {
                        if (ff[t] > sf[x]) {
                            checker = false;
                            break;
                        }
                        t--;
                        x--;
                    }
                    if (checker) {
                        if (f.get(oper).toCharArray()[i] > f.get(oper).toCharArray()[k]) {
                            mon = true;
                        }
                    }
                }
            }
        }
        if (mon && dvoi && one && zero && lin) {
            System.out.println("YES");

        } else {
            System.out.println("NO");
        }
    }
}

class Scanner {

    public interface Check {
        boolean isRight(char c);
    }

    private final Reader reader;
    private char inputChar;
    private final Check check;
    private char[] buffer = new char[5];
    private int count = 0;
    private int index = 0;
    private char readChar;
    private boolean isRead;

    public Scanner(Reader input, Check check) {
        reader = input;
        this.check = check;
        inputChar = 0;
    }

    public Scanner(String filename, Check check) throws IOException { //
        this(new FileReader(filename, StandardCharsets.UTF_8), check); //
    }

    public Scanner(InputStream in, Check check) throws IOException { //
        this(new InputStreamReader(in, "UTF-8"), check); //
    }

    public void close() throws IOException { //
        reader.close();
    }

    private boolean ifRead() {
        return (count == index);
    } //

    private void read() throws IOException {
        count = reader.read(buffer, index = 0, 5); //
    }

    private void next() throws IOException {
        if (ifRead()) {
            read();
        }
        if (isRead) {
            inputChar = readChar;
            isRead = false;
        } else {
            inputChar = buffer[index++];
        }
    }

    public boolean hasToRead() throws IOException {
        if (ifRead()) {
            read();
        }
        if (count == -1) {
            return false;
        }
        return true;
    }

    public String nextChar() throws IOException {
        skipWhiteSpace();
        StringBuilder string = new StringBuilder();
        while ((hasToRead()) && (check.isRight(inputChar))) {
            string.append(inputChar);
            next();
        }
        return string.toString();
    }

    private void skipWhiteSpace() throws IOException {
        while ((hasToRead()) && (!check.isRight(inputChar) && (inputChar != '\n') && (inputChar != '\r'))) {
            next();
        }
    }

    public boolean isEndOfLine() throws IOException {
        skipWhiteSpace();
        StringBuilder sep = new StringBuilder();
        if (!hasToRead()) {
            return true;
        }
        if (inputChar == '\n') {
            sep.append(inputChar);
            return (sep.toString().equals(System.lineSeparator()));
        }
        if (inputChar == '\r') {
            sep.append(inputChar);
            next();
            if (inputChar == '\n') {
                sep.append(inputChar);
            } else {
                readChar = inputChar;
                isRead = true;
            }

        }
        return (sep.toString().equals(System.lineSeparator()));
    }

    public int nextInt() throws IOException {
        String intStr = nextChar();
        if ((intStr.length() > 2) && ((intStr.charAt(1) == 'x') || (intStr.charAt(1) == 'X'))) {
            return (int) Long.parseLong(intStr.substring(2), 16);
        }
        return Integer.parseInt(intStr);
    }

    public void skipLine() throws IOException {
        if ((inputChar == '\n')) {
            next();
        } else if ((inputChar == '\r')) {
            next();
            if (inputChar == '\n') {
                next();
            }
        }
    }
}