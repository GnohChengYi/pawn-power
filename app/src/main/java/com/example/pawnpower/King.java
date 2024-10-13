package com.example.pawnpower;

//import androidx.annotation.NonNull;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return 'K';
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        // Ignores castling

        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

        return dx <= 1 && dy <= 1;
    }
}
