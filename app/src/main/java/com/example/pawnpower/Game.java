package com.example.pawnpower;

//import android.util.Log;

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

        if (!isValidMove(startX, startY, endX, endY)) {
//            Log.e("Game", "Invalid move");
            return false;
        }

        Piece piece = board.getPiece(startX, startY);
        board.setPiece(null, startX, startY);
        if (canQueen(piece, endX, endY)) {
            piece = new Queen(piece.color);
        }
        board.setPiece(piece, endX, endY);

        currentColor = (currentColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
        return true;
    }

    private boolean isValidMove(int startX, int startY, int endX, int endY) {
//        Log.d("Game", String.format("validating move (%d, %d) -> (%d, %d)", startX, startY,
//        endX, endY));
//        System.out.printf("Validating move (%d,%d) -> (%d,%d)\n", startX, startY, endX, endY);
        Piece startPiece = board.getPiece(startX, startY);
        if (startPiece == null) {
//            Log.d("Game", "Start piece is null");
            System.out.println("Start piece is null");
            return false;
        }
        if (startPiece.color != currentColor) {
//            Log.d("Game", "Not current color");
            System.out.println("Not current color");
            return false;
        }
        if (!board.isValidMove(startX, startY, endX, endY)) {
//            Log.d("Game", "Invalid move");
            System.out.println("Invalid move from board");
            return false;
        }
        return true;
    }

    private boolean canQueen(Piece piece, int x, int y) {
        Pawn dummyPawn = new Pawn(Color.WHITE);
        if (piece.getSymbol() != dummyPawn.getSymbol()) return false;
        if (piece.color == Color.WHITE) return y == Board.SIZE - 1;
        if (piece.color == Color.BLACK) return y == 0;
        return false;
    }

    // TODO check both color King whether checkmate
}
