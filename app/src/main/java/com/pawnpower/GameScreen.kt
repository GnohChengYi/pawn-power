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
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameScreen : AppCompatActivity() {
    private lateinit var game: Game
    private lateinit var boardSetup: BoardSetup
    private lateinit var pieceSelectImageViews: List<ImageView>
    private lateinit var boardImageViews: List<ImageView> // Stores all board square ImageViews
    private lateinit var pointsText: TextView

    // Dedicated member for the square selected during the setup phase
    private var selectedSetupSquare: ImageView? = null

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

        pointsText = findViewById(R.id.pointsText)
        val endButton: Button = findViewById(R.id.endButton)
        endButton.visibility = View.GONE

        game = Game()
        boardSetup = BoardSetup(this, game)
        val wbButton: Button = findViewById(R.id.whiteBlackButton)

        wbButton.setOnClickListener {
            boardSetup.setSide()
            clearSelectedSetupSquareHighlight()
            selectedSetupSquare = null
        }

        setUpPieceSelectImageView()
        setUpBoardSquaresForSetup()

        val deleteButton: ImageButton = findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener {
            selectedSetupSquare?.let { currentSelectedImageView ->
                boardSetup.deletePiece(currentSelectedImageView)
                updatePointsText()
                clearSelectedSetupSquareHighlight()
                selectedSetupSquare = null
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
                clearSelectedSetupSquareHighlight() // Clear any setup selection before starting
                selectedSetupSquare = null

                selectPiecesBar.visibility = View.GONE
                setupBottomBar.visibility = View.GONE
                descriptionText.text = getString(R.string.game_on)
                boardImageViews.forEach { it.setOnClickListener(null) }

                var selectedPieceImageViewDuringPlay: ImageView? = null
                var fromSquareDuringPlay: Square? = null
                var isPieceSelectedDuringPlay = false

                boardImageViews.forEach { boardSquareImageView ->
                    boardSquareImageView.setOnClickListener innerClickListener@{ view ->
                        val clickedSquareImageView = view as ImageView
                        val squareTag =
                            clickedSquareImageView.tag as? Square ?: return@innerClickListener
                        val piece = game.board.getPiece(squareTag.x, squareTag.y)

                        if (isPieceSelectedDuringPlay && fromSquareDuringPlay != null) {
                            if (game.isValidMove(
                                    fromSquareDuringPlay!!.x,
                                    fromSquareDuringPlay!!.y,
                                    squareTag.x,
                                    squareTag.y
                                )
                            ) {
                                game.makeMove(
                                    fromSquareDuringPlay!!.x,
                                    fromSquareDuringPlay!!.y,
                                    squareTag.x,
                                    squareTag.y
                                )
                                clickedSquareImageView.setImageDrawable(
                                    selectedPieceImageViewDuringPlay?.drawable
                                )
                                selectedPieceImageViewDuringPlay?.setImageDrawable(null)
                                selectedPieceImageViewDuringPlay?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this,
                                        android.R.color.transparent
                                    )
                                )

                                isPieceSelectedDuringPlay = false
                                selectedPieceImageViewDuringPlay = null
                                fromSquareDuringPlay = null

                                if (game.isEnded()) {
                                    descriptionText.text = getString(R.string.white_wins)
                                    endButton.visibility = View.VISIBLE
                                    pointsText.visibility = View.GONE
                                    endButton.setOnClickListener {
                                        this.finish()
                                    }
                                    boardImageViews.forEach { it.setOnClickListener(null) }
                                }
                            } else if (piece != null && piece.color == boardSetup.color) { // Selecting another of own pieces
                                selectedPieceImageViewDuringPlay?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this,
                                        android.R.color.transparent
                                    )
                                )
                                selectedPieceImageViewDuringPlay = clickedSquareImageView
                                fromSquareDuringPlay = squareTag
                                selectedPieceImageViewDuringPlay?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this,
                                        android.R.color.holo_green_light
                                    )
                                )
                            } else {
                                selectedPieceImageViewDuringPlay?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this,
                                        android.R.color.transparent
                                    )
                                )
                                isPieceSelectedDuringPlay = false
                                selectedPieceImageViewDuringPlay = null
                                fromSquareDuringPlay = null
                            }
                        } else if (piece != null /* && piece.color == boardSetup.color */) { // Initial piece selection
                            selectedPieceImageViewDuringPlay = clickedSquareImageView
                            fromSquareDuringPlay = squareTag
                            isPieceSelectedDuringPlay = true
                            selectedPieceImageViewDuringPlay?.setBackgroundColor(
                                ContextCompat.getColor(
                                    this,
                                    android.R.color.holo_green_light
                                )
                            )
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

    @SuppressLint("DiscouragedApi")
    private fun setUpBoardSquaresForSetup() {
        val imageViewIds = buildList {
            for (rows in 0..7) {
                for (cols in 0..7) {
                    add("S${rows}${cols}")
                }
            }
        }

        val mutableImageViews = mutableListOf<ImageView>()
        for (idString in imageViewIds) {
            val resId = resources.getIdentifier(idString, "id", packageName)
            if (resId != 0) {
                val imageView: ImageView = findViewById(resId)
                imageView.tag = Square(idString)
                mutableImageViews.add(imageView)

                imageView.setOnClickListener { view ->
                    val clickedImageView = view as ImageView

                    clearSelectedSetupSquareHighlight()
                    selectedSetupSquare = clickedImageView
                    highlightSelectedSetupSquare()

                    // boardSetup.addPiece expects an ImageView
                    boardSetup.addPiece(clickedImageView)
                    updatePointsText()
                }
            }
        }
        boardImageViews = mutableImageViews.toList()
    }

    private fun clearSelectedSetupSquareHighlight() {
        selectedSetupSquare?.setBackgroundColor(
            ContextCompat.getColor(this, android.R.color.transparent)
        )
    }

    private fun highlightSelectedSetupSquare() {
        selectedSetupSquare?.setBackgroundColor(
            ContextCompat.getColor(this, android.R.color.holo_blue_light)
        )
    }

    private fun updatePointsText() {
        pointsText.text = buildString {
            append(getString(R.string.points_label))
            append(boardSetup.pointsLeft)
        }
    }
}
