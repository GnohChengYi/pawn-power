package com.example.pawnpower

import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat

class BoardSetup (val gs: GameScreen, val game: Game) {
    private val pawnSelect: ImageView = gs.findViewById(R.id.selectPawnImage)
    private val knightSelect: ImageView = gs.findViewById(R.id.selectKnightImage)
    private val bishopSelect: ImageView = gs.findViewById(R.id.selectBishopImage)
    private val rookSelect: ImageView = gs.findViewById(R.id.selectRookImage)
    private val queenSelect: ImageView = gs.findViewById(R.id.selectQueenImage)
    private val kingSelect: ImageView = gs.findViewById(R.id.selectKingImage)
    private val sideSelect: Button = gs.findViewById(R.id.whiteBlackButton)

    internal var color: Color = Color.WHITE

    var currentPiece: String = "pawn"
    var canStart: Boolean = false

    fun setSide() {
        color = if ((color == Color.WHITE)) Color.BLACK else Color.WHITE

        if (color == Color.WHITE) {
            pawnSelect.setImageResource(R.drawable.w_pawn)
            knightSelect.setImageResource(R.drawable.w_knight)
            bishopSelect.setImageResource(R.drawable.w_bishop)
            rookSelect.setImageResource(R.drawable.w_rook)
            queenSelect.setImageResource(R.drawable.w_queen)
            kingSelect.setImageResource(R.drawable.w_king)
            sideSelect.text = gs.getString(R.string.white)
            sideSelect.setTextColor(ContextCompat.getColor(gs, R.color.colorBoardDark))
            sideSelect.setBackgroundColor(ContextCompat.getColor(gs, R.color.colorBoardLight))
        } else {
            pawnSelect.setImageResource(R.drawable.b_pawn)
            knightSelect.setImageResource(R.drawable.b_knight)
            bishopSelect.setImageResource(R.drawable.b_bishop)
            rookSelect.setImageResource(R.drawable.b_rook)
            queenSelect.setImageResource(R.drawable.b_queen)
            kingSelect.setImageResource(R.drawable.b_king)
            sideSelect.text = gs.getString(R.string.black)
            sideSelect.setTextColor(ContextCompat.getColor(gs, R.color.colorBoardLight))
            sideSelect.setBackgroundColor(ContextCompat.getColor(gs, R.color.colorBoardDark))
        }
    }

    fun addPiece(selectedSquare: ImageView) {
        val square: Square = selectedSquare.tag as Square
        if (color == Color.WHITE) {
            when (currentPiece) {
                "pawn" -> {
                    selectedSquare.setImageResource(R.drawable.w_pawn)
                    game.board.setPiece(Pawn(Color.WHITE), square.x, square.y)
                }
                "knight" -> {
                    selectedSquare.setImageResource(R.drawable.w_knight)
                    game.board.setPiece(Knight(Color.WHITE), square.x, square.y)
                }
                "bishop" -> {
                    selectedSquare.setImageResource(R.drawable.w_bishop)
                    game.board.setPiece(Bishop(Color.WHITE), square.x, square.y)
                }
                "rook" -> {
                    selectedSquare.setImageResource(R.drawable.w_rook)
                    game.board.setPiece(Rook(Color.WHITE), square.x, square.y)
                }
                "queen" -> {
                    selectedSquare.setImageResource(R.drawable.w_queen)
                    game.board.setPiece(Queen(Color.WHITE), square.x, square.y)
                }
                "king" -> {
                    selectedSquare.setImageResource(R.drawable.w_king)
                    game.board.setPiece(King(Color.WHITE), square.x, square.y)
                }
            }
        } else {
            when (currentPiece) {
                "pawn" -> {
                    selectedSquare.setImageResource(R.drawable.b_pawn)
                    game.board.setPiece(Pawn(Color.BLACK), square.x, square.y)
                }
                "knight" -> {
                    selectedSquare.setImageResource(R.drawable.b_knight)
                    game.board.setPiece(Knight(Color.BLACK), square.x, square.y)
                }
                "bishop" -> {
                    selectedSquare.setImageResource(R.drawable.b_bishop)
                    game.board.setPiece(Bishop(Color.BLACK), square.x, square.y)
                }
                "rook" -> {
                    selectedSquare.setImageResource(R.drawable.b_rook)
                    game.board.setPiece(Rook(Color.BLACK), square.x, square.y)
                }
                "queen" -> {
                    selectedSquare.setImageResource(R.drawable.b_queen)
                    game.board.setPiece(Queen(Color.BLACK), square.x, square.y)
                }
                "king" -> {
                    selectedSquare.setImageResource(R.drawable.b_king)
                    game.board.setPiece(King(Color.BLACK), square.x, square.y)
                }
            }
        }
    }

    fun deletePiece(selectedSquare: ImageView) {
        selectedSquare.setImageDrawable(null)
        val square: Square = selectedSquare.getTag() as Square
        game.board.removePiece(square.x, square.y)
    }

    fun validateSetup() {
        // TODO
        canStart = true
    }
}