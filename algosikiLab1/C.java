/*
import java.io.*;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner();
        Queue q = new Queue();
        int n = s.nextInt();
        Writer writer = new BufferedWriter(new PrintWriter(System.out));
        for (int i = 0; i < n; i++) {
            int oper = s.nextInt();
            if (oper == 1) {
                q.add(s.nextInt());
            } else if (oper == 2) {
                q.getHead();
            } else if (oper == 3) {
                q.getTail();
            } else if (oper == 4) {
                writer.write(q.getPos(s.nextInt()) + "\n");
            } else {
                writer.write(q.whoIsFirst() + "\n");
            }
        }
        writer.close();
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

class Queue {
    private int[] queue = new int[100000];
    private int[] pos = new int[100000];
    private int tail = -1;
    private int head;

    public void add(int num) {
        queue[++tail] = num;
        pos[num-1] = tail;
    }

    public int getHead() {
        if(tail == head){
           return queue[tail--];
        }
        return queue[head++];
    }

    public int whoIsFirst(){
        return queue[head];
    }

    public int getTail() {
        return queue[tail--];
    }

    public int getPos(int n) {
        return (pos[n-1] - head);
    }
}
*/
