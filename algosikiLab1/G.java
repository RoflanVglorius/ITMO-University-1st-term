/*
import java.io.*;
import java.nio.charset.StandardCharsets;

public class G {
    static Node[] snm;

    public static Node findRoot(Node x) {
        if (x.parent == x.index) {
            return x;
        }
        x.parent = findRoot(snm[x.parent - 1]).parent;
        return (findRoot(snm[x.parent - 1]));
    }

    public static void union(Node x, Node y) {
        Node parentX = findRoot(x);
        Node parentY = findRoot(y);
        if (parentX.parent == parentY.parent) {
            return;
        }
        if (parentX.size >= parentY.size) {
            parentX.parent = parentY.index;
        } else {
            parentY.parent = parentX.parent;
        }
        if (parentX.min > parentY.min) {
            parentX.min = parentY.min;
        } else {
            parentY.min = parentX.min;
        }
        if (parentX.max > parentY.max) {
            parentY.max = parentX.max;
        } else {
            parentX.max = parentY.max;
        }
        parentX.size += parentY.size;
        parentY.size = parentX.size;
        return;
    }

    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c) || Character.isLetter(c));
            }
        };
        Scanner scanner = new Scanner(System.in, check);
        BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));
        int n = scanner.nextInt();
        scanner.skipLine();
        snm = new Node[n];
        for (int i = 0; i < n; i++) {
            snm[i] = new Node(i + 1);
        }
        while (scanner.hasToRead()) {
            String oper = scanner.nextChar();
            if (oper.equals("")){
                break;
            }
            if (oper.equals("union")) {
                int f = scanner.nextInt();
                int s = scanner.nextInt();
                union(snm[f - 1], snm[s - 1]);
            } else {
                Node x = findRoot(snm[scanner.nextInt() - 1]);
                StringBuilder buff = new StringBuilder();
                writer.write(buff.append(x.min).append(" ").append(x.max)
                        .append(" ").append(x.size).append("\n").toString());
            }
            scanner.skipLine();
        }
        writer.close();
    }
}

class Node {
    public int index;
    public int parent;
    public int min;
    public int max;
    public int size;

    public Node(int n) {
        parent = n;
        index = n;
        min = n;
        max = n;
        size = 1;
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
}*/
