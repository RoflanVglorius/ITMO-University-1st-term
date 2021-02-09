package game.player;

import game.Cell;
import game.Move;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(final Position position, Cell cell) {
        Move move;
        do {
            move = new Move(random.nextInt(position.getHeight()), random.nextInt(position.getWidth()), cell);
        }
        while (!position.isValid(move));
        return move;
    }
}
