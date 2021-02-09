import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task26 {
    static Scanner s;
    static Writer w;
    static ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
    static int k = -1;
    static int n = -1;
    static ArrayList<Integer> used = new ArrayList<>();
    static int minIndex;
    static int last = -1;

    public static void write() throws IOException {
        w.write(n + " " + sets.size() + '\n');
        for (ArrayList<Integer> set : sets) {
            for (Integer integer : set) {
                w.write(integer + " ");
            }
            w.write('\n');
        }
        w.write('\n');
    }

    public static Integer min(Integer comp) {
        long min = (long) Integer.MAX_VALUE + 1;
        for (int i = 0; i < used.size(); i++) {
            if (used.get(i) < min && comp < used.get(i)) {
                min = used.get(i);
                minIndex = i;
            }
        }
        return min > Integer.MAX_VALUE ? -1 : (int) min;
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("nextsetpartition.in"));
        w = new PrintWriter("nextsetpartition.out");
//        s = new Scanner(new FileInputStream("input.txt"));
//        w = new PrintWriter("output.txt");
        while (n != 0 && k != 0) {
            last = -1;
            minIndex = 0;
            sets = new ArrayList<>();
            used = new ArrayList<>();
            n = s.nextInt();
            k = s.nextInt();
            if (n == 1){
                w.write(1 + " " + 1 + '\n' + 1 + "\n\n");
                s.nextLine();
                s.nextLine();
                continue;
            }
            if (n == 0 || k == 0) {
                w.close();
                return;
            }
            s.nextLine();
            for (int indSet = 0; indSet < k; indSet++) {
                StringBuilder buff = new StringBuilder(s.nextLine());
                ArrayList<Integer> buff2 = new ArrayList<>();
                StringBuilder str = new StringBuilder();
                for (char c : buff.toString().toCharArray()) {
                    if (!Character.isDigit(c)) {
                        buff2.add(Integer.parseInt(str.toString()));
                        str.setLength(0);
                    } else {
                        str.append(c);
                    }
                }
                buff2.add(Integer.parseInt(str.toString()));
                sets.add(buff2);
            }
            a:
            for (int i = sets.size() - 1; i >= 0; i--) {
                boolean add = true;
                ArrayList<Integer> buff = sets.get(i);
                if (used.size() == 0) {
                    used.add(buff.get(buff.size() - 1));
                    buff.remove(buff.size() - 1);
                    add = false;
                }
                if (buff.size() == 0) {
                    sets.remove(i);
                    continue a;
                }
                if (min(buff.get(buff.size() - 1)) != -1 && used.size() != 0 && add) {
                    buff.add(used.get(minIndex));
                    used.remove(minIndex);
                    last = i;
                    break a;
                }

                for (int j = buff.size() - 1; j >= 0; j--) {
                    if (j == 0 || min(buff.get(j)) == -1) {
                        used.add(buff.get(j));
                        buff.remove(j);
                        if(buff.size() == 0)
                        sets.remove(i);
                    } else {
                        used.add(buff.get(j));
                        buff.set(j, used.get(minIndex));
                        used.remove(minIndex);
                        last = i;
                        break a;
                    }
                }
            }
            used.sort(Integer::compareTo);
            for (int j = last + 1; j < last + used.size() + 1; j++) {
                    sets.add(new ArrayList<>(List.of(used.get(j - last - 1))));
            }
            write();
        }
        w.close();
    }
}
