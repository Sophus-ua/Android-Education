package com.example.fourthlesson.task_02

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fourthlesson.R

class GameTicTacToe : AppCompatActivity() {

    private lateinit var firstName: String
    private lateinit var secondName: String
    private lateinit var scoreBoard: TextView
    private lateinit var infoBoard: TextView
    private lateinit var startFinish: Button
    private lateinit var arrayOfButtons: Array<Array<Button?>>
    private var gameIsOn = false
    private var crossPlayerNextStart = true
    private var crossPlayerTurn = true
    private var scoreCrossPlayer = 0
    private var scoreZeroPlayer = 0
    private var numberOfMoves = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_tic_tac_toe)

        arrayOfButtons = getArrayOfButtons()
        scoreBoard = findViewById(R.id.scoreBoard)
        infoBoard = findViewById(R.id.infoBoard)
        startFinish = findViewById(R.id.start)

        firstName = intent.getStringExtra("firstName")!!
        secondName = intent.getStringExtra("secondName")!!
        fillScoreBoard()
    }

    fun startClick(view: View) {
        clearGameFields()
        if (!gameIsOn) {
            gameIsOn = true
            startFinish.text = getString(R.string.finish)
            crossPlayerTurn = crossPlayerNextStart
            infoBoard.text = if (crossPlayerTurn) firstName else secondName
            crossPlayerNextStart = !crossPlayerNextStart
        } else {
            gameIsOn = false
            startFinish.text = getString(R.string.start)
            infoBoard.text = getString(R.string.pushStart)
        }
    }

    fun click(view: View) {
        val fieldIsEmpty = (view as Button).text.equals(getString(R.string.empty))
        if (!gameIsOn || !fieldIsEmpty) return

        view.text = if (crossPlayerTurn) getString(R.string.crosses)
        else getString(R.string.zeroes)

        if (!checkWin(view)) {
            crossPlayerTurn = !crossPlayerTurn
            infoBoard.text = if (crossPlayerTurn) firstName
            else secondName
            if (--numberOfMoves == 0)
                noWinner()
        } else win()
    }

    fun restartClick(view: View) {
        finish()
    }

    private fun checkWin(view: View): Boolean {
        val tags = (view.tag as String).split(",")
        val buttonText = (view as Button).text
        val row = tags[0].toInt()
        val column = tags[1].toInt()

        if (checkRowAndColumn(row, column, buttonText)) return true

        val firstDiagonal = arrayOf(0, 1, 2)
        val secondDiagonal = arrayOf(2, 1, 0)
        if (column == firstDiagonal[row] && checkDiagonal(row, firstDiagonal, buttonText))
            return true
        return (column == secondDiagonal[row] && checkDiagonal(row, secondDiagonal, buttonText))
    }

    private fun checkRowAndColumn(row: Int, column: Int, buttonText: CharSequence): Boolean {
        val rowList = mutableListOf(row, column)
        val columnList = mutableListOf(row, column)

        for (i in 0 until 3) {
            if (i != column && buttonText == arrayOfButtons[row][i]!!.text) {
                rowList.add(row)
                rowList.add(i)
            }
            if (i != row && buttonText == arrayOfButtons[i][column]!!.text) {
                columnList.add(i)
                columnList.add(column)
            }
        }

        if (rowList.size != 6 && columnList.size != 6) return false
        if (rowList.size == 6) winDraw(rowList)
        if (columnList.size == 6) winDraw(columnList)
        return true
    }

    private fun checkDiagonal(row: Int, diagonal: Array<Int>, buttonText: CharSequence): Boolean {
        val diagonalList = mutableListOf(row, diagonal[row])
        var rowCount = 0
        for (i in diagonal.iterator()) {
            if (rowCount != row && buttonText == arrayOfButtons[rowCount][i]!!.text) {
                diagonalList.add(rowCount)
                diagonalList.add(i)
            }
            rowCount++
        }

        if (diagonalList.size != 6) return false
        winDraw(diagonalList)
        return true
    }

    private fun winDraw(list: List<Int>) {
        val colorStateList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.orange))
        lateinit var button: Button
        var i = 0
        for (count in 1 until 4) {
            val row = list[i++]
            val column = list[i++]
            button = arrayOfButtons[row][column]!!
            button.backgroundTintList = colorStateList
        }
    }

    private fun win() {
        endGameToast(false)
        if (crossPlayerTurn) {
            infoBoard.text = String.format(getString(R.string.win_player), firstName)
            crossPlayerNextStart = false
            scoreCrossPlayer++
        } else {
            infoBoard.text = String.format(getString(R.string.win_player), secondName)
            crossPlayerNextStart = true
            scoreZeroPlayer++
        }
        fillScoreBoard()
        startFinish.text = getString(R.string.start)
        gameIsOn = false
    }

    private fun noWinner() {
        endGameToast(true)
        gameIsOn = false
        startFinish.text = getString(R.string.start)
        infoBoard.text = getString(R.string.draw)
    }

    private fun endGameToast(isDraw: Boolean) {
        val message =
            if (isDraw) getString(R.string.draw)
            else if (crossPlayerTurn) String.format(getString(R.string.win_player), firstName)
            else String.format(getString(R.string.win_player), secondName)

        (Toast.makeText(this, message, Toast.LENGTH_LONG)).show()
    }

    private fun clearGameFields() {
        numberOfMoves = 9
        val colorStateList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green))

        for (i in arrayOfButtons.indices)
            for (j in arrayOfButtons[i].indices) {
                val button = arrayOfButtons[i][j]
                button!!.setText(R.string.empty)
                button.backgroundTintList = colorStateList
            }
    }

    private fun fillScoreBoard(){
        scoreBoard.text = String.format(
            getString(R.string.score), firstName, scoreCrossPlayer, scoreZeroPlayer, secondName
        )
    }

    private fun getArrayOfButtons(): Array<Array<Button?>> {
        arrayOfButtons = Array(3) { arrayOfNulls(3) }
        arrayOfButtons[0][0] = findViewById(R.id.one)
        arrayOfButtons[0][1] = findViewById(R.id.two)
        arrayOfButtons[0][2] = findViewById(R.id.three)
        arrayOfButtons[1][0] = findViewById(R.id.four)
        arrayOfButtons[1][1] = findViewById(R.id.five)
        arrayOfButtons[1][2] = findViewById(R.id.six)
        arrayOfButtons[2][0] = findViewById(R.id.seven)
        arrayOfButtons[2][1] = findViewById(R.id.eight)
        arrayOfButtons[2][2] = findViewById(R.id.nine)
        return arrayOfButtons
    }
}