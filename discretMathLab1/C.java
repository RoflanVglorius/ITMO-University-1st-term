import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class C {
    public static char[] toBinary(int number, int bytes) {
        StringBuilder buff = new StringBuilder(Integer.toBinaryString(number));
        while (buff.length() < bytes) {
            buff.insert(0, 0);
        }
        return buff.toString().toCharArray();
    }

    public static int getDepth(ArrayList<Element> e, Element el) {
        int len = el.input.length;
        int depth;
        if (len == 0) {
            return 0;
        } else {
            ArrayList<Integer> depths = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                depths.add(getDepth(e, e.get(el.input[i])));
            }
            return Collections.max(depths) + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isDigit(c));
            }
        };
        Scanner scanner = new Scanner(System.in, check);
        int n = scanner.nextInt();
        scanner.skipLine();
        ArrayList<Element> elements = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            if (num == 0) {
                elements.add(new Element(new int[0], 0, new char[0]));
                index.add(i);
            } else {
                elements.add(new Element(new int[num], 0, new char[1 << num]));
                for (int j = 0; j < num; j++) {
                    elements.get(i).input[j] = scanner.nextInt() - 1;
                }
                scanner.skipLine();
                for (int k = 0; k < 1 << num; k++) {
                    elements.get(i).table[k] = scanner.nextChar().charAt(0);
                }
            }
            scanner.skipLine();
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 1 << index.size(); i++) {
            char[] buff = toBinary(i, index.size());
            for (int j = 0; j < index.size(); j++) {
                elements.get(index.get(j)).res = Character.getNumericValue(buff[j]);
            }
            for (int k = 0; k < n; k++) {
                if (elements.get(k).input.length != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int t = 0; t < elements.get(k).input.length; t++) {
                        stringBuilder.append(elements.get(elements.get(k).input[t]).res);
                    }
                    elements.get(k).res = Character.getNumericValue(
                            elements.get(k).
                                    table[Integer.parseInt(stringBuilder.toString(), 2)]);
                }
            }
            s.append(elements.get(n - 1).res);
        }
        System.out.println(getDepth(elements, elements.get(n - 1)));
        System.out.println(s);
    }
}

class Element {
    public int[] input;
    public int res;
    public char[] table;

    public Element(int[] input, int res, char[] table) {
        this.input = input;
        this.res = res;
        this.table = table;
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