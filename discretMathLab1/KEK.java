import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class KEK {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] index = new int[n];
        int[] col = new int[k];
        ArrayList<int[]> in = new ArrayList<>();
        String ans = "NO";
        int is = 0;
        for (int i = 0; i < n; i++) {
            int[] s = new int[k];
            for (int t = 0; t < k; t++) {
                s[t] = scanner.nextInt();
            }
            in.add(s);
        }
        if (k == 0) {
            ans = "YES";
        } else if (k == 1) {
            int kek = in.get(0)[0];
            for (int i = 1; i < n; i++)
                if (in.get(i)[0] != kek) {
                    ans = "YES";
                    break;
                }
        } else {
            a:
            while (true) {
                is = 0;
                for (int i = 0; i < n; i++) {
                    if (index[i] != 2) {
                        int[] b = in.get(i);
                        int c = 0;
                        for (int t = 0; t < k; t++) {
                            if (b[t] == -1) {
                                c += 1;
                            }
                        }
                        if (c == k - 1) {
                            index[i] = 1;
                            is++;
                        }
                    }
                }
                if (is == 0) {
                    break a;
                }
                for (int i = 0; i < n; i++) {
                    if (index[i] == 1) {
                        int[] buff = in.get(i);
                        for (int j = 0; j < k; j++) {
                            if (buff[j] != -1 && col[j] == 0) {
                                if (buff[j] == 1) {
                                    buff[j] = 3;
                                    col[j] = 3;
                                } else {
                                    buff[j] = 2;
                                    col[j] = 2;
                                }
                                index[i] = 2;
                            }
                        }
                        in.set(i, buff);
                    }
                }
                for (int i = 0; i < k; i++) {
                    if (col[i] != 0) {
                        for (int j = 0; j < n; j++) {
                            int[] buff = in.get(j);
                            if (buff[i] == 0) {
                                if (col[i] == 2) {
                                    buff[i] = 3;
                                    index[j] = 2;
                                } else {
                                    buff[i] = -1;
                                }
                            } else if (buff[i] == 1) {
                                if (col[i] == 2) {
                                    buff[i] = -1;
                                } else {
                                    buff[i] = 3;
                                    index[j] = 2;
                                }
                            }
                            in.set(j, buff);
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    int[] buff = in.get(i);
                    int count = 0;
                    for (int j = 0; j < k; j++) {
                        if (buff[j] == -1 || buff[j] == 2) {
                            count++;
                        }
                    }

                    if (count == k) {
                        ans = "YES";
                        break a;
                    }
                }
                int checker = 0;
                for (int i = 0; i < n; i++) {
                    if (index[i] != 2) {
                        checker++;
                        break;
                    }
                }
                if (checker == 0) {
                    break a;
                }
            }
        }
        Writer writer = new FileWriter("output.txt", StandardCharsets.UTF_8);
        writer.write(ans);
        writer.close();
    }
}