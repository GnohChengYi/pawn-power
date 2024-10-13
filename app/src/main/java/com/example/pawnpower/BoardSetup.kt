package com.example.pawnpower

import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat

class BoardSetup (val gs: GameScreen) {
    private val pawnSelect: ImageView = gs.findViewById(R.id.selectPawnImage)
    private val knightSelect: ImageView = gs.findViewById(R.id.selectKnightImage)
    private val bishopSelect: ImageView = gs.findViewById(R.id.selectBishopImage)
    private val rookSelect: ImageView = gs.findViewById(R.id.selectRookImage)
    private val queenSelect: ImageView = gs.findViewById(R.id.selectQueenImage)
    private val kingSelect: ImageView = gs.findViewById(R.id.selectKingImage)
    private val sideSelect: Button = gs.findViewById(R.id.whiteBlackButton)

    private var isWhite: Boolean = true

    var currentPiece: String = "pawn"
    var canStart: Boolean = false

    fun setSide() {
        isWhite = !isWhite
        if (isWhite) {
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
        if (isWhite) {
            when (currentPiece) {
                "pawn" -> selectedSquare.setImageResource(R.drawable.w_pawn)
                "knight" -> selectedSquare.setImageResource(R.drawable.w_knight)
                "bishop" -> selectedSquare.setImageResource(R.drawable.w_bishop)
                "rook" -> selectedSquare.setImageResource(R.drawable.w_rook)
                "queen" -> selectedSquare.setImageResource(R.drawable.w_queen)
                "king" -> selectedSquare.setImageResource(R.drawable.w_king)
            }
        } else {
            when (currentPiece) {
                "pawn" -> selectedSquare.setImageResource(R.drawable.b_pawn)
                "knight" -> selectedSquare.setImageResource(R.drawable.b_knight)
                "bishop" -> selectedSquare.setImageResource(R.drawable.b_bishop)
                "rook" -> selectedSquare.setImageResource(R.drawable.b_rook)
                "queen" -> selectedSquare.setImageResource(R.drawable.b_queen)
                "king" -> selectedSquare.setImageResource(R.drawable.b_king)
            }
        }
    }

    fun deletePiece(selectedSquare: ImageView) {
        selectedSquare.setImageDrawable(null)
    }

    fun validateSetup() {
        // TODO
        canStart = true
    }
}