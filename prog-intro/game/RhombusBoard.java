package game;

public class RhombusBoard extends GameBoard {

    public RhombusBoard(int m, int k) {
        super(m, m, k);
        for (int i = 0; i <= (m - 1) / 2; i++) {
            for (int j = 0; j < m; j++) {
                if (j < i || j >= m - i) {
                    cells[(m - 1) / 2 - i][j] = Cell.N;
                    cells[m / 2 + i][j] = Cell.N;
                    emptyCells -= 2;
                } else {
                    cells[(m - 1) / 2 - i][j] = Cell.E;
                    cells[m / 2 + i][j] = Cell.E;
                }
            }
        }
    }
}
