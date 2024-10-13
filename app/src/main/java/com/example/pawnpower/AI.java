package com.example.pawnpower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI {
    public void setUpPosition(Game game, Color color, int maxPoints) {
        int remainingPoints = maxPoints;
        int startY = (color == Color.WHITE) ? 0 : (Board.SIZE / 2);
        int endY = (color == Color.WHITE) ? (Board.SIZE / 2) : Board.SIZE;  // exclusive
        Random random = new Random();
        List<Piece> nonKingPieces = List.of(new Queen(color), new Bishop(color),
                new Knight(color), new Rook(color), new Pawn(color));

        for (int x = 0; x < Board.SIZE; x++) {
            if (remainingPoints < 0) break;
            for (int y = startY; y < endY; y++) {
                if (random.nextFloat() > 0.7) continue; // randomly skip squares
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

    public void makeMove(Game game) {
        ArrayList<int[]> validMoves = new ArrayList<>();

        for (int startX = 0; startX < Board.SIZE; startX++) {
            for (int startY = 0; startY < Board.SIZE; startY++) {
                for (int endX = 0; endX < Board.SIZE; endX++) {
                    for (int endY = 0; endY < Board.SIZE; endY++) {
                        if (game.isValidMove(startX, startY, endX, endY)) {
                            validMoves.add(new int[]{startX, startY, endX, endY});
                        }
                    }
                }
            }
        }

        int randomIndex = new Random().nextInt(validMoves.size());
        int[] randomMove = validMoves.get(randomIndex);
        game.makeMove(randomMove[0], randomMove[1], randomMove[2], randomMove[3]);
    }
}
