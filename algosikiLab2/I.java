import java.util.Scanner;

public class I {

    public static double bin(double n, double l, double r) {
        for (int i = 0; i<100; i++) {
            double m = (l + r) / 2;
            if ((Math.sqrt(m) + m * m) > n) {
                r = m;
            } else if ((Math.sqrt(m) + m * m) < n) {
                l = m;
            } else {return m;}
        }
        return (l+r)/2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n = scanner.nextDouble();
        double r = Math.sqrt(n);
        System.out.println(bin(n, 0, r));
    }
}
