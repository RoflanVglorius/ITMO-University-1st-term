import java.io.*;
import java.nio.charset.StandardCharsets;

/*
3
000 0
001 0
010 0
011 1
100 0
101 1
110 1
111 1

3
000 0
001 0
010 0
011 0
100 1   2 1 5  2 6 7
101 0
110 1   2 1 2  2 6 9
111 1   2 1 2  2 3 11  3 8 10  3 12 13

 */
public class D {
    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c));
            }
        };
        Scanner scanner = new Scanner(System.in, check);
        int n = scanner.nextInt();
        scanner.skipLine();
        StringBuilder l = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            l.append(1 + " " + i + '\n');
        }
        int index = 2 * n;
        for (int i = 0; i < (1 << n); i++) {
            char[] s = scanner.nextChar().toCharArray();
            int r = scanner.nextInt();
            scanner.skipLine();
            if (r == 1) {
                l.append(2);
                if (s[0] == '0') {
                    l.append(" " + (n + 1));
                } else {
                    l.append(" " + 1);
                }
                if (s[1] == '0') {
                    l.append(" " + (n + 2));
                } else {
                    l.append(" " + 2);
                }
                index++;
                l.append('\n');
                for (int j = 2; j < n; j++) {
                    l.append(2 + " ");
                    if (s[j] == '0') {
                        l.append((j + n + 1) + " " + index);
                        index++;
                        l.append('\n');
                    } else {
                        l.append((j + 1) + " " + index);
                        index++;
                        l.append('\n');
                    }
                }
            }
        }
        if (index != 2 * n) {
            int kek = index;
            l.append(3 + " " + (3 * n - 1) + " " + (4 * n - 2) + '\n');
            kek++;
            index += 2;
            for (int j = 5 * n - 3; j < index; j += n - 1) {
                l.append(3 + " " + j + " " + kek + '\n');
                kek++;
            }
            System.out.println(kek);
            System.out.println(l.toString());
        } else {
            System.out.println(n + 2);
            System.out.println(1 + " " + 1);
            System.out.println(2 + " " + 1 + " " + (n + 1));
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