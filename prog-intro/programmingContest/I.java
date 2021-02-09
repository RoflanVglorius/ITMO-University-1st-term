import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int xl = Integer.MAX_VALUE;
        int yl = Integer.MAX_VALUE;
        int xr = Integer.MIN_VALUE;
        int yr = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int h = scanner.nextInt();
            if (x - h < xl) {
                xl = x - h;
            }
            if (x + h > xr) {
                xr = x + h;
            }
            if (y - h < yl) {
                yl = y - h;
            }
            if (y + h > yr) {
                yr = y + h;
            }
        }
        System.out.printf("%d %d %d",
                (xl + xr) / 2, (yl + yr) / 2, (int) Math.ceil((double) (Math.max(xr - xl, yr - yl)) / 2));
    }
}