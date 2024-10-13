package com.example.pawnpower;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleGame {
    private final Game game;
    private final Scanner scanner = new Scanner(System.in);
    private final AI ai = new AI();

    public ConsoleGame() {
        game = new Game();

        System.out.println("\nMaximum points for each side (e.g. 39):");
        int maxPoints = scanner.nextInt();
        scanner.nextLine();

        setUpPosition(Color.WHITE, maxPoints);
        setUpPosition(Color.BLACK, maxPoints);
    }

    private void setUpPosition(Color color, int maxPoints) {
        int remainingPoints = maxPoints;
        int startY = (color == Color.WHITE) ? 0 : (Board.SIZE / 2);
        int endY = (color == Color.WHITE) ? (Board.SIZE / 2) : Board.SIZE;  // exclusive
        Random random = new Random();
        List<Piece> nonKingPieces = List.of(new Queen(color), new Bishop(color),
                new Knight(color), new Rook(color), new Pawn(color));

        for (int x = 0; x < Board.SIZE; x++) {
            if (remainingPoints < 0) break;
            for (int y = startY; y < endY; y++) {
                if (random.nextFloat() > 0.2) continue; // randomly skip squares
                int randomIndex = random.nextInt(nonKingPieces.size());
                Piece randomPiece = nonKingPieces.get(randomIndex);
                int points = randomPiece.getPoints();
                if (points > remainingPoints) break;
                remainingPoints -= points;
                game.board.setPiece(randomPiece, x, y);
            }
        }

        King king = new King(color);
        game.board.setPiece(king, 0, startY);   // will overwrite (0,startY), but don't care
    }

    public void play() {
        System.out.println("play");
        System.out.println(game.board);
        if (game.isEnded()) return;
        while (true) {
            System.out.println("\nEnter move (e.g., e2-e4) or END:");
            String move = scanner.nextLine().trim();
            if (move.equals("END")) break;
            if (!makeMove(move)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            System.out.println(game.board);
            if (game.isEnded()) break;

            System.out.println("AI moving ...");
            ai.makeMove(game);
            System.out.println("\n" + game.board);
            if (game.isEnded()) break;
        }
        scanner.close();
    }

    // Format e.g. e2-e4
    private boolean makeMove(String move) {
        try {
            int startX = move.charAt(0) - 'a';
            int startY = Integer.parseInt(move.substring(1, 2)) - 1;
            int endX = move.charAt(3) - 'a';
            int endY = Integer.parseInt(move.substring(4, 5)) - 1;
            return game.makeMove(startX, startY, endX, endY);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        ConsoleGame consoleGame = new ConsoleGame();
        consoleGame.play();
    }
}
