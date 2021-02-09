package md2html;

import java.io.*;


public class FileSource implements AutoCloseable {
    private StringBuilder currentParagraph;
    private int pos;
    private final BufferedReader reader;
    private String currentLine = "";

    public FileSource(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    public String nextParagraph() throws IOException {
        pos = 0;
        skipBlankLines();
        if (currentLine == null) {
            return "";
        }
        currentParagraph = new StringBuilder(currentLine);
        nextLine();
        while (currentLine != null && !currentLine.isEmpty()) {
            currentParagraph.append('\n');
            currentParagraph.append(currentLine);
            nextLine();
        }
        return currentParagraph.toString();
    }

    private void skipBlankLines() throws IOException {
        while (currentLine != null && currentLine.isEmpty()) {
            nextLine();
        }
    }

    public boolean hasNext() {
        return pos < currentParagraph.length();
    }

    public char next() {
        return currentParagraph.charAt(pos++);
    }

    private void nextLine() throws IOException {
        currentLine = reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
    }

    public String takeNextIfSimilar(char ch) {
        return pos < currentParagraph.length() && (ch == currentParagraph.charAt(pos)) ?
                String.valueOf(ch) + currentParagraph.charAt(pos) : String.valueOf(ch);
    }
}
