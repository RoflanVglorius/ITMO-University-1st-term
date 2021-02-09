import java.util.Arrays;

public class IntList {
    private int[] list;
    private int size = 0;

    public IntList() {
        list = new int[16];
    }

    public int[] copy() {
        return list;
    }

    public void add(int num) {
        if (size >= list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        list[size] = num;
        size++;
    }

    public int get(int index) {
        return list[index];
    }

    public int size() {
        return size;
    }
}
