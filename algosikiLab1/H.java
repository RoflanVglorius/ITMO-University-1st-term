import java.io.*;
import java.util.StringTokenizer;

public class H {
    static Node[] snm;

    public static Node findRoot(Node x) {
        if (x.parent == x.index) {
            return x;
        }
        return (findRoot(snm[x.parent - 1]));
    }

    public static void add(Node x, int exp) {
        Node parent = findRoot(x);
        parent.toAdd += exp;
    }

    public static int get(Node x, int exp) {
        if (x.parent != x.index) {
            exp += x.toAdd + get(snm[x.parent - 1], exp);
        } else {
            exp += x.toAdd;
        }
        return exp;
    }

    public static void union(Node x, Node y) {
        Node parentX = findRoot(x);
        Node parentY = findRoot(y);
        if (parentX == parentY) {
            return;
        }
        if (parentX.size <= parentY.size) {
            parentX.parent = parentY.index;
            parentX.toAdd -= parentY.toAdd;
        } else {
            parentY.parent = parentX.parent;
            parentY.toAdd -= parentX.toAdd;
        }
        parentX.size += parentY.size;
        parentY.size = parentX.size;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());
        snm = new Node[n];
        for (int i = 0; i < n; i++) {
            snm[i] = new Node(i + 1);
        }
        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(reader.readLine());
            String oper = token.nextToken();
            if (oper.equals("join")) {
                int f = Integer.parseInt(token.nextToken());
                int s = Integer.parseInt(token.nextToken());
                union(snm[f - 1], snm[s - 1]);
            } else if (oper.equals("add")) {
                add(snm[Integer.parseInt(token.nextToken()) - 1], Integer.parseInt(token.nextToken()));
            } else {
                writer.write(get(snm[Integer.parseInt(token.nextToken()) - 1], 0) + "\n");
            }
        }
        writer.close();
    }
}

class Node {
    public int index;
    public int parent;
    public int exp;
    public int toAdd;
    public int size;

    public Node(int n) {
        parent = n;
        index = n;
        exp = 0;
        toAdd = 0;
        size = 1;
    }
}