package com.example.pawnpower;

public class AI {
    public void makeMove(Game game) {
        // make the first move found
        for (int startX = 0; startX < Board.SIZE; startX++) {
            for (int startY = 0; startY < Board.SIZE; startY++) {
                for (int endX = 0; endX < Board.SIZE; endX++) {
                    for (int endY = 0; endY < Board.SIZE; endY++) {
                        if (game.isValidMove(startX, startY, endX, endY)) {
                            game.makeMove(startX, startY, endX, endY);
                            return;
                        }
                    }
                }
            }
        }
    }
}
