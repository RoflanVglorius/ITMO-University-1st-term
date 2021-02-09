import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int current = -710 * 25000;
        for (int i = 0; i < n; i++) {
            System.out.println(current);
            current += 710;
        }
    }
}
