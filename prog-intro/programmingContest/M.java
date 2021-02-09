import java.util.HashMap;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int t = 0; t < n; t++) {
            int input = scanner.nextInt();
            int[] difficulty = new int[input];
            int count = 0;
            for (int fill = 0; fill < input; fill++) {
                difficulty[fill] = scanner.nextInt();
            }
            HashMap<Integer, Integer> solutions = new HashMap<>();
            for (int j = input - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    count += solutions.getOrDefault(2 * difficulty[j] - difficulty[i], 0);
                }
                solutions.put(difficulty[j], solutions.getOrDefault(difficulty[j], 0) + 1);
            }
            System.out.println(count);
        }
    }
}