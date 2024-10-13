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

    fun setSide() {
        isWhite = !isWhite
        if (isWhite) {
            pawnSelect.setImageResource(R.drawable.w_pawn)
            knightSelect.setImageResource(R.drawable.w_knight)
            bishopSelect.setImageResource(R.drawable.w_bishop)
            rookSelect.setImageResource(R.drawable.w_rook)
            queenSelect.setImageResource(R.drawable.w_queen)
            kingSelect.setImageResource(R.drawable.w_king)
            sideSelect.text = "WHITE"
            sideSelect.setTextColor(ContextCompat.getColor(gs, R.color.colorBoardDark))
            sideSelect.setBackgroundColor(ContextCompat.getColor(gs, R.color.colorBoardLight))
        } else {
            pawnSelect.setImageResource(R.drawable.b_pawn)
            knightSelect.setImageResource(R.drawable.b_knight)
            bishopSelect.setImageResource(R.drawable.b_bishop)
            rookSelect.setImageResource(R.drawable.b_rook)
            queenSelect.setImageResource(R.drawable.b_queen)
            kingSelect.setImageResource(R.drawable.b_king)
            sideSelect.text = "BLACK"
            sideSelect.setTextColor(ContextCompat.getColor(gs, R.color.colorBoardLight))
            sideSelect.setBackgroundColor(ContextCompat.getColor(gs, R.color.colorBoardDark))
        }
    }
}