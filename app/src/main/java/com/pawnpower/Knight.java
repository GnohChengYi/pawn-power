package com.pawnpower;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return 'N';
    }

    @Override
    public int getPoints() {
        return 3;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx == 2 && dy == 1) || (dy == 2 && dx == 1);
    }
}
