package com.example.pawnpower;

//import android.util.Log;

public class Game {
    public final Board board;
    private Color currentColor;

    public Game() {
        board = new Board();
        this.currentColor = Color.WHITE;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    // {start,end} x {X,Y} are in [0,7]
    public boolean makeMove(int startX, int startY, int endX, int endY) {

        if (!isValidMove(startX, startY, endX, endY)) {
//            Log.e("Game", "Invalid move");
            return false;
        }

        Piece piece = board.getPiece(startX, startY);
        board.setPiece(null, startX, startY);
        if (canQueen(piece, endY)) {
            piece = new Queen(piece.color);
        }
        board.setPiece(piece, endX, endY);

        currentColor = (currentColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
        return true;
    }

    private boolean canQueen(Piece piece, int y) {
        Pawn dummyPawn = new Pawn(Color.WHITE);
        if (piece.getSymbol() != dummyPawn.getSymbol()) return false;
        if (piece.color == Color.WHITE) return y == Board.SIZE - 1;
        if (piece.color == Color.BLACK) return y == 0;
        return false;
    }

    public boolean isEnded() {
        if (hasValidMove()) {
            System.out.println(currentColor + " to move");
            return false;
        }

        if (board.isColorChecked(currentColor)) {
            System.out.println(currentColor + " is checkmated!");
        } else {
            System.out.println(currentColor + " is stalemated!");
        }

        return true;
    }

    private boolean hasValidMove() {
        for (int startX = 0; startX < Board.SIZE; startX++) {
            for (int startY = 0; startY < Board.SIZE; startY++) {
                for (int endX = 0; endX < Board.SIZE; endX++) {
                    for (int endY = 0; endY < Board.SIZE; endY++) {
                        if (isValidMove(startX, startY, endX, endY)) return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
//        Log.d("Game", String.format("validating move (%d, %d) -> (%d, %d)", startX, startY,
//        endX, endY));
//        System.out.printf("Validating move (%d,%d) -> (%d,%d)\n", startX, startY, endX, endY);
        Piece startPiece = board.getPiece(startX, startY);
        if (startPiece == null) {
//            Log.d("Game", "Start piece is null");
//            System.out.println("Start piece is null");
            return false;
        }
        if (startPiece.color != currentColor) {
//            Log.d("Game", "Not current color");
//            System.out.println("Not current color");
            return false;
        }
        if (!board.isValidMove(startX, startY, endX, endY)) {
//            Log.d("Game", "Invalid move");
//            System.out.println("Invalid move from board");
            return false;
        }
        return true;
    }
}
