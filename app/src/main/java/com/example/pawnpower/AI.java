package com.example.pawnpower;

import java.util.ArrayList;
import java.util.Random;

public class AI {
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
