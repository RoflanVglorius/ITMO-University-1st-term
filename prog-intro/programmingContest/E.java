import java.io.*;
import java.util.*;

public class E {
    static int maxDepthIndex;
    static int middle = -1;
    static boolean isAnswer = true;

    public static void maxDepth(int position, Node[] nodes, int depth) {
        nodes[position].depth = ++depth;
        nodes[position].isVisited = true;
        if (nodes[position].isTeam && nodes[position].depth > nodes[maxDepthIndex].depth) {
            maxDepthIndex = position;
        }
        for (int next : nodes[position].connections) {
            if (!nodes[next - 1].isVisited) {
                maxDepth(next - 1, nodes, depth);
            }
        }
        if (2 * nodes[position].depth == nodes[maxDepthIndex].depth && position != maxDepthIndex && middle == -1
                && !nodes[position].isTeam) {
            middle = position;
        }
    }

    public static void checkAnswer(int position, Node[] nodes) {
        Stack<Integer> stack = new Stack<>();
        stack.push(position);
        while (stack.size() != 0 && isAnswer) {
            position = stack.pop();
            int prev = nodes[position].previous;
            nodes[position].depth = nodes[prev].depth + 1;
            nodes[position].isVisited = false;
            if (nodes[middle].isTeam || nodes[position].isTeam && 2 * nodes[middle].depth != nodes[position].depth) {
                isAnswer = false;
            }
            for (int i = nodes[position].connections.size() - 1; i >= 0; i--) {
                int index = nodes[position].connections.get(i) - 1;
                if (nodes[index].isVisited) {
                    nodes[index].previous = position;
                    stack.push(index);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner();
        int i;
        int nCities = scanner.nextInt();
        int nTeams = scanner.nextInt();
        Node[] nodes = new Node[nCities];
        nodes[0] = new Node();
        for (i = 0; i < nCities - 1; i++) {
            int current = scanner.nextInt();
            int connection = scanner.nextInt();
            if (nodes[current - 1] == null) {
                nodes[current - 1] = new Node();
            }
            nodes[current - 1].connections.add(connection);
            if (nodes[connection - 1] == null) {
                nodes[connection - 1] = new Node();
            }
            nodes[connection - 1].connections.add(current);
        }
        int current = scanner.nextInt() - 1;
        nodes[current].isTeam = true;
        for (i = 0; i < nTeams - 1; i++) {
            nodes[scanner.nextInt() - 1].isTeam = true;
        }
        maxDepthIndex = current;
        maxDepth(current, nodes, -1);
        if (nCities == 1 || nTeams == 1) {
            middle = 0;
        } else if (middle == -1) {
            isAnswer = false;
        } else {
            nodes[middle].depth -= 1;
            nodes[middle].previous = middle;
            checkAnswer(middle, nodes);
        }
        if (isAnswer) {
            System.out.printf("%s\n%d", "YES", (middle + 1));
        } else {
            System.out.println("NO");
        }
    }
}

class Node {
    public int depth;
    public ArrayList<Integer> connections;
    public boolean isVisited;
    public boolean isTeam;
    public int previous;

    public Node() {
        this.depth = 0;
        this.connections = new ArrayList<>();
        this.isVisited = false;
        this.isTeam = false;
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