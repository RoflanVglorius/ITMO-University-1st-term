package game;

import java.util.ArrayList;
import java.util.EnumSet;

public class GameBoard {

    protected class CurrentPosition implements Position {
        @Override
        public Cell get(int row, int col) {
            return cells[row][col];
        }

        @Override
        public boolean isValid(Move move) {
            return 0 <= move.getRow() && move.getRow() < m
                    && 0 <= move.getCol() && move.getCol() < n
                    && get(move.getRow(), move.getCol()) == Cell.E
                    && move.getValue() == turn;
        }

        @Override
        public String toString() {
            int whitespacesFromCol = (int) Math.log10(n);
            int whitespacesFromRow = (int) Math.log10(m);
            StringBuilder sb = new StringBuilder(" ");
            sb.append(" ".repeat(whitespacesFromRow));
            for (int i = 1; i <= n; i++) {
                sb.append(i);
                sb.append(" ".repeat(whitespacesFromCol * 2 - (int) Math.log10(i)));

            }
            sb.append("\n");
            for (int i = 1; i <= m; i++) {
                sb.append(i);
                sb.append(" ".repeat(whitespacesFromRow - (int) Math.log10(i)));
                for (Cell cell : cells[i - 1]) {
                    sb.append(cell);
                    sb.append(" ".repeat(whitespacesFromCol * 2));
                }
                sb.append("\n");
            }
            return sb.toString();
        }


        @Override
        public int getWidth() {
            return n;
        }

        @Override
        public int getHeight() {
            return m;
        }
    }

    protected Cell[][] cells;
    protected Cell turn;
    private final int m;
    private final int n;
    private final int k;
    protected int emptyCells;
    private int playersCount;
    private final CurrentPosition position;
    private final ArrayList<Cell> possibleCells;
    private final int[][] directions = new int[][]{
            {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    protected GameBoard(int m, int n, int k) {
        cells = new Cell[m][n];
        emptyCells = m * n;
        this.m = m;
        this.n = n;
        this.k = k;
        position = new CurrentPosition();
        turn = Cell.X;
        possibleCells = new ArrayList<>(EnumSet.allOf(Cell.class));
    }

    private boolean checkDirection(int row, int col, int rowStep, int colStep) {
        return (countCells(row, col, rowStep, colStep) + countCells(row, col, -rowStep, -colStep) - 1 >= k);
    }

    private int countCells(int row, int col, int rowDirection, int colDirection) {
        int count = 0;
        while (row >= 0 && row < m && col >= 0 && col < n && position.get(row, col) == turn) {
            count++;
            row += rowDirection;
            col += colDirection;
        }
        return count;
    }

    public Cell getTurn() {
        return turn;
    }

    protected Result makeMove(final Move move) {
        if (!position.isValid(move)) {
            return Result.LOSE;
        }
        int row = move.getRow();
        int col = move.getCol();
        cells[row][col] = move.getValue();
        emptyCells--;
        for (int[] direction : directions) {
            if (checkDirection(row, col, direction[0], direction[1])) {
                return Result.WIN;
            }
        }
        if (emptyCells == 0) {
            return Result.DRAW;
        }
        skipTurn();
        return Result.UNKNOWN;
    }

    public Position getPosition() {
        return position;
    }

    private void skipTurn() {
        turn = possibleCells.get(turn.getIndex() % playersCount);
    }

    protected void setPlayersCount(int count) {
        if (count > possibleCells.size() - 2 || count < 1) {
            throw new IllegalArgumentException(count + " players are not supported");
        }
        playersCount = count;
    }
}