import java.io.*;
import java.util.Scanner;

public class Task5 {
    static Writer w;
    static Scanner r;
    public static void writeArray(int[][] array) throws IOException{
        for (int[] ar : array){
            for (int el : ar){
                w.write(Integer.toString(el));
            }
            w.write('\n');
        }
        w.close();
    }
    public static void main(String[] args) throws IOException {
        r = new Scanner(new FileInputStream("telemetry.in"));
        w = new BufferedWriter(new FileWriter("telemetry.out"));
        int n = r.nextInt();
        int k = r.nextInt();
        int power = 1;
        int count = 0;
        int col = n - 1;
        int[][] gray = new int[(int) Math.pow(k, n)][n];
        while (power < (int) Math.pow(k, n)) {
            int row = 0;
            while (true) {
                if (row < (int) Math.pow(k, n)) {
                    for (int i = 0; i < k; i++) {
                        for (int j = 0; j < power; j++) {
                            gray[row][col] = i;
                            row++;
                        }
                    }
                } else {
                    break;
                }
                if (row < (int) Math.pow(k, n)) {
                    for (int i = k - 1; i >= 0; i--) {
                        for (int j = 0; j < power; j++) {
                            gray[row][col] = i;
                            row++;
                        }
                    }
                } else {
                    break;
                }
            }
            col--;
            power *= k;
        }
        writeArray(gray);
    }
}
/*
00
01
02
12
11
10
20
21
22


00
10
20
21
11
01
02
12
22


000
001
002
012
011
010
020
021
022
122
121
120
110
111
112
102
101
100
200
201
202
212
211
210
220
221
222
 */