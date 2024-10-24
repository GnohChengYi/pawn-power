package com.pawnpower;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return 'Q';
    }

    @Override
    public int getPoints() {
        return 9;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx == 0 && dy > 0) || (dy == 0 && dx > 0) || (dx == dy);
    }
}
