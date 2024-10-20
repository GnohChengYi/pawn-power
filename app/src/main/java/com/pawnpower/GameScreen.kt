package com.pawnpower

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameScreen : AppCompatActivity() {
    private lateinit var game: Game
    private lateinit var boardSetup: BoardSetup
    private lateinit var pieceSelectImageViews: List<ImageView>

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pointsText: TextView = findViewById(R.id.pointsText)
        val endButton: Button = findViewById(R.id.endButton)
        endButton.visibility = View.GONE

        game = Game()
        boardSetup = BoardSetup(this, game)
        val wbButton: Button = findViewById(R.id.whiteBlackButton)

        wbButton.setOnClickListener {
            boardSetup.setSide()
        }

        setUpPieceSelectImageView()

        val imageViewIds = buildList {
            for (rows in 0..7) {
                for (cols in 0..7) {
                    add("S${rows}${cols}")
                }
            }
        }

        val imageViews = mutableListOf<ImageView>()

        for (id in imageViewIds) {
            val resId = resources.getIdentifier(id, "id", packageName)

            val imageView: ImageView = findViewById(resId)
            imageView.tag = Square(id)
            imageViews.add(imageView)
        }

        var selectedSquare: ImageView = imageViews[0]

        for (imageView in imageViews) {
            imageView.setOnClickListener {
                selectedSquare = imageView
                boardSetup.addPiece(imageView)
                pointsText.text = buildString {
                    append("Points: ")
                    append(boardSetup.pointsLeft)
                }
            }
        }

        val deleteButton: ImageButton = findViewById(R.id.deleteButton)

        deleteButton.setOnClickListener {
            boardSetup.deletePiece(selectedSquare)
            pointsText.text = buildString {
                append("Points: ")
                append(boardSetup.pointsLeft)
            }
        }

        // Start Game

        val startButton: ImageButton = findViewById(R.id.startButton)
        val selectPiecesBar: LinearLayout = findViewById(R.id.selectPiecesBar)
        val setupBottomBar: LinearLayout = findViewById(R.id.setupBottomBar)
        val descriptionText: TextView = findViewById(R.id.descriptionText)

        startButton.setOnClickListener {
            boardSetup.validateSetup()
            if (boardSetup.canStart) {
                selectPiecesBar.visibility = View.GONE
                setupBottomBar.visibility = View.GONE
                descriptionText.text = getString(R.string.game_on)
                for (imageView in imageViews) {
                    imageView.setOnClickListener(null)
                }

                // Play Chess

//                val ai: AI = AI()
//
//                ai.setUpPosition(
//                    game,
//                    if ((boardSetup.color == Color.WHITE)) Color.BLACK else Color.WHITE,
//                    39)
                // TODO Return ai setup to affect frontend

//                if (game.currentColor != boardSetup.color) {
//                    // AI turn
//                    ai.makeMove(game)
//                }

                var selectedPiece: ImageView = imageViews[0]
                var fromSquare = Square("S00")
                var pieceSelected = false

                for (imageView in imageViews) {
                    imageView.setOnClickListener {
                        val square: Square = imageView.tag as Square
                        val piece = game.board.getPiece(square.x, square.y)
//                        if (piece != null && piece.color == boardSetup.color) { // Use this instead of below when AI is ready
                        if (piece != null) {
                            // Select own piece
                            selectedPiece = imageView
                            fromSquare = square
                            pieceSelected = true
                        } else if (pieceSelected && game.isValidMove(
                                fromSquare.x, fromSquare.y,
                                square.x, square.y
                            )
                        ) {
                            // Make move
                            game.makeMove(
                                fromSquare.x, fromSquare.y,
                                square.x, square.y
                            )
                            imageView.setImageDrawable(selectedPiece.drawable)
                            // TODO if pawn has become queen, update image
                            selectedPiece.setImageDrawable(null)

                            pieceSelected = false

                            // AI turn
//                            ai.makeMove(game)
                            // TODO Return move to affect frontend

                            if (game.isEnded()) {
                                descriptionText.text = getString(R.string.white_wins)
                                // TODO specify game end state
                                endButton.visibility = View.VISIBLE
                                pointsText.visibility = View.GONE
                                endButton.setOnClickListener {
                                    this.finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpPieceSelectImageView() {
        val pawnSelect: ImageView = findViewById(R.id.selectPawnImage)
        val knightSelect: ImageView = findViewById(R.id.selectKnightImage)
        val bishopSelect: ImageView = findViewById(R.id.selectBishopImage)
        val rookSelect: ImageView = findViewById(R.id.selectRookImage)
        val queenSelect: ImageView = findViewById(R.id.selectQueenImage)
        val kingSelect: ImageView = findViewById(R.id.selectKingImage)

        pieceSelectImageViews =
            listOf(pawnSelect, knightSelect, bishopSelect, rookSelect, queenSelect, kingSelect)

        setPieceSelectListener(pawnSelect, "pawn")
        setPieceSelectListener(knightSelect, "knight")
        setPieceSelectListener(bishopSelect, "bishop")
        setPieceSelectListener(rookSelect, "rook")
        setPieceSelectListener(queenSelect, "queen")
        setPieceSelectListener(kingSelect, "king")
    }

    private fun setPieceSelectListener(pieceSelect: ImageView, pieceName: String) {
        // https://developer.android.com/reference/android/graphics/Color#LTGRAY
        val colorGray = -3355444

        pieceSelect.setOnClickListener {
            pieceSelectImageViews.forEach { imageView ->
                imageView.clearColorFilter()
            }
            boardSetup.currentPiece = pieceName
            pieceSelect.setColorFilter(colorGray)
        }
    }
}