package com.example.pawnpower;

public class King extends Piece {
    public King(Color color) {
        super(color, "King");
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        if (startPieceIsNotThis(board, startX, startY)) {
            return false;
        }
        if (startSameAsEnd(startX, startY, endX, endY)) {
            return false;
        }
        if (isOutOfBoard(startX, startY) || isOutOfBoard(endX, endY)) {
            return false;
        }
        if (isInCheckAfterMove(board, startX, startY, endX, endY)) {
            return false;
        }

        // Ignores castling

        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

        return dx <= 1 && dy <= 1;
    }

    private boolean startPieceIsNotThis(Board board, int startX, int startY) {
        Piece startPiece = board.getPiece(startX, startY);
        return !startPiece.type.equals(this.type) || startPiece.color != this.color;
    }

    private static boolean startSameAsEnd(int startX, int startY, int endX, int endY) {
        return startX == endX && startY == endY;
    }

    private boolean isOutOfBoard(int x, int y) {
        return x < 0 || x >= Board.SIZE || y < 0 || y >= Board.SIZE;
    }

    private boolean isInCheckAfterMove(Board board, int startX, int startY, int endX, int endY) {
        // TODO check every opponent piece
        return false; // Placeholder
    }
}


// TODO Similar implementations for Queen, Rook, Bishop, Knight, Pawn
