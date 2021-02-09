import java.io.*;
import java.nio.charset.StandardCharsets;

public class G {
    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c));
            }
        };
        boolean possibility = true;
        boolean zeroResult = false;
        int zeroIndex = 0;
        Scanner scanner = new Scanner(System.in, check);
        int n = scanner.nextInt();
        int[][] numbers = new int[n + 1][33];
        scanner.skipLine();
        for (int i = 0; i < n; i++) {
            int input = scanner.nextInt();
            char[] buff = Integer.toBinaryString(input).toCharArray();
            int index = buff.length - 1;
            for (int j = 32; j > 32 - buff.length; j--) {
                numbers[i][j] = Character.getNumericValue(buff[index]);
                index--;
            }
        }
        scanner.skipLine();
        int input = scanner.nextInt();
        if (input == 0){
            zeroResult = true;
        }
        StringBuilder toBinaryBuff = new StringBuilder(Integer.toBinaryString(input));
        while (toBinaryBuff.length() < 33) {
            toBinaryBuff.insert(0, 0);
        }
        char[] result = toBinaryBuff.toString().toCharArray();
        for (int i = 0; i < 33; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < n; j++) {
                s.append(numbers[j][i]);
            }
            numbers[n][i] = Integer.parseInt(s.toString(), 2);
        }
        for (int i = 0; i < 33; i++) {
            for (int j = i; j < 33; j++) {
                if (numbers[n][i] == numbers[n][j] && result[i] != result[j]) {
                    possibility = false;
                }
            }
        }
        if (!possibility) {
            System.out.println("Impossible");
        } else if(zeroResult){
            System.out.println("1&~1");
        }else{
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < 33; i++) {
                if (result[i] == '1') {
                    if (numbers[0][i] == 1) {
                        buffer.append(1);
                    } else {
                        buffer.append("~1");
                    }
                    for (int j = 1; j < n; j++) {
                        if (numbers[j][i] == 1) {
                            buffer.append("&").append(j + 1);
                        } else {
                            buffer.append("&").append("~").append(j + 1);
                        }
                    }
                    buffer.append("|");
                }
            }
            if (buffer.length() > 0) {
                buffer.setLength(buffer.length() - 1);
            }
            Writer out = new BufferedWriter(new PrintWriter(System.out));
            out.write(buffer.toString());
            out.close();
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
    private char[] buffer = new char[1024];
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
        this(new InputStreamReader(in, StandardCharsets.UTF_8), check); //
    }

    public void close() throws IOException { //
        reader.close();
    }

    private boolean ifRead() {
        return (count == index);
    } //

    private void read() throws IOException {
        count = reader.read(buffer, index = 0, 1024); //
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
        return count != -1;
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
        return (int) Long.parseLong(intStr);
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