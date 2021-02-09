/*
import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));
        ArrayDeque<Integer> first = new ArrayDeque<>();
        ArrayDeque<Integer> second = new ArrayDeque<>();
        int n = Integer.parseInt(token.nextToken());
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(reader.readLine());
            String buff = token.nextToken();
            if (buff.equals("+")) {
                second.addLast(Integer.parseInt(token.nextToken()));
                if (first.size() < second.size()) {
                    first.addLast(second.pollFirst());
                }
            } else if (buff.equals("*")) {
                if (first.size() > second.size()) {
                    second.addFirst(Integer.parseInt(token.nextToken()));
                } else {
                    first.addLast(Integer.parseInt(token.nextToken()));
                }
            } else {
                if (first.size() != 0) {
                    writer.write(first.pollFirst() + "\n");
                } else {
                    writer.write(second.pollFirst() + "\n");
                }
                if (first.size() < second.size()) {
                    first.addLast(second.pollFirst());
                }
            }
        }
        writer.close();
    }
}

*/
