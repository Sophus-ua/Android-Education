package com.example.thirdlesson

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GameTicTacToeActivity : AppCompatActivity() {

    private lateinit var infoBoard: TextView
    private lateinit var start: Button
    private var gameIsOn = false
    private var crossPlayerTurn = false
    private lateinit var arrayOfButtons: Array<Array<Button?>>
    private var numberOfMoves = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_tic_tac_toe)

        fillArrayOfButtons()
        infoBoard = findViewById(R.id.infoBoard)
        start = findViewById(R.id.start)
    }

    fun click(view: View) {
        if (!gameIsOn || !isEmptyGameField(view)) {
            return
        }

        (view as Button).text = if (crossPlayerTurn) getString(R.string.crosses)
        else getString(R.string.zeroes)

        if (!checkWin(view)) {
            infoBoard.text = if (crossPlayerTurn) getString(R.string.turn_zero)
            else getString(R.string.turn_cross)
            crossPlayerTurn = !crossPlayerTurn
            if (--numberOfMoves == 0)
                noWinner()
        } else win(crossPlayerTurn)
    }

    fun startClick(view: View) {
        if (!gameIsOn) {
            clearGameFields()
            gameIsOn = true
            start.text = getString(R.string.finish)
            infoBoard.text = getString(R.string.turn_cross)
            crossPlayerTurn = true
        } else {
            clearGameFields()
            gameIsOn = false
            start.text = getString(R.string.start)
            infoBoard.text = getString(R.string.pushStart)
        }
    }

    private fun noWinner(){
        gameIsOn = false
        start.text = getString(R.string.start)
        infoBoard.text = getString(R.string.draw)
    }

    private fun isEmptyGameField(view: View): Boolean {
        return (view as Button).text.equals(getString(R.string.empty))
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
        if (rowList.size == 6) {
            winDraw(rowList)
            return true
        }
        if (columnList.size == 6) {
            winDraw(columnList)
            return true
        }
        return false
    }

    private fun checkDiagonal(row: Int, diagonal: Array<Int>, buttonText: CharSequence): Boolean {
        val diagonalList = mutableListOf(row, diagonal[row])
        var rowCount = 0
        for (i in diagonal) {
            if (rowCount != row && buttonText == arrayOfButtons[rowCount][i]!!.text) {
                diagonalList.add(rowCount)
                diagonalList.add(i)
            }
            rowCount++
        }
        if (diagonalList.size == 6){
            winDraw(diagonalList)
            return true
        }
        return false
    }

    private fun winDraw(list: List<Int>) {
        val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
        lateinit var button: Button
        var i = 0
        for (count in 1 until 4) {
            val row = list.get(i++)
            val column = list.get(i++)
            button = arrayOfButtons[row][column]!!
            button.backgroundTintList = colorStateList
        }
    }

    private fun win(crossPlayerTurn: Boolean) {
        if (crossPlayerTurn)
            infoBoard.text = getString(R.string.win_cross)
        else
            infoBoard.text = getString(R.string.win_zero)

        start.text = getString(R.string.start)
        gameIsOn = false
    }

    private fun clearGameFields() {
        numberOfMoves = 9
        val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_500))

        for (i in arrayOfButtons.indices)
            for (j in arrayOfButtons[i].indices) {
                val button = arrayOfButtons[i][j]
                button!!.setText(R.string.empty)
                button.backgroundTintList = colorStateList
            }
    }

    private fun fillArrayOfButtons() {
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
    }
}


