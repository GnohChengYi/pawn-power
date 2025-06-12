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
    private lateinit var boardImageViews: List<ImageView>
    private lateinit var pointsText: TextView
    private var selectedSetupSquare: ImageView? = null

    // Store the color resource IDs for convenience
    private var colorBoardDarkResId: Int = 0
    private var colorBoardLightResId: Int = 0
    private var colorHighlightSetupResId: Int = 0
    private var colorHighlightPlayResId: Int = 0


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

        // Initialize color resource IDs
        colorBoardDarkResId = R.color.colorBoardDark
        colorBoardLightResId = R.color.colorBoardLight
        colorHighlightSetupResId = android.R.color.holo_blue_light
        colorHighlightPlayResId = android.R.color.holo_green_light

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
                // When deleting, the square is no longer selected, restore its original color
                restoreOriginalSquareColor(currentSelectedImageView)
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
                // Clear setup phase listeners and restore original colors if any were highlighted
                boardImageViews.forEach {
                    it.setOnClickListener(null)
                    // Ensure all squares are back to their original color before starting gameplay
                    restoreOriginalSquareColor(it)
                }

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
                                // Restore original color of the source square
                                selectedPieceImageViewDuringPlay?.let {
                                    restoreOriginalSquareColor(
                                        it
                                    )
                                }

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
                                selectedPieceImageViewDuringPlay?.let {
                                    restoreOriginalSquareColor(
                                        it
                                    )
                                }
                                selectedPieceImageViewDuringPlay = clickedSquareImageView
                                fromSquareDuringPlay = squareTag
                                // Highlight new selection
                                selectedPieceImageViewDuringPlay?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this,
                                        colorHighlightPlayResId
                                    )
                                )
                            } else { // Invalid move or clicking empty square, so deselect
                                // Restore original color of previously selected piece's square
                                selectedPieceImageViewDuringPlay?.let {
                                    restoreOriginalSquareColor(
                                        it
                                    )
                                }

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
                                    colorHighlightPlayResId
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
        val precomputedImageViewIds = arrayOf(
            R.id.S00, R.id.S01, R.id.S02, R.id.S03, R.id.S04, R.id.S05, R.id.S06, R.id.S07,
            R.id.S10, R.id.S11, R.id.S12, R.id.S13, R.id.S14, R.id.S15, R.id.S16, R.id.S17,
            R.id.S20, R.id.S21, R.id.S22, R.id.S23, R.id.S24, R.id.S25, R.id.S26, R.id.S27,
            R.id.S30, R.id.S31, R.id.S32, R.id.S33, R.id.S34, R.id.S35, R.id.S36, R.id.S37,
            R.id.S40, R.id.S41, R.id.S42, R.id.S43, R.id.S44, R.id.S45, R.id.S46, R.id.S47,
            R.id.S50, R.id.S51, R.id.S52, R.id.S53, R.id.S54, R.id.S55, R.id.S56, R.id.S57,
            R.id.S60, R.id.S61, R.id.S62, R.id.S63, R.id.S64, R.id.S65, R.id.S66, R.id.S67,
            R.id.S70, R.id.S71, R.id.S72, R.id.S73, R.id.S74, R.id.S75, R.id.S76, R.id.S77
        )

        val mutableImageViews = mutableListOf<ImageView>()
        for ((index, resId) in precomputedImageViewIds.withIndex()) {
            if (resId != 0) {
                val imageView: ImageView = findViewById(resId)
                imageView.tag = Square("S${index / 8}${index % 8}")
                mutableImageViews.add(imageView)

                // Set initial alternating background color
                restoreOriginalSquareColor(imageView) // Use the helper to set initial color

                imageView.setOnClickListener { view ->
                    handleSquareClick(view as ImageView)
                }
            }
        }
        boardImageViews = mutableImageViews.toList()
    }

    // Helper function to restore the original background color of a square
    private fun restoreOriginalSquareColor(imageView: ImageView) {
        val square = imageView.tag as? Square
        square?.let {
            imageView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    getOriginalSquareColorResId(it)
                )
            )
        }
    }

    // Helper function to get the original color of a square
    private fun getOriginalSquareColorResId(square: Square): Int {
        return if ((square.x + square.y) % 2 == 0) {
            colorBoardLightResId
        } else {
            colorBoardDarkResId
        }
    }

    private fun handleSquareClick(clickedImageView: ImageView) {
        clearSelectedSetupSquareHighlight()
        selectedSetupSquare = clickedImageView
        highlightSelectedSetupSquare()
        // boardSetup.addPiece expects an ImageView
        boardSetup.addPiece(clickedImageView)
        updatePointsText()
    }

    private fun clearSelectedSetupSquareHighlight() {
        // Now, instead of setting to transparent, restore its original color
        selectedSetupSquare?.let {
            restoreOriginalSquareColor(it)
        }
    }

    private fun highlightSelectedSetupSquare() {
        selectedSetupSquare?.setBackgroundColor(
            ContextCompat.getColor(
                this,
                colorHighlightSetupResId
            ) // Use the defined highlight color
        )
    }

    private fun updatePointsText() {
        pointsText.text = buildString {
            append(getString(R.string.points_label))
            append(boardSetup.pointsLeft)
        }
    }
}
