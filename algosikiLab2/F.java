import java.util.Scanner;

public class F {
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

    public static int binr(int[] a, int x) {
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
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int[] nums1 = new int[n1];
        int[] nums2 = new int[n2];
        for (int j = 0; j < n1; j++) {
            nums1[j] = scanner.nextInt();
        }
        for (int j = 0; j < n2; j++) {
            nums2[j] = scanner.nextInt();
        }
        for (int i = 0; i < nums2.length; i++) {
            int req = nums2[i];
            int l = binl(nums1, req);
            int r = binr(nums1, req);
            if (r == -1) {
                System.out.println(nums1[0]);
            } else if (l == nums1.length) {
                System.out.println(nums1[l - 1]);
            } else {
                if (req - nums1[r] < nums1[l] - req) {
                    System.out.println(nums1[r]);
                } else if (req - nums1[r] > nums1[l] - req) {
                    System.out.println(nums1[l]);
                } else {
                    System.out.println(nums1[r]);
                }
            }
        }
    }
}