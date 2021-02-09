package game;

public interface Position {
    Cell get(int row, int col);

    boolean isValid(Move move);

    int getHeight();

    int getWidth();
}
