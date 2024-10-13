package com.example.pawnpower;

public abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract char getSymbol();

    public abstract int getPoints();

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY);

    public boolean isValidCapture(int startX, int startY, int endX, int endY) {
        return isValidMove(startX, startY, endX, endY);
    }

    @Override
    public String toString() {
        String symbolStr = Character.toString(getSymbol());
        String whiteSymbol = symbolStr.toUpperCase();
        String blackSymbol = symbolStr.toLowerCase();
        return color == Color.WHITE ? whiteSymbol : blackSymbol;
    }
}
