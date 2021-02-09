import java.io.*;
import java.nio.charset.StandardCharsets;

public class A {
    public static void main(String[] args) throws IOException {
        int refl = 1;
        int antirefl = 1;
        int sim = 1;
        int antisim = 1;
        int tran = 1;
        int refl2 = 1;
        int antirefl2 = 1;
        int sim2 = 1;
        int antisim2 = 1;
        int tran2 = 1;
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c));
            }
        };
        Scanner scanner = new Scanner(System.in, check);
        int n = scanner.nextInt();
        scanner.skipLine();
        int[][] r1 = new int[n][n];
        int[][] r2 = new int[n][n];
        int i;
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                r1[i][j] = scanner.nextInt();
            }
            scanner.skipLine();
        }
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                r2[i][j] = scanner.nextInt();
            }
            scanner.skipLine();
        }
        for (i = 0; i < n; i++) {
            if (r1[i][i] != 1) {
                refl = 0;
                break;
            }
        }
        for (i = 0; i < n; i++) {
            if (r2[i][i] != 1) {
                refl2 = 0;
                break;
            }
        }
        //antirefl
        for (i = 0; i < n; i++) {
            if (r1[i][i] == 1) {
                antirefl = 0;
                break;
            }
        }
        for (i = 0; i < n; i++) {
            if (r2[i][i] == 1) {
                antirefl2 = 0;
                break;
            }
        }
        //sim
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((r1[i][j] != r1[j][i]) || (r1[i][j] != r1[j][i])) {
                    sim = 0;
                    break;
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((r2[i][j] != r2[j][i]) || (r2[i][j] != r2[j][i])) {
                    sim2 = 0;
                    break;
                }
            }
        }
        //antisim
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((r1[i][j] == 1 && r1[j][i] == 1 && i != j)) {
                    antisim = 0;
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((r2[i][j] == 1 && r2[j][i] == 1 && i != j)) {
                    antisim2 = 0;
                }
            }
            //tran
            for (i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (r1[i][j] == 1) {
                        for (int t = 0; t < n; t++) {
                            if (r1[j][t] == 1 && r1[i][t] == 0) {
                                tran = 0;
                            }
                        }
                    }
                }
            }
            for (i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (r2[i][j] == 1) {
                        for (int t = 0; t < n; t++) {
                            if (r2[j][t] == 1 && r2[i][t] == 0) {
                                tran2 = 0;
                            }
                        }
                    }
                }
            }
            int[][] r3 = new int[n][n];
            System.out.println(refl + " " + antirefl + " " + sim + " " + antisim + " " + tran);
            System.out.println(refl2 + " " + antirefl2 + " " + sim2 + " " + antisim2 + " " + tran2);
            for (i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int t = 0; t < n; t++) {
                        if (r1[i][j] == 1 & r2[j][t] == 1) {
                            r3[i][t] = 1;
                        }
                    }
                }
            }
            for (i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    System.out.print(r3[i][j] + " ");
                }
                int k = n - 1;
                System.out.printf("%s\n", r3[i][k]);
            }
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
