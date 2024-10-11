package com.example.pawnpower;

import android.util.Log;

public class Game {
    public final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Color currentColor;

    public Game(Player whitePlayer, Player blackPlayer) {
        board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentColor = Color.WHITE;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    // {start,end} x {X,Y} are in [0,7]
    public boolean makeMove(int startX, int startY, int endX, int endY) {
        Piece startPiece = board.getPiece(startX, startY);

        if (!isValidMove(startPiece, startX, startY, endX, endY)) {
//            Log.e("Game", "Invalid move");
            return false;
        }

        board.setPiece(null, startX, startY);
        board.setPiece(startPiece, endX, endY);

        currentColor = (currentColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

        // TODO handle Queening

        return true;
    }

    private boolean isValidMove(Piece startPiece, int startX, int startY, int endX, int endY) {
//        Log.d("Game", String.format("validating move (%d, %d) -> (%d, %d)", startX, startY, endX, endY));
        if (startPiece == null) {
//            Log.d("Game", "Start piece is null");
            return false;
        }
        if (startPiece.color != currentColor) {
//            Log.d("Game", "Not current color");
            return false;
        }
        if (!startPiece.isValidMove(board, startX, startY, endX, endY)) {
//            Log.d("Game", "Invalid move");
            return false;
        }
        Piece endPiece = board.getPiece(endX, endY);
        if (endPiece != null && endPiece.color == startPiece.color) {
//            Log.d("Game", "Cannot capture piece of same color");
            return false;
        }
        return true;
    }
}


