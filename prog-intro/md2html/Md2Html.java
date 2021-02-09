package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        // :NOTE: Число аргументов
        String output;
        try (Reader reader = new FileReader(args[0], StandardCharsets.UTF_8);
             FileSource source = new FileSource(reader)) {
            Md2HtmlParser parser = new Md2HtmlParser(source);
            output = parser.parse();
        } catch (IOException e) {
            // :NOTE: Сообщения об ошибках
            System.out.println("Input file error");
            return;
        }

        try (Writer writer = new PrintWriter(args[1], StandardCharsets.UTF_8)) {
            writer.write(output);
        } catch (IOException e) {
            System.out.println("Output file error");
        }
    }
}
