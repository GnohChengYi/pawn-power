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
        // TODO think of a way to enforce exactly one King of each Color
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
//            System.out.println("Start position same as end position");
            return false;
        }
        Piece startPiece = getPiece(startX, startY);
        if (startPiece == null) {
            System.out.println("No piece at start position");
            return false;
        }
        // ignores castling
        if (isPathBlocked(startX, startY, endX, endY)) {
//            System.out.println("Other piece blocking path");
            return false;
        }
        Piece endPiece = getPiece(endX, endY);
        if (endPiece == null) {
            if (!startPiece.isValidMove(startX, startY, endX, endY)) {
//                System.out.println("Invalid move by piece");
                return false;
            }
        } else {
            if (startPiece.color == endPiece.color) {
//                System.out.println("Cannot capture piece of the same color");
                return false;
            }
            if (!startPiece.isValidCapture(startX, startY, endX, endY)) {
//                System.out.println("Invalid capture move by piece");
                return false;
            }
        }
        if (isSelfColorCheckedAfterMove(startX, startY, endX, endY)) {
//            System.out.println("Own color getting checked after move");
            return false;
        }

        return true;
    }

    private boolean isOutOfBoard(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
    }

    private static boolean startSameAsEnd(int startX, int startY, int endX, int endY) {
        return startX == endX && startY == endY;
    }

    private boolean isSelfColorCheckedAfterMove(int startX, int startY, int endX, int endY) {
        Piece movingPiece = getPiece(startX, startY);
        Piece originalEndPiece = getPiece(endX, endY);

        board[startX][startY] = null;
        board[endX][endY] = movingPiece;

        boolean isChecked = isColorChecked(movingPiece.color);

        board[startX][startY] = movingPiece;
        board[endX][endY] = originalEndPiece;

        return isChecked;
    }

    public boolean isColorChecked(Color color) {
        int[] kingXY = getKingPosition(color);
        if (kingXY == null) {
            System.out.println("Cannot find King of color " + color);
            return true;    // when no King, it's considered losing ~ always checked
        }
        int kingX = kingXY[0];
        int kingY = kingXY[1];

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                Piece piece = board[x][y];
                if (piece == null || piece.color == color) continue;
                if (isPathBlocked(x, y, kingX, kingY)) continue;
                if (piece.isValidCapture(x, y, kingX, kingY)) {
                    return true;
                }
            }
        }

        return false;
    }

    // returns only the first King found
    public int[] getKingPosition(Color movingColor) {
        King dummyKing = new King(Color.WHITE);
        char kingSymbol = dummyKing.getSymbol();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                Piece piece = board[x][y];
                if (piece == null || piece.color != movingColor) continue;
                if (piece.getSymbol() == kingSymbol) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    private boolean isPathBlocked(int startX, int startY, int endX, int endY) {
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
