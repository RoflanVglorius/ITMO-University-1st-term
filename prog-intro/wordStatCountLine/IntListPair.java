public class IntListPair {
    private IntList lines;
    private IntList columns;

    public IntListPair() {
        lines = new IntList();
        columns = new IntList();
    }

    public void add(int line, int column) {
        lines.add(line);
        columns.add(column);
    }

    public int size() {
        return lines.size();
    }


    public int getLine(int index) {
        return lines.get(index);
    }

    public int getColumn(int index) {
        return columns.get(index);
    }
}
