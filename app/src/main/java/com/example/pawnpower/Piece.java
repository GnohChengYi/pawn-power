package com.example.pawnpower;

public abstract class Piece {
    protected Color color;
    protected String symbol;

    public Piece(Color color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY);

    public boolean isValidCapture(int startX, int startY, int endX, int endY) {
        return isValidMove(startX, startY, endX, endY);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? symbol.toUpperCase() : symbol.toLowerCase();
    }
}
