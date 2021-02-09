import java.io.*;
import java.util.Scanner;

public class Task1 {
    static Writer w;
    static Scanner r;
    static void generate(String s, int n) throws IOException{
        if(s.length() < n){
            generate(s + 0, n);
            generate(s + 1, n);
        } else {
            w.write(s + '\n');
        }
    }
    public static void main(String[] args) throws IOException{
        w = new BufferedWriter(new FileWriter("allvectors.out"));
        r = new Scanner(new FileInputStream("allvectors.in"));
        generate(new String(), r.nextInt());
        w.close();
    }
}
