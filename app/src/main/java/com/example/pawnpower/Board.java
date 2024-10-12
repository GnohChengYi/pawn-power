package com.example.pawnpower;

public class Board {
    public static final int SIZE = 8;

    private Piece[][] board;

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        board = new Piece[SIZE][SIZE];
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
        // ignores castling
        if (existsPieceBetween(startX, startY, endX, endY)) {
            System.out.println("Other piece between start and end");
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

    private boolean existsPieceBetween(int startX, int startY, int endX, int endY) {
        // knight probably already handled because it's path is neither straight or diagonal
        if (isXYBlocked(startX, startY, endX, endY)) return true;
        return isDiagonalBlocked(startX, startY, endX, endY);
    }

    private boolean isXYBlocked(int startX, int startY, int endX, int endY) {
        if (startX == endX) {
            int smallY = Math.min(startY, endY);
            int largeY = Math.max(startY, endY);
            for (int y = smallY + 1; y < largeY; y++) {
                if (board[startX][y] != null) return true;
            }
        }
        if (startY == endY) {
            int smallX = Math.min(startX, endX);
            int largeX = Math.max(startX, endX);
            for (int x = smallX + 1; x < largeX; x++) {
                if (board[x][startY] != null) return true;
            }
        }
        return false;
    }

    private boolean isDiagonalBlocked(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

        if (dx != dy) return false; // not diagonal

        int smallX = Math.min(startX, endX);
        int largeX = Math.max(startX, endX);
        int smallY = Math.min(startY, endY);
        int largeY = Math.max(startY, endY);

        for (int x = smallX + 1; x < largeX; x++) {
            for (int y = smallY + 1; y < largeY; y++) {
                if (board[x][y] != null) return true;
            }
        }

        return false;
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
