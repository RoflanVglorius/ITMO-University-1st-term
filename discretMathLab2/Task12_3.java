import java.io.*;
import java.util.StringTokenizer;

public class Task12_3 {
    static Scanner s;
    static Writer w;
    static int n;
    static int k;
    static int[] currentSets;

    static public void writeCurrent() throws IOException {
        for (int setIndex = 0; setIndex < k; setIndex++) {
            for (int el = 0; el < n; el++) {
                if (currentSets[el] == setIndex) {
                    w.write((el + 1) + " ");
                }
            }
            w.write('\n');
        }
        w.write('\n');
    }

    static public void generate(int setCount, int currentElement) throws IOException {
        if (currentElement >= n) {
            if (setCount == k) {
                writeCurrent();
            }
            return;
        }
        currentSets[currentElement] = setCount - 1;
        for (int i = 0; i <= setCount; i++) {
            currentSets[currentElement] = i;
            generate(setCount + (i == setCount ? 1 : 0), currentElement + 1);
        }
    }


    public static void main(String[] args) throws IOException {
        s = new Scanner();
        w = new PrintWriter("output.txt");
        n = s.nextInt();
        k = s.nextInt();
        currentSets = new int[n];
        generate(0, 0);
        w.close();
    }
}

class Scanner {
    public BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
    public StringTokenizer token;

    public Scanner() throws IOException {
        token = new StringTokenizer(reader.readLine());
    }

    public int nextInt() throws IOException {
        while (!token.hasMoreTokens()) {
            token = new StringTokenizer(reader.readLine());
        }
        return Integer.parseInt(token.nextToken());
    }
}
