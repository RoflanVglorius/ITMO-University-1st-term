import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatCountLineIndex {
    public static void main(String[] args) {
        Map<String, IntListPair> words = new LinkedHashMap<>();
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return (Character.isLetter(c) || (Character.getType(c) == Character.DASH_PUNCTUATION) || (c) == '\'');
            }
        };
        int lineIndex = 1;
        try {
            Scanner scanner = new Scanner(args[0], check);
            try {
                while (scanner.hasToRead()) {
                    int columnIndex = 1;
                    while (!scanner.isEndOfLine()) {
                        String word = scanner.nextChar().toLowerCase();
                        IntListPair buff;
                        if (words.containsKey(word)) {
                            buff = words.get(word);
                        } else {
                            buff = new IntListPair();
                        }
                        buff.add(lineIndex, columnIndex);
                        words.put(word, buff);
                        columnIndex++;
                    }
                    lineIndex++;
                    scanner.skipLine();
                }
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Input reader error: " + e.getMessage());
            System.exit(-1);
        }
        List<Map.Entry<String, IntListPair>> entryList = new ArrayList<>(words.entrySet());
        entryList.sort(Comparator.comparingInt(toSort -> toSort.getValue().size()));
        try {
            Writer out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, IntListPair> entry : entryList) {
                    out.write(String.format("%s %s", entry.getKey(), entry.getValue().size()));
                    int iter = entry.getValue().size();
                    for (int i = 0; i < iter; i++) {
                        out.write(String.format(" %s:%s",
                                entry.getValue().getLine(i), entry.getValue().getColumn(i)));
                    }
                    out.write('\n');
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