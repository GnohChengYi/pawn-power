package com.example.pawnpower;

public class Board {
    public static final int SIZE = 8;

    private Piece[][] board;

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        board = new Piece[SIZE][SIZE];
        // TODO Initialize pieces (white and black)
    }

    public void setPiece(Piece piece, int x, int y) {
        board[x][y] = piece;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    // Methods for checking valid moves, capturing pieces, etc.
}
