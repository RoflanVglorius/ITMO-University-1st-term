    import java.util.Arrays;
import java.io.IOException;

public class ReverseAvg {
    private static long[] ensureCapacity(int count, long[] array) {
        if (count >= array.length) {
            return Arrays.copyOf(array, array.length * 2);
        } else {
            return array;
        }
    }

    private static int[] ensureCapacity(int count, int[] array) {
        if (count >= array.length) {
            return Arrays.copyOf(array, array.length * 2);
        } else {
            return array;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner.Check check = new Scanner.Check() {
            public boolean isRight(char c) {
                return ((Character.isDigit(c)) || (c == '-'));
            }
        };
        long[] ints = new long[2];  //все элементы
        int[] cntl = new int[2];  //кол-во элементов в каждой строке
        int[] cntc = new int[2];  //кол-во элементов в каждом столбце
        long[] sumColumns = new long[2];  //сумма столбцов
        long[] sumLines = new long[2]; //сумма строк
        int cntNum = 0;  //кол-во чисел
        int cntLines = 0;  //кол-во строк
        int cntColumns = 0;  //кол-во столбцов
        try {
            Scanner scanner = new Scanner(System.in, check);
            try {
                while (scanner.hasToRead()) {
                    int num = 0;
                    while (!scanner.isEndOfLine()) {
                        ints = ensureCapacity(cntNum, ints); //Проверка переполнения
                        sumColumns = ensureCapacity(cntColumns, sumColumns); //Проверка переполнения
                        sumLines = ensureCapacity(cntLines, sumLines); //Проверка переполнения
                        cntc = ensureCapacity(cntColumns, cntc); //Проверка переполнения
                        ints[cntNum] = scanner.nextInt(); //Новый элемент
                        sumLines[cntLines] += ints[cntNum]; //Сумма строк
                        sumColumns[num] += ints[cntNum]; //Сумма столбцов
                        cntc[num]++; //кол-во в столбце
                        cntNum++;  //кол-во символов всего
                        num++;  //кол-во в строке
                        if (num > cntColumns) {
                            cntColumns++;  //кол-во столбцов(если число элементов в строке больше, чем старое кол-во столбцов, то добавляется новый столбец)
                        }
                    }
                    scanner.skipLine();
                    cntl = ensureCapacity(cntLines, cntl);
                    sumLines = ensureCapacity(cntLines, sumLines);
                    cntl[cntLines] = num;  //запись кол-ва символов в строке
                    cntLines++;  //кол-во строк
                }
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Input reader error: " + e.getMessage());
            System.exit(-1);
        }
        int index = 0;
        for (int i = 0; cntLines > i; i++) {
            for (int j = 0; j < cntl[i]; j++) {
                ints[index] = (sumLines[i] + sumColumns[j] - ints[index]) / (cntc[j] + cntl[i] - 1);
                System.out.print(ints[index] + " ");
                index++;
            }
            System.out.println();
        }
    }
}