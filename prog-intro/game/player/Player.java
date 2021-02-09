package game.player;

import game.Cell;
import game.Move;
import game.Position;

public interface Player {
    Move makeMove(final Position position, Cell cell);
}
