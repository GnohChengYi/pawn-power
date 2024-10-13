package com.example.pawnpower

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gameScreen = BoardSetup(this)
        val wbButton: Button = findViewById(R.id.whiteBlackButton)

        wbButton.setOnClickListener {
            gameScreen.setSide()
        }

        val pawnSelect: ImageView = findViewById(R.id.selectPawnImage)
        val knightSelect: ImageView = findViewById(R.id.selectKnightImage)
        val bishopSelect: ImageView = findViewById(R.id.selectBishopImage)
        val rookSelect: ImageView = findViewById(R.id.selectRookImage)
        val queenSelect: ImageView = findViewById(R.id.selectQueenImage)
        val kingSelect: ImageView = findViewById(R.id.selectKingImage)

        pawnSelect.setOnClickListener {
            gameScreen.currentPiece = "pawn"
        }

        knightSelect.setOnClickListener {
            gameScreen.currentPiece = "knight"
        }

        bishopSelect.setOnClickListener {
            gameScreen.currentPiece = "bishop"
        }

        rookSelect.setOnClickListener {
            gameScreen.currentPiece = "rook"
        }

        queenSelect.setOnClickListener {
            gameScreen.currentPiece = "queen"
        }

        kingSelect.setOnClickListener {
            gameScreen.currentPiece = "king"
        }

        val r00: ImageView = findViewById(R.id.R00)
        val r01: ImageView = findViewById(R.id.R01)
        val r02: ImageView = findViewById(R.id.R02)
        val r03: ImageView = findViewById(R.id.R03)
        val r04: ImageView = findViewById(R.id.R04)
        val r05: ImageView = findViewById(R.id.R05)
        val r06: ImageView = findViewById(R.id.R06)
        val r07: ImageView = findViewById(R.id.R07)
        val r10: ImageView = findViewById(R.id.R10)
        val r11: ImageView = findViewById(R.id.R11)
        val r12: ImageView = findViewById(R.id.R12)
        val r13: ImageView = findViewById(R.id.R13)
        val r14: ImageView = findViewById(R.id.R14)
        val r15: ImageView = findViewById(R.id.R15)
        val r16: ImageView = findViewById(R.id.R16)
        val r17: ImageView = findViewById(R.id.R17)
        val r20: ImageView = findViewById(R.id.R20)
        val r21: ImageView = findViewById(R.id.R21)
        val r22: ImageView = findViewById(R.id.R22)
        val r23: ImageView = findViewById(R.id.R23)
        val r24: ImageView = findViewById(R.id.R24)
        val r25: ImageView = findViewById(R.id.R25)
        val r26: ImageView = findViewById(R.id.R26)
        val r27: ImageView = findViewById(R.id.R27)
        val r30: ImageView = findViewById(R.id.R30)
        val r31: ImageView = findViewById(R.id.R31)
        val r32: ImageView = findViewById(R.id.R32)
        val r33: ImageView = findViewById(R.id.R33)
        val r34: ImageView = findViewById(R.id.R34)
        val r35: ImageView = findViewById(R.id.R35)
        val r36: ImageView = findViewById(R.id.R36)
        val r37: ImageView = findViewById(R.id.R37)
        val r40: ImageView = findViewById(R.id.R40)
        val r41: ImageView = findViewById(R.id.R41)
        val r42: ImageView = findViewById(R.id.R42)
        val r43: ImageView = findViewById(R.id.R43)
        val r44: ImageView = findViewById(R.id.R44)
        val r45: ImageView = findViewById(R.id.R45)
        val r46: ImageView = findViewById(R.id.R46)
        val r47: ImageView = findViewById(R.id.R47)
        val r50: ImageView = findViewById(R.id.R50)
        val r51: ImageView = findViewById(R.id.R51)
        val r52: ImageView = findViewById(R.id.R52)
        val r53: ImageView = findViewById(R.id.R53)
        val r54: ImageView = findViewById(R.id.R54)
        val r55: ImageView = findViewById(R.id.R55)
        val r56: ImageView = findViewById(R.id.R56)
        val r57: ImageView = findViewById(R.id.R57)
        val r60: ImageView = findViewById(R.id.R60)
        val r61: ImageView = findViewById(R.id.R61)
        val r62: ImageView = findViewById(R.id.R62)
        val r63: ImageView = findViewById(R.id.R63)
        val r64: ImageView = findViewById(R.id.R64)
        val r65: ImageView = findViewById(R.id.R65)
        val r66: ImageView = findViewById(R.id.R66)
        val r67: ImageView = findViewById(R.id.R67)
        val r70: ImageView = findViewById(R.id.R70)
        val r71: ImageView = findViewById(R.id.R71)
        val r72: ImageView = findViewById(R.id.R72)
        val r73: ImageView = findViewById(R.id.R73)
        val r74: ImageView = findViewById(R.id.R74)
        val r75: ImageView = findViewById(R.id.R75)
        val r76: ImageView = findViewById(R.id.R76)
        val r77: ImageView = findViewById(R.id.R77)

        var selectedSquare: ImageView

        r00.setOnClickListener {
            selectedSquare = r00
            gameScreen.addPiece(selectedSquare)
        }

        r01.setOnClickListener {
            selectedSquare = r01
            gameScreen.addPiece(selectedSquare)
        }

        r02.setOnClickListener {
            selectedSquare = r02
            gameScreen.addPiece(selectedSquare)
        }

        r03.setOnClickListener {
            selectedSquare = r03
            gameScreen.addPiece(selectedSquare)
        }

        r04.setOnClickListener {
            selectedSquare = r04
            gameScreen.addPiece(selectedSquare)
        }

        r05.setOnClickListener {
            selectedSquare = r05
            gameScreen.addPiece(selectedSquare)
        }

        r06.setOnClickListener {
            selectedSquare = r06
            gameScreen.addPiece(selectedSquare)
        }

        r07.setOnClickListener {
            selectedSquare = r07
            gameScreen.addPiece(selectedSquare)
        }

        r10.setOnClickListener {
            selectedSquare = r10
            gameScreen.addPiece(selectedSquare)
        }

        r11.setOnClickListener {
            selectedSquare = r11
            gameScreen.addPiece(selectedSquare)
        }

        r12.setOnClickListener {
            selectedSquare = r12
            gameScreen.addPiece(selectedSquare)
        }

        r13.setOnClickListener {
            selectedSquare = r13
            gameScreen.addPiece(selectedSquare)
        }

        r14.setOnClickListener {
            selectedSquare = r14
            gameScreen.addPiece(selectedSquare)
        }

        r15.setOnClickListener {
            selectedSquare = r15
            gameScreen.addPiece(selectedSquare)
        }

        r16.setOnClickListener {
            selectedSquare = r16
            gameScreen.addPiece(selectedSquare)
        }

        r17.setOnClickListener {
            selectedSquare = r17
            gameScreen.addPiece(selectedSquare)
        }

        r20.setOnClickListener {
            selectedSquare = r20
            gameScreen.addPiece(selectedSquare)
        }

        r21.setOnClickListener {
            selectedSquare = r21
            gameScreen.addPiece(selectedSquare)
        }

        r22.setOnClickListener {
            selectedSquare = r22
            gameScreen.addPiece(selectedSquare)
        }

        r23.setOnClickListener {
            selectedSquare = r23
            gameScreen.addPiece(selectedSquare)
        }

        r24.setOnClickListener {
            selectedSquare = r24
            gameScreen.addPiece(selectedSquare)
        }

        r25.setOnClickListener {
            selectedSquare = r25
            gameScreen.addPiece(selectedSquare)
        }

        r26.setOnClickListener {
            selectedSquare = r26
            gameScreen.addPiece(selectedSquare)
        }

        r27.setOnClickListener {
            selectedSquare = r27
            gameScreen.addPiece(selectedSquare)
        }

        r30.setOnClickListener {
            selectedSquare = r30
            gameScreen.addPiece(selectedSquare)
        }

        r31.setOnClickListener {
            selectedSquare = r31
            gameScreen.addPiece(selectedSquare)
        }

        r32.setOnClickListener {
            selectedSquare = r32
            gameScreen.addPiece(selectedSquare)
        }

        r33.setOnClickListener {
            selectedSquare = r33
            gameScreen.addPiece(selectedSquare)
        }

        r34.setOnClickListener {
            selectedSquare = r34
            gameScreen.addPiece(selectedSquare)
        }

        r35.setOnClickListener {
            selectedSquare = r35
            gameScreen.addPiece(selectedSquare)
        }

        r36.setOnClickListener {
            selectedSquare = r36
            gameScreen.addPiece(selectedSquare)
        }

        r37.setOnClickListener {
            selectedSquare = r37
            gameScreen.addPiece(selectedSquare)
        }

        r40.setOnClickListener {
            selectedSquare = r40
            gameScreen.addPiece(selectedSquare)
        }

        r41.setOnClickListener {
            selectedSquare = r41
            gameScreen.addPiece(selectedSquare)
        }

        r42.setOnClickListener {
            selectedSquare = r42
            gameScreen.addPiece(selectedSquare)
        }

        r43.setOnClickListener {
            selectedSquare = r43
            gameScreen.addPiece(selectedSquare)
        }

        r44.setOnClickListener {
            selectedSquare = r44
            gameScreen.addPiece(selectedSquare)
        }

        r45.setOnClickListener {
            selectedSquare = r45
            gameScreen.addPiece(selectedSquare)
        }

        r46.setOnClickListener {
            selectedSquare = r46
            gameScreen.addPiece(selectedSquare)
        }

        r47.setOnClickListener {
            selectedSquare = r47
            gameScreen.addPiece(selectedSquare)
        }

        r50.setOnClickListener {
            selectedSquare = r50
            gameScreen.addPiece(selectedSquare)
        }

        r51.setOnClickListener {
            selectedSquare = r51
            gameScreen.addPiece(selectedSquare)
        }

        r52.setOnClickListener {
            selectedSquare = r52
            gameScreen.addPiece(selectedSquare)
        }

        r53.setOnClickListener {
            selectedSquare = r53
            gameScreen.addPiece(selectedSquare)
        }

        r54.setOnClickListener {
            selectedSquare = r54
            gameScreen.addPiece(selectedSquare)
        }

        r55.setOnClickListener {
            selectedSquare = r55
            gameScreen.addPiece(selectedSquare)
        }

        r56.setOnClickListener {
            selectedSquare = r56
            gameScreen.addPiece(selectedSquare)
        }

        r57.setOnClickListener {
            selectedSquare = r57
            gameScreen.addPiece(selectedSquare)
        }

        r60.setOnClickListener {
            selectedSquare = r60
            gameScreen.addPiece(selectedSquare)
        }

        r61.setOnClickListener {
            selectedSquare = r61
            gameScreen.addPiece(selectedSquare)
        }

        r62.setOnClickListener {
            selectedSquare = r62
            gameScreen.addPiece(selectedSquare)
        }

        r63.setOnClickListener {
            selectedSquare = r63
            gameScreen.addPiece(selectedSquare)
        }

        r64.setOnClickListener {
            selectedSquare = r64
            gameScreen.addPiece(selectedSquare)
        }

        r65.setOnClickListener {
            selectedSquare = r65
            gameScreen.addPiece(selectedSquare)
        }

        r66.setOnClickListener {
            selectedSquare = r66
            gameScreen.addPiece(selectedSquare)
        }

        r67.setOnClickListener {
            selectedSquare = r67
            gameScreen.addPiece(selectedSquare)
        }

        r70.setOnClickListener {
            selectedSquare = r70
            gameScreen.addPiece(selectedSquare)
        }

        r71.setOnClickListener {
            selectedSquare = r71
            gameScreen.addPiece(selectedSquare)
        }

        r72.setOnClickListener {
            selectedSquare = r72
            gameScreen.addPiece(selectedSquare)
        }

        r73.setOnClickListener {
            selectedSquare = r73
            gameScreen.addPiece(selectedSquare)
        }

        r74.setOnClickListener {
            selectedSquare = r74
            gameScreen.addPiece(selectedSquare)
        }

        r75.setOnClickListener {
            selectedSquare = r75
            gameScreen.addPiece(selectedSquare)
        }

        r76.setOnClickListener {
            selectedSquare = r76
            gameScreen.addPiece(selectedSquare)
        }

        r77.setOnClickListener {
            selectedSquare = r77
            gameScreen.addPiece(selectedSquare)
        }
    }
}