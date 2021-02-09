import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*
3 3
1 0 -1
0 1 0
-1 0 1

1 2
1
0
 */
public class F {
    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c) || c == '-');
            }
        };
        Scanner scanner = new Scanner(System.in, check);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] index = new int[n];
        int[] col = new int[k];
        ArrayList<int[]> in = new ArrayList<>();
        scanner.skipLine();
        String ans = "NO";
        int is = 0;
        for (int i = 0; i < n; i++) {
            int[] s = new int[k];
            for (int t = 0; t < k; t++) {
                s[t] = scanner.nextInt();
            }
            in.add(s);
            scanner.skipLine();
        }
        if (k == 0) {
            ans = "YES";
        } else if (k == 1) {
            int kek = in.get(0)[0];
            for (int i = 1; i < n; i++)
                if (in.get(i)[0] != kek) {
                    ans = "YES";
                    break;
                }
        } else {
            a:
            while (true) {
                is = 0;
                for (int i = 0; i < n; i++) {
                    if (index[i] != 2) {
                        int[] b = in.get(i);
                        int c = 0;
                        for (int t = 0; t < k; t++) {
                            if (b[t] == -1) {
                                c += 1;
                            }
                        }
                        if (c == k - 1) {
                            index[i] = 1;
                            is++;
                        }
                    }
                }
                if (is == 0) {
                    break a;
                }
                for (int i = 0; i < n; i++) {
                    if (index[i] == 1) {
                        int[] buff = in.get(i);
                        for (int j = 0; j < k; j++) {
                            if (buff[j] != -1 && col[j] == 0) {
                                if (buff[j] == 1) {
                                    buff[j] = 3;
                                    col[j] = 3;
                                } else {
                                    buff[j] = 3;
                                    col[j] = 2;
                                }
                                index[i] = 2;
                            }
                        }
                        in.set(i, buff);
                    }
                }
                for (int i = 0; i < k; i++) {
                    if (col[i] != 0) {
                        for (int j = 0; j < n; j++) {
                            int[] buff = in.get(j);
                            if (buff[i] == 0) {
                                if (col[i] == 2) {
                                    buff[i] = 3;
                                    index[j] = 2;
                                } else {
                                    buff[i] = -1;
                                }
                            } else if (buff[i] == 1) {
                                if (col[i] == 2) {
                                    buff[i] = -1;
                                } else {
                                    buff[i] = 3;
                                    index[j] = 2;
                                }
                            }
                            in.set(j, buff);
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    int[] buff = in.get(i);
                    int count = 0;
                    for (int j = 0; j < k; j++) {
                        if (buff[j] == -1 || buff[j] == 2) {
                            count++;
                        }
                    }

                    if (count == k) {
                        ans = "YES";
                        break a;
                    }
                }
                int checker = 0;
                for (int i = 0; i < n; i++) {
                    if (index[i] != 2) {
                        checker++;
                        break;
                    }
                }
                if (checker == 0) {
                    break a;
                }
            }
        }
        System.out.println(ans);
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