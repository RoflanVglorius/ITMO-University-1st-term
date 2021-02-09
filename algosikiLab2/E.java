import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class E {
    public static int binl(int[] a, int x) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] < x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    public static int binr(int[]a, int x) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int j = 0; j<n; j++){
            nums[j]=scanner.nextInt();
        }
        Arrays.sort(nums);
        int cntreq = scanner.nextInt();
        int[] reqs = new int[2];
        for (int i = 0; i < cntreq; i++) {
            reqs[0]=scanner.nextInt();
            reqs[1]=scanner.nextInt();
            int l = binl(nums, reqs[0]);
            int r = binr(nums, reqs[1]);
            if (l == -1 || r == nums.length) {
                System.out.print(0 + ' ');
            } else {
                int res = r - l + 1;
                System.out.print(res + " ");
            }
        }
    }
}