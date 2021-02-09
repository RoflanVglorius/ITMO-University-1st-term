/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        Steque steque = new Steque();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int row = 1;
        int count = 0;
        steque.push(Integer.parseInt(token.nextToken()));
        while (token.hasMoreTokens()) {
            int next = Integer.parseInt(token.nextToken());
            if (next == steque.last()) {
                row++;
            } else {
                if (row >= 3) {
                    count += row;
                    for (int i = 0; i < row; i++) {
                        steque.pop();
                    }
                    row = 1;
                    if (next == steque.last()) {
                        row++;
                        if (next == steque.preLast()) {
                            row++;
                        }
                    }
                } else {
                    row = 1;
                }
            }
            steque.push(next);
        }
        if (row >= 3 && steque.preLast() == steque.last()) {
            count += row;
        }
        System.out.println(count);
    }
}

class Steque {
    private int[] list;
    private int size = 0;

    public Steque() {
        list = new int[100000];
    }

    public void push(int num) {
        list[size] = num;
        size++;
    }

    public void pop() {
        size--;
    }

    public int last() {
        return list[size - 1];
    }

    public int preLast() {
        return list[size - 2];
    }
}
*/
