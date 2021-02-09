import java.util.Comparator;
import java.util.Scanner;

public class H {
    public static boolean good(long w, long h, long n, long m) {
        return ((m/w)*(m/h) >= n);
    }

    public static long bin(long w, long h, long n, long l, long r) {
        while (r - l > 1) {
            long m = (l + r) / 2;
            if (good(w,h,n,m)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long w = scanner.nextInt();
        long h = scanner.nextInt();
        long n = scanner.nextInt();
        long r;
        if (w > h) {
            r = n * w;
        } else {
            r = n * h;
        }
        System.out.println(bin(w,h,n,0,r));
    }
}


