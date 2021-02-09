import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    private static int[] biggerArrayNum(int[] array) {
        return Arrays.copyOf(array, array.length * 2);
    }

    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return ((Character.isDigit(c)) || (c == '-'));
            }
        };
        int[] ints = new int[16];
        int[] cnt = new int[16];
        int cntnum = 0;
        int cntstr = 0;
        try {
            Scanner scanner = new Scanner(System.in, check);
            try {
                while (scanner.hasToRead()) {
                    int num = 0;
                    while (!(scanner.isEndOfLine())) {
                        if (cntnum == ints.length) {
                            ints = biggerArrayNum(ints);
                        }
                        if (cntstr == cnt.length) {
                            cnt = biggerArrayNum(cnt);
                        }
                        ints[cntnum] = scanner.nextInt();
                        cntnum++;
                        num++;
                    }
                    scanner.skipLine();
                    if (cntnum == ints.length) {
                        ints = biggerArrayNum(ints);
                    }
                    if (cntstr == cnt.length) {
                        cnt = biggerArrayNum(cnt);
                    }
                    cnt[cntstr] = num;
                    cntstr++;
                }
            } finally {
                scanner.close();
            }
        } catch (
                IOException e) {
            System.out.println("Input reader error: " + e.getMessage());
            System.exit(-1);
        }
        for (
                int i = cntstr;
                i > 0; i--) {
            for (int j = 0; j < cnt[cntstr - 1]; j++) {
                System.out.print(ints[cntnum - 1] + " ");
                cntnum--;
            }
            cntstr--;
            System.out.println();
        }
    }
}