import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Task28 {
    static Scanner s;
    static Writer w;

    static void write(int[] array) throws IOException {
        for (int i : array) {
            w.write(Integer.toString(i));
            w.write(' ');
        }
        w.write('\n');
    }

    static int[] changeAndFlip(int[] array, int start, int change) {
        int buff = array[start - 1];
        array[start - 1] = array[change];
        array[change] = buff;
        int[] buffer = Arrays.copyOf(array, array.length);
        int current = start;
        for (int i = array.length - 1; i >= start; i--) {
            array[current] = buffer[i];
            current++;
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        s = new Scanner(new FileInputStream("input.txt"));
        w = new BufferedWriter(new FileWriter("output.txt"));
        int n = s.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = s.nextInt();
        }
        int toInc = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (array[i] < array[i + 1]) {
                toInc = i;
                break;
            }
        }
        int min = toInc + 1;
        if (toInc >= 0) {
            for (int i = toInc + 1; i < n; i++) {
                if (array[i] > array[toInc] && array[i] <= array[min]) {
                    min = i;
                } else if (array[i] < array[toInc]) {
                    break;
                }
            }
        }
        if (toInc == -1) {
            write(new int[n]);
        } else {
            write(changeAndFlip(array, toInc + 1, min));
        }
        w.close();
    }
}