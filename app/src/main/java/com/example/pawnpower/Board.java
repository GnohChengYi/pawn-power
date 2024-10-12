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

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        if (isOutOfBoard(startX, startY) || isOutOfBoard(endX, endY)) {
            System.out.println("Position is out of board");
            return false;
        }
        if (startSameAsEnd(startX, startY, endX, endY)) {
            System.out.println("Start position same as end position");
            return false;
        }
        Piece startPiece = getPiece(startX, startY);
        if (startPiece == null) {
            System.out.println("No piece at start position");
            return false;
        }
        if (sameColorEndPiece(startPiece, endX, endY)) {
            System.out.println("Start piece is same color as end piece");
            return false;
        }
        if (!startPiece.isValidMove(startX, startY, endX, endY)) {
            System.out.println("Invalid move by piece");
            return false;
        }
        return !isSelfColorCheckAfterMove(startX, startY, endX, endY);
    }

    private boolean isOutOfBoard(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
    }

    private static boolean startSameAsEnd(int startX, int startY, int endX, int endY) {
        return startX == endX && startY == endY;
    }

    private boolean sameColorEndPiece(Piece startPiece, int endX, int endY) {
        Piece endPiece = getPiece(endX, endY);
        if (endPiece == null) {
            return false;
        }
        return startPiece.color == endPiece.color;
    }

    private boolean isSelfColorCheckAfterMove(int startX, int startY, int endX, int endY) {
        // TODO check every opponent piece
        return false; // Placeholder
    }

    @Override
    public String toString() {
        // print from White POV, with row,col
        StringBuilder string = new StringBuilder();
        for (int i = SIZE - 1; i >= 0; i--) {
            string.append(i + 1).append(" ");
            for (int j = 0; j < SIZE; j++) {
                Piece piece = board[j][i];  // swap axis
                String symbol = piece == null ? "-" : piece.toString();
                string.append(symbol).append(" ");
            }
            string.append("\n");
        }
        string.append("  ");
        for (int col = 'a'; col <= 'h'; col++) {
            string.append((char) col).append(" ");
        }
        return string.toString();
    }
}
