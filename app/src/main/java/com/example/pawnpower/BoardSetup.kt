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
    var kingPlaced: Boolean = false
    var pointsLeft = 39

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

        if (!enoughPoints(square)) {
            return
        }

        // Delete piece only if will be replaced
        if (currentPiece != "king" || !kingPlaced) {
            deletePiece(selectedSquare)
        }

        if (color == Color.WHITE) {
            when (currentPiece) {
                "pawn" -> {
                    selectedSquare.setImageResource(R.drawable.w_pawn)
                    game.board.setPiece(Pawn(Color.WHITE), square.x, square.y)
                    pointsLeft -= 1
                }
                "knight" -> {
                    selectedSquare.setImageResource(R.drawable.w_knight)
                    game.board.setPiece(Knight(Color.WHITE), square.x, square.y)
                    pointsLeft -= 3
                }
                "bishop" -> {
                    selectedSquare.setImageResource(R.drawable.w_bishop)
                    game.board.setPiece(Bishop(Color.WHITE), square.x, square.y)
                    pointsLeft -= 3
                }
                "rook" -> {
                    selectedSquare.setImageResource(R.drawable.w_rook)
                    game.board.setPiece(Rook(Color.WHITE), square.x, square.y)
                    pointsLeft -= 5
                }
                "queen" -> {
                    selectedSquare.setImageResource(R.drawable.w_queen)
                    game.board.setPiece(Queen(Color.WHITE), square.x, square.y)
                    pointsLeft -= 9
                }
                "king" -> {
                    if (!kingPlaced) {
                        selectedSquare.setImageResource(R.drawable.w_king)
                        game.board.setPiece(King(Color.WHITE), square.x, square.y)
                        kingPlaced = true
                    }
                }
            }
        } else {
            when (currentPiece) {
                "pawn" -> {
                    selectedSquare.setImageResource(R.drawable.b_pawn)
                    game.board.setPiece(Pawn(Color.BLACK), square.x, square.y)
//                    pointsLeft -= 1
                }
                "knight" -> {
                    selectedSquare.setImageResource(R.drawable.b_knight)
                    game.board.setPiece(Knight(Color.BLACK), square.x, square.y)
//                    pointsLeft -= 3
                }
                "bishop" -> {
                    selectedSquare.setImageResource(R.drawable.b_bishop)
                    game.board.setPiece(Bishop(Color.BLACK), square.x, square.y)
//                    pointsLeft -= 3
                }
                "rook" -> {
                    selectedSquare.setImageResource(R.drawable.b_rook)
                    game.board.setPiece(Rook(Color.BLACK), square.x, square.y)
//                    pointsLeft -= 5
                }
                "queen" -> {
                    selectedSquare.setImageResource(R.drawable.b_queen)
                    game.board.setPiece(Queen(Color.BLACK), square.x, square.y)
//                    pointsLeft -= 9
                }
                "king" -> {
//                    if (!kingPlaced) {
                        selectedSquare.setImageResource(R.drawable.b_king)
                        game.board.setPiece(King(Color.BLACK), square.x, square.y)
//                        kingPlaced = true
//                    }
                }
            }
        }
    }

    private fun enoughPoints(square: Square): Boolean {
        var points = 0
        val piece = game.board.getPiece(square.x, square.y)
        if (piece != null) {
            points += piece.points
        }
        when (currentPiece) {
            "pawn" -> points -= 1
            "knight" -> points -= 3
            "bishop" -> points -= 3
            "rook" -> points -= 5
            "queen" -> points -= 9
            "king" -> points -= 0
        }
        return pointsLeft + points >= 0
    }

    fun deletePiece(selectedSquare: ImageView) {
        val square: Square = selectedSquare.getTag() as Square
        val piece = game.board.getPiece(square.x, square.y) ?: return

        selectedSquare.setImageDrawable(null)
        pointsLeft += piece.points

        if (piece.symbol == 'K') {
            kingPlaced = false
        }

        game.board.removePiece(square.x, square.y)
    }

    fun validateSetup() {
        // TODO
        canStart = true
    }
}