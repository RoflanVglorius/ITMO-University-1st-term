/*
import java.io.*;
import java.util.StringTokenizer;

//public class A {
    public static void main(String[] args) throws IOException {
        Steque list = new Steque();
        Scanner scanner = new Scanner();
        Writer writer = new BufferedWriter(new PrintWriter(System.out));
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int oper = scanner.nextInt();
            if (oper == 1) {
                list.push(scanner.nextInt());
            } else if (oper == 2) {
                list.pop();
            } else if (oper == 3) {
                writer.write(list.getMin() + "\n");
            }
        }
        writer.close();
    }
}

//class Scanner {
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

//class Steque {
    private int[] list;
    private int[] minList;
    private int size = 0;

    public Steque() {
        list = new int[1000000];
        minList = new int[1000000];
    }

    public void push(int num) {
        list[size] = num;
        if (size == 0 || num < minList[size-1]) {
            minList[size] = num;
        } else {
            minList[size] = minList[size - 1];
        }
        size++;
    }

    public int getMin() {
        return minList[size - 1];
    }

    public void pop() {
        size--;
    }
}*/
