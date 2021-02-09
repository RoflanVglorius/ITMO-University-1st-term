/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
    public static void main(String[] args) throws IOException {
        Steque s = new Steque();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        while (token.hasMoreTokens()) {
            String in = token.nextToken();
            if (in.equals("*")) {
                s.push((s.pop() * s.pop()));
            } else if (in.equals("-")) {
                s.push((-s.pop() + s.pop()));
            } else if (in.equals("+")) {
                s.push((s.pop() + s.pop()));
            } else {
                s.push(Integer.parseInt(in));
            }
        }
        System.out.println(s.pop());
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

    public int pop() {
        return list[--size];
    }
}*/
