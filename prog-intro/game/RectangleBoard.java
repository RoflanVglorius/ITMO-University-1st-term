package game;

import java.util.Arrays;

public class RectangleBoard extends GameBoard {

    public RectangleBoard(int m, int n, int k) {
        super(m, n, k);
        for (int i = 0; i < m; i++) {
            Arrays.fill(cells[i], Cell.E);
        }
    }
}
