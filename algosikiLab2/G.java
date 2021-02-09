import java.util.Scanner;

public class G {
    public static boolean good(long x, int y, int n, long t) {
        return ((t / x + t / y) >= (n - 1));
    }

    public static long bin(long x, int y, int n, long l, long r) {
        while (r - l > 1) {
            long m = (l + r) / 2;
            if (good(x, y, n, m)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long x = scanner.nextInt();
        int y = scanner.nextInt();
        long l;
        long r;
        if (x > y) {
            r = n * y;
            l = y;
        } else {
            r = n * x;
            l = x;
        }
        if (n >= 2) {
            System.out.println((l + bin(x, y, n, 0, r)));
        } else {
            System.out.println(l);
        }
    }
}
