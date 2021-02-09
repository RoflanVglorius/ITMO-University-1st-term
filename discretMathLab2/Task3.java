import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {
    static Writer w;
    static Scanner r;
    public static void writeList(ArrayList<Integer> a) throws IOException{
        for (Integer i : a){
            w.write(i.toString());
        }
        w.write('\n');
    }
    public static ArrayList<Integer> shift(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == 2) {
                a.set(i, 0);
            } else {
                a.set(i, a.get(i) + 1);
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("antigray.in"));
        w = new BufferedWriter(new FileWriter("antigray.out"));
        ArrayList<ArrayList<Integer>> ag = new ArrayList<>();
        int n = r.nextInt();
        for (int i = 0; i < (int) Math.pow(3, n - 1); i++) {
            StringBuilder s = new StringBuilder(Integer.toString(i, 3));
            while (s.length() < n) {
                s.insert(0, 0);
            }
            ArrayList<Integer> buff = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                buff.add(Character.getNumericValue(s.charAt(j)));
            }
            ag.add(buff);
        }
        for (ArrayList<Integer> list : ag){
            for (int j = 0; j < 3; j++){
                writeList(list);
                list = shift(list);
            }
        }
        w.close();
    }
}
