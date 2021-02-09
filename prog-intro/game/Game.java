package game;

import game.player.Player;

public class Game {
    private final Player[] players;
    private final boolean enableLogging;

    public Game(Player[] players, boolean enableLogging) {
        this.enableLogging = enableLogging;
        this.players = players;
    }

    public void play(final GameBoard gameBoard) {
        int result;
        gameBoard.setPlayersCount(players.length);
        turns:
        while (true) {
            for (Player player : this.players) {
                result = makeMove(gameBoard, gameBoard.getTurn().getIndex(), player);
                if (result != -1) {
                    break turns;
                }
            }
        }
        System.out.println("Final position: \n" + gameBoard.getPosition());
        if (result == 0) {
            System.out.println("Game result: draw");
        } else if (result < -1) {
            System.out.println("Cheating detected. Game stopped");
        } else {
            System.out.println("Game result: player " + result + " won");
        }
    }

    private int makeMove(final GameBoard GameBoard, final int no, final Player player) {
        final Position position = GameBoard.getPosition();
        final Move move = player.makeMove(position, GameBoard.getTurn());
        if (enableLogging) {
            System.out.println(GameBoard.getPosition());
            System.out.println("Move: " + move);
        }
        final Result result = GameBoard.makeMove(move);
        if (result == Result.WIN) {
            return no;
        } else if (result == Result.LOSE) {
            return -2;
        } else if (result == Result.DRAW) {
            return 0;
        } else if (result == Result.UNKNOWN) {
            return -1;
        }
        throw new AssertionError("Unknown result type " + result);
    }
}
