package game.player;

import game.Cell;
import game.Move;
import game.Position;


public class SequentialPlayer implements Player {

    @Override
    public Move makeMove(final Position position, final Cell cell) {
        for (int row = 0; row < position.getHeight(); row++) {
            for (int col = 0; col < position.getWidth(); col++) {
                if (position.get(row, col) == Cell.E) {
                    final Move move = new Move(row, col, cell);
                    if (position.isValid(move)) {
                        return move;
                    }
                }
            }
        }
        throw new AssertionError("No empty cells");
    }
}
