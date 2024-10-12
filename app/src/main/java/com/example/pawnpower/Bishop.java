package com.example.pawnpower;

//import androidx.annotation.NonNull;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, "B");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return dx == dy;
    }
}
