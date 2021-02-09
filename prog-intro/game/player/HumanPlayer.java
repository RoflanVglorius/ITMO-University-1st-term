package game.player;

import game.Cell;
import game.Main;
import game.Move;
import game.Position;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner scanner;

    public HumanPlayer(final Scanner scanner) {
        this.scanner = scanner;
    }

    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Move makeMove(final Position position, final Cell cell) {
        while (true) {
            System.out.println("Current position:");
            System.out.println(position.toString());
            System.out.println("Enter " + cell + "'s move");
            System.out.println("Row: ");
            Main.checkInput(scanner, "row");
            int row = scanner.nextInt() - 1;
            System.out.println("Column: ");
            Main.checkInput(scanner, "column");
            int col = scanner.nextInt() - 1;
            final Move move = new Move(row, col, cell);
            if (position.isValid(move)) {
                return move;
            }
            System.out.println("Invalid move: row " + (row + 1) + ", column " + (col + 1));
        }
    }
}
