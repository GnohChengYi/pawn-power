package com.pawnpower;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameSystemTest {
    @Test
    public void testBasicValidMove() {
        Game game = new Game();
        Piece king = new King(Color.WHITE);
        game.board.setPiece(king, 0, 0);

        assertTrue(game.makeMove(0, 0, 1, 1));
        assertEquals(Color.BLACK, game.getCurrentColor());
        assertFalse(game.makeMove(1, 1, 0, 0)); // Not current color
    }

    @Test
    public void testBasicInvalidMove() {
        Game game = new Game();
        Piece king = new King(Color.WHITE);
        game.board.setPiece(king, 0, 0);

        assertFalse(game.makeMove(0, 0, 2, 2));
    }
}