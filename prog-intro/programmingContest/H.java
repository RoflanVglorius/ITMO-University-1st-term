import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class H {

    public static int max(int[] array) {
        int maximum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }
        return maximum;
    }

    public static void toSum(int[] array) {
        for (int i = 1; i < array.length; i++) {
            array[i] += array[i - 1];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] queries = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            queries[i] = scanner.nextInt();
            sum += queries[i];
        }
        int max = max(queries);
        int[] f = new int[sum + 1];
        int currentPos = queries[0];
        int count = 1;
        for (int i = 0; i <= sum; i++) {
            if (i < currentPos) {
                f[i] = count;
            } else {
                currentPos += count < n ? queries[count] : 0;
                f[i] = ++count;
            }
        }
        toSum(queries);
        int q = scanner.nextInt();
        HashMap<Integer, Integer> answers = new HashMap<>();
        for (int i = 0; i < q; i++) {
            int t = scanner.nextInt();
            if (t < max) {
                System.out.println("Impossible");
                continue;
            } else if (answers.containsKey(t)) {
                System.out.println(answers.get(t));
                continue;
            }
            currentPos = t;
            count = 0;
            int blocks = 0;
            while (count < n) {
                count += f[currentPos] - 1 - count;
                currentPos = sum - queries[count - 1] >= t ? queries[count - 1] + t : sum;
                blocks += 1;
            }
            answers.put(t, blocks);
            System.out.println(blocks);
        }
    }
}