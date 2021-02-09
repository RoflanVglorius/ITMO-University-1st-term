package game;

import game.player.*;

import java.util.Scanner;

public class Main {

    public static void checkInput(Scanner scanner, String string) {
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Please, enter integer value of " + string);
                scanner.nextLine();
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m;
        int n;
        int k;
        final Game game = new Game(new Player[]{new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer()}, true);
        while (true) {
            System.out.println("Select board type: rhombus / rectangle");
            String boardType = scanner.next();
            if (boardType.equals("rhombus")) {
                System.out.println("Enter diag length and amount of cells in the winning sequence: ");
                checkInput(scanner, "diag length");
                m = scanner.nextInt();
                checkInput(scanner, "winning sequence");
                k = scanner.nextInt();
                game.play(new RhombusBoard(m, k));
                break;
            } else if (boardType.equals("rectangle")) {
                System.out.println("Enter amount of rows, columns and cells in the winning sequence: ");
                checkInput(scanner, "rows");
                m = scanner.nextInt();
                checkInput(scanner, "column");
                n = scanner.nextInt();
                checkInput(scanner, "winning sequence");
                k = scanner.nextInt();
                game.play(new RectangleBoard(m, n, k));
                break;
            } else {
                System.out.println("Please, enter board type in the correct format");
            }
        }
    }
}