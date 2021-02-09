import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String first = s.nextLine();
        String second = s.nextLine();
        int[][] d = new int[second.length() + 1][first.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            d[0][i] = i;
        }
        for (int i = 0; i <= second.length(); i++) {
            d[i][0] = i;
        }
        for (int i = 1; i <= second.length(); i++) {
            for (int j = 1; j <= first.length(); j++) {
                if (first.charAt(j-1) == second.charAt(i-1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(d[i - 1][j - 1], d[i - 1][j]);
                    d[i][j] = Math.min(d[i][j], d[i][j - 1]) + 1;
                }
            }
        }
        System.out.println(d[second.length()][first.length()]);
    }
}
