/*
import java.io.*;
import java.util.StringTokenizer;

public class F {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner();
        Steque q = new Steque();
        int n = s.nextInt();
        int firstPop = 0;
        boolean ifPopped = false;
        StringBuilder str = new StringBuilder();
        Writer writer = new BufferedWriter(new PrintWriter(System.out));
        a:
        for (int i = 0; i < n; i++) {
            int x = s.nextInt();
            if (q.size() < 0) {
                firstPop = x;
                q.push(x);
                str.append("push\n");
            } else {
                if (x > q.last()) {
                    while (q.size() >= 0 && x > q.last()) {
                        str.append("pop\n");
                        if (!ifPopped) {
                            firstPop = q.last();
                            ifPopped = true;
                        }
                        if (q.last() < firstPop) {
                            str = new StringBuilder("impossible");
                            break a;
                        }
                        if (q.last() > firstPop) {
                            firstPop = q.last();
                        }
                        q.pop();
                    }
                    str.append("push\n");
                    q.push(x);
                } else {
                    str.append("push\n");
                    q.push(x);
                }
            }
        }
        if (!str.toString().equals("impossible")) {
            while (q.size() > 0) {
                if (q.last() < firstPop && ifPopped) {
                    str = new StringBuilder("impossible");
                    break;
                }
                str.append("pop\n");
                q.pop();
            }
            if (!str.toString().equals("impossible")) {
                str.append("pop");
            }
        }
        writer.write(str.toString());
        writer.close();
    }
}

class Steque {
    private int[] list;
    private int size = -1;

    public Steque() {
        list = new int[100000];
    }

    public void push(int num) {
        list[++size] = num;
    }

    public int pop() {
        return list[size--];
    }

    public int last() {
        return list[size];
    }

    public int size() {
        return size;
    }
}

class Scanner {
    public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
*/
