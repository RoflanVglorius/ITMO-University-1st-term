import java.util.ArrayList;

public class D {
    static ArrayList<Long> countedR = new ArrayList<>();
    static long[] countedD;
    static long result;

    public static long countResult(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (Math.floorMod(i, j) == 0) {
                    sum += countedD[j - 1];
                }
            }
        }
        return sum;
    }

    public static void preCountR(int n, int k) {
        countedR.add((long) k);
        for (int t = 2; t <= n; t++) {
            long sum = 0;
            for (int i = 0; i < t; i++) {
                long power = 1;
                for (int j = 1; j <= (i) / 2 + (i) % 2 + (t - i) / 2 + (t - i) % 2; j++) {
                    power *= k;
                }
                sum += power;
            }
            countedR.add(sum);
        }
    }

    public static void preCountD(int n, int k) {
        long buff = 0;
            for (int i = 1; i < n; i++) {
                if (Math.floorMod(n, i) == 0) {
                    preCountD(i, k);
                    buff += (n / i) * result;
                }
            }

        result = countedR.get(n - 1) - buff;
        countedD[n - 1] = result;
    }

    public static void main(String[] args) {
        countedD = new long[3];
        preCountR(3, 3);
        for
        preCountD(3, 3);
        System.out.println(countResult(3));
    }
}
