package md2html;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Md2HtmlParser {
    private final FileSource source;
    private final Set<String> prevMarkups = new HashSet<>();
    private final Map<Character, String> specialHtml = new HashMap<>(Map.of('&', "&amp;", '<',
            "&lt;", '>', "&gt;"));
    private final Map<String, String> tag = new HashMap<>(Map.of("*", "em", "**", "strong",
            "`", "code", "__", "strong", "--",
            "s", "_", "em", "~", "mark"));
    private final Set<Character> escapeSymbols = new HashSet<>(Set.of('*', '_', '-', '~', '`', '!'));
    private char currentChar;
    private char currentBracket;

    public Md2HtmlParser(FileSource source) {
        this.source = source;
    }

    public String parse() throws IOException {
        StringBuilder convertedMd = new StringBuilder();
        while (!source.nextParagraph().isEmpty()) {
            convertedMd.append(parseParagraph());
        }
        return convertedMd.toString();
    }

    private String parseParagraph() {
        StringBuilder convertedMd = new StringBuilder();
        nextChar();
        int headlineLevel = 0;
        while (check('#')) {
            headlineLevel++;
            nextChar();
        }
        boolean isHeadline = Character.isWhitespace(currentChar) && headlineLevel != 0;
        if (isHeadline) {
            nextChar();
        }
        String parsed = parseText(false, false);
        if (!isHeadline) {
            convertedMd.append(String.format("<p>%s%s</p>",
                    "#".repeat(headlineLevel), parsed));
        } else {
            convertedMd.append(String.format("<h%d>%s</h%d>",
                    headlineLevel, parsed, headlineLevel));
        }
        return convertedMd.append('\n').toString();
    }

    private String parseText(boolean inMarkup, boolean isImage) {
        StringBuilder convertedMd = new StringBuilder();
        while (!check('\0')) {
            String saved = source.takeNextIfSimilar(currentChar);
            if (check('\\')) {
                nextChar();
                if (!escapeSymbols.contains(currentChar)) {
                    convertedMd.append('\\');
                    continue;
                }
                convertedMd.append(currentChar);
            } else if (!isImage && tag.containsKey(saved)) {
                if (inMarkup) {
                    break;
                }
                convertedMd.append(parseMarkup(saved));
                nextChar(saved.length());
                continue;
            } else if (specialHtml.containsKey(currentChar)) {
                convertedMd.append(specialHtml.get(currentChar));
            } else if (check('!') && !isImage) {
                nextChar();
                convertedMd.append(parseImage());
                continue;
            } else if (isImage && check(currentBracket)) {
                break;
            } else {
                convertedMd.append(currentChar);
            }
            nextChar();
        }
        return convertedMd.toString();
    }

    private String parseImage() {
        char[] brackets = {'[', ']', '(', ')'};
        boolean isCorrect = true;
        String[] nameAndLink = new String[2];
        StringBuilder format = new StringBuilder("!");
        for (int i = 0; i < 4; i++) {
            if (!check(brackets[i])) {
                isCorrect = false;
                nextChar();
                break;
            }
            format.append(brackets[i]);
            if (i % 2 == 0) {
                format.append("%s");
            }
            nextChar();
            currentBracket = brackets[i + (i % 2 == 0 ? 1 : 0)];
            if (i % 2 == 0) {
                nameAndLink[i / 2] = parseText(false, true);
            }
        }
        return String.format(isCorrect ? "<img alt='%s' src='%s'>" : format.toString(), nameAndLink[0], nameAndLink[1]);
    }


    private String parseMarkup(String markup) {
        skip(markup);
        StringBuilder convertedMd = new StringBuilder();
        while (!check('\0')) {
            String saved = source.takeNextIfSimilar(currentChar);
            if (saved.equals(markup)) {
                prevMarkups.remove(markup);
                return String.format("<%s>%s</%s>", tag.get(markup), convertedMd, tag.get(markup));
            } else if (prevMarkups.contains(saved)) {
                break;
            } else if (tag.containsKey(saved)) {
                prevMarkups.add(markup);
                convertedMd.append(parseMarkup(saved));
                String current = source.takeNextIfSimilar(currentChar);
                if (!prevMarkups.contains(current)) {
                    nextChar(current.length());
                }
            } else {
                convertedMd.append(parseText(true, false));
            }
        }
        prevMarkups.remove(markup);
        return String.format("%s%s", markup, convertedMd.toString());
    }

    private void skip(String toSkip) {
        for (int i = 0; i < toSkip.length(); i++) {
            nextChar();
        }
    }

    private void nextChar() {
        currentChar = source.hasNext() ? source.next() : '\0';
    }

    private void nextChar(int step) {
        while (step > 0) {
            currentChar = source.hasNext() ? source.next() : '\0';
            step--;
        }
    }

    private boolean check(char ch) {
        return ch == currentChar;
    }
}