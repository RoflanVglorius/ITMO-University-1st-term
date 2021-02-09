import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {
    static Writer w;
    static Scanner r;
    static ArrayList<String> chain = new ArrayList<>();
    static boolean[] isExist;

    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("chaincode.in"));
        w = new BufferedWriter(new FileWriter("chaincode.out"));
        int n = r.nextInt();
        String str = "0".repeat(n);
        w.write(str);
        w.write('\n');
        chain.add(str);
        isExist = new boolean[1 << n];
        isExist[0] = true;
        while (true) {
            String buff = chain.get(chain.size() - 1).substring(1);
            String buff1 = new StringBuilder(buff).append(1).toString();
            String buff0 = new StringBuilder(buff).append(0).toString();
            if (!isExist[Integer.parseInt(buff1, 2)]) {
                chain.add(buff1);
                isExist[Integer.parseInt(buff1, 2)] = true;
                w.write(buff1);
                w.write('\n');
            } else if (!isExist[Integer.parseInt(buff0, 2)]) {
                chain.add(buff0);
                isExist[Integer.parseInt(buff0, 2)] = true;
                w.write(buff0);
                w.write('\n');
            } else {
                break;
            }
        }
        w.close();
    }
}
