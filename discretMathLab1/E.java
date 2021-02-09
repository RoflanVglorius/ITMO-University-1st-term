import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class E {
    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c));
            }
        };
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in, check);
        int n = Integer.parseInt(scanner.nextChar());
        scanner.skipLine();
        for (int i = 0; i < (1 << n); i++) {
            list.add(scanner.nextChar());
            list.add(scanner.nextChar());
            scanner.skipLine();
        }
        int[][] triangle = new int[1 << n][1 << n];
        int j = 0;
        for (int i = 1; i < (1 << n+1); i+=2){
            triangle[j][0]=Integer.parseInt(list.get(i));
            j++;
        }
        for (int i = 0; i < (1 << n)- 1; i++){
            for (j = (1 << n) - 1;j>0;j--){
                triangle[j-1][i+1]=((triangle[j][i] + triangle[j-1][i])%2);
            }
        }
        j = 0;
        for (int i = 0; i < (1 << n+1); i+=2){
            System.out.println(list.get(i) + " " + triangle[0][j]);
            j++;
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