import java.io.*;
import java.util.TreeMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

public class WordStatWordsPrefix {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> words = new TreeMap<>();
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return ((Character.isLetter(c)) || (Character.getType(c) == Character.DASH_PUNCTUATION) || ((c) == '\''));
            }
        };
        try {
            Scanner scanner = new Scanner(args[0], check);
            try {
                while (scanner.hasToRead()) {
                    while (!scanner.isEndOfLine()) {
                        String word = scanner.nextChar();
                        if (word.length() >= 3) {
                            String buff = word.toLowerCase().substring(0, 3);
                            if ((words.containsKey(buff)) && (word.length() != 0)) {
                                words.put(buff, words.get(buff) + 1);
                            } else if (word.length() != 0) {
                                words.put(buff, 1);
                            }
                        }
                    }
                    scanner.skipLine();
                }
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Input reader error: " + e.getMessage());
            System.exit(-1);
        }
        try {
            Writer out = new BufferedWriter(new FileWriter((args[1]), StandardCharsets.UTF_8));
            try {
                for (Map.Entry entry : words.entrySet()) {
                        out.write(String.format("%s %d\n", entry.getKey(), entry.getValue()));
                    }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Output error : " + e.getMessage());
            System.exit(-1);
        }
    }
}