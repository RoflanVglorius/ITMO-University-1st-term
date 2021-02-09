/*
import java.io.*;
import java.util.*;

public class Task12 {
    static Writer w;
    static Scanner r;
    static int n;
    static int k;
    static HashSet<HashSet<HashSet<Integer>>> ans = new HashSet<>();
    static boolean[] used;
    static Integer[] perm;
    static ArrayList<HashSet<Integer>> subsets = new ArrayList<>();


    static void write1(HashSet<HashSet<HashSet<Integer>>> set) throws IOException {
        for (HashSet<HashSet<Integer>> sset : set) {
            write2(sset);
        }
        w.write('\n');
    }

    static void write2(HashSet<HashSet<Integer>> sset) throws IOException {
        for (HashSet<Integer> ssset : sset) {
            write(ssset);
        }
        w.write('\n');
    }

    private static void write(HashSet<Integer> ssset) throws IOException {
        for (Integer integer : ssset) {
            w.write(Integer.toString(integer));
            w.write(" ");
        }
        w.write('\n');
    }

    static void generateSubset(int len) throws IOException {
        if (len >= 1) {
            HashSet<Integer> subset = new HashSet<>(Set.of(Arrays.copyOf(perm, len)));
            subsets.add(subset);
            if (len == n - k + 1) {
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (len == 0 || !used[i] && i + 1 >= perm[len - 1]) {
                used[i] = true;
                perm[len] = i + 1;
                generateSubset(len + 1);
                used[i] = false;
            }
        }
    }

    static void generate(int len, HashSet<Integer> prev, int index, int count, HashSet<HashSet<Integer>> set, HashSet<Integer> inter, boolean[] isUsed2) throws IOException {
        if (len == k) {
            if (count == n) {
                ans.add(set);
            }
            return;
        }
        a:
        for (int i = index + 1; i < subsets.size() - k + 1 + len; i++) {
            boolean[] isUsed = Arrays.copyOf(isUsed2, isUsed2.length);
            HashSet<Integer> nextInter = new HashSet<>(Set.copyOf(inter));
            HashSet<Integer> buff = subsets.get(i);
            for (Integer j : buff) {
                if (isUsed[j - 1]) {
                    continue a;
                } else {
                    isUsed[j - 1] = true;
                }
            }
            nextInter.addAll(buff);
            HashSet<HashSet<Integer>> next = new HashSet<>(Set.copyOf(set));
            next.add(buff);
            generate(len + 1, buff, i, count + buff.size(), next, nextInter, isUsed);
        }
    }


    public static void main(String[] args) throws IOException {
                r = new Scanner(new FileInputStream("input.txt"));
                w = new BufferedWriter(new FileWriter("output.txt"));
//        r = new Scanner(new FileInputStream("part2sets.in"));
//        w = new BufferedWriter(new FileWriter("part2sets.out"));
        n = r.nextInt();
        k = r.nextInt();
        if (k == 1) {
            for (int i = 1; i <= n; i++)
                w.write(Integer.toString(i) + " ");
            w.close();
            return;
        }
        perm = new Integer[n];
        used = new boolean[n];
        generateSubset(0);
        for (int i = 0; i <= subsets.size() - k + 1; i++) {
            boolean[] isUsed = new boolean[n];
            HashSet<Integer> buff = subsets.get(i);
            for (Integer integer : buff) {
                isUsed[integer - 1] = true;
            }
            generate(1, buff, 0, buff.size(), new HashSet<>(Set.of(buff)), new HashSet(Set.copyOf(buff)), isUsed);
        }
        write1(ans);
        w.close();
    }
}
*/
