import java.io.*;
import java.nio.charset.StandardCharsets;

public class Scanner {

    public interface Check {
        boolean isRight(char c);
    }

    private Reader reader;
    private char inputChar;
    private Check check;
    private char[] buffer = new char[1024];
    private int count = 0;
    private int index = 0;

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
        count = reader.read(buffer, index = 0, 1024); //
    }

    private void next() throws IOException {
        if (ifRead()) {
            read();
        }
        inputChar = buffer[index++];
    }

    private boolean hasNext() throws IOException {
        while ((hasToRead()) && (!check.isRight(inputChar))) {
            next();
        }
        return !(count == -1);
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
        return (!(hasToRead()) || (inputChar == '\n') || (inputChar == '\r'));
    }

    public int nextInt() throws IOException{
        return Integer.parseInt(nextChar());
    }

    public void skipLine() throws IOException {
        if ((inputChar == '\n') || (inputChar == '\r')) {
            next();
            if (inputChar == '\n') {
                next();
            }
        }
    }
}