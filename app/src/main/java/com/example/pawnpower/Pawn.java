package com.example.pawnpower;

//import androidx.annotation.NonNull;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return 'P';
    }

    @Override
    public int getPoints() {
        return 1;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        int dir = getDirection();
        // ignore en passant and cannot move two squares at first move
        return endX == startX && endY == startY + dir;
    }

    @Override
    public boolean isValidCapture(int startX, int startY, int endX, int endY) {
        int dir = getDirection();
        int dx = Math.abs(endX - startX);
        return (endY == startY + dir) && (dx == 1);
    }

    private int getDirection() {
        return color == Color.WHITE ? -1 : +1;
    }
}
