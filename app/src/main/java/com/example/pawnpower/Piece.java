package com.example.pawnpower;

public abstract class Piece {
    protected Color color;
    protected String type;

    public Piece(Color color, String type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY);
}
