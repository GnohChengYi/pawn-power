package com.example.pawnpower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleGame {
    private final Game game;

    public ConsoleGame() {
        Player whitePlayer = new Player(Color.WHITE);
        Player blackPlayer = new Player(Color.BLACK);
        game = new Game(whitePlayer, blackPlayer);

        ArrayList<Piece> availablePieces = new ArrayList<>(List.of(new King(Color.WHITE),
                new King(Color.BLACK), new Queen(Color.WHITE), new Queen(Color.BLACK),
                new Bishop(Color.WHITE), new Bishop(Color.BLACK), new Knight(Color.WHITE),
                new Knight(Color.BLACK), new Rook(Color.WHITE), new Rook(Color.BLACK)));
        Random random = new Random();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                // TODO replace setup logic
                if (random.nextFloat() < 0.2 && !availablePieces.isEmpty()) {
                    int randomIndex = random.nextInt(availablePieces.size());
                    Piece randomPiece = availablePieces.remove(randomIndex);
                    game.board.setPiece(randomPiece, i, j);
                }
            }
        }
    }

    public void play() {
        System.out.println("play");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(game.board);
            System.out.println("\nEnter move (e.g., e2-e4) or END:");
            String move = scanner.nextLine().trim();
            if (move.equals("END")) break;
            if (!makeMove(move)) {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // Format e.g. e2-e4
    private boolean makeMove(String move) {
        int startX = move.charAt(0) - 'a';
        int startY = Integer.parseInt(move.substring(1, 2)) - 1;
        int endX = move.charAt(3) - 'a';
        int endY = Integer.parseInt(move.substring(4, 5)) - 1;
        return game.makeMove(startX, startY, endX, endY);
    }

    public static void main(String[] args) {
        ConsoleGame consoleGame = new ConsoleGame();
        consoleGame.play();
    }
}
