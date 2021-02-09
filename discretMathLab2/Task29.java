import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task29 {
    static Scanner s;
    static BufferedWriter w;

    public static void main(String[] args) throws IOException {
//        s = new Scanner(new FileInputStream("input.txt"));
//        w = new BufferedWriter(new FileWriter("output.txt"));
        s = new Scanner(new FileInputStream("nextpartition.in"));
        w = new BufferedWriter(new FileWriter("nextpartition.out"));
        char[] in = s.nextLine().toCharArray();
        int index = 0;
        int sum;
        StringBuilder buff = new StringBuilder();
        while (Character.isDigit(in[index])) {
            buff.append(in[index++]);
        }
        sum = Integer.parseInt(buff.toString());
        buff.setLength(0);
        int[] part = new int[sum];
        int cur = 0;
        index++;
        for (int i = index; i < in.length; i++) {
            if (Character.isDigit(in[i])) {
                buff.append(in[i]);
            } else {
                part[cur] = Integer.parseInt(buff.toString());
                buff.setLength(0);
                cur++;
            }
        }
        part[cur++] = Integer.parseInt(buff.toString());
        if(sum == part[0]){
            w.write("No solution");
            w.close();
            return;
        }
        part[cur - 1]--;
        part[cur - 2]++;
        if (part[cur - 1] < part[cur - 2]) {
            part[cur - 2] += part[cur - 1];
            part[cur - 1] = 0;
        } else {
            int i = cur - 1;
            int unused = part[cur - 1];
            part[cur - 1] = 0;
            while (unused >= part[cur - 2]) {
                part[i++] = part[cur - 2];
                unused -= part[cur - 2];
            }
            part[--i] += unused;
        }
        StringBuilder res = new StringBuilder();
        res.append(sum).append('=');
        int j = 0;
        while (part[j] != 0) {
            res.append(part[j++]).append('+');
        }
        w.write(res.substring(0, res.length() - 1));
        w.close();
    }
}
