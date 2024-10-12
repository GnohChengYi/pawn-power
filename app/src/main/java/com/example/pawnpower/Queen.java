package com.example.pawnpower;

//import androidx.annotation.NonNull;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, "Q");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx == 0 && dy > 0) || (dy == 0 && dx > 0) || (dx == dy);
    }
}
