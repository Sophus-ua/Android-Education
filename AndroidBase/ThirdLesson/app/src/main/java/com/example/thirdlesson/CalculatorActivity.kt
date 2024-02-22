package com.example.thirdlesson

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var scoreboard: TextView
    private var appliedOperation = false
    private var secondNumberIsEntered = false
    private var operationDivision = false
    private var showedDivisionByZero = false
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private lateinit var operation: (Double, Double) -> Double


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        scoreboard = findViewById(R.id.scoreboard)
    }

    fun numberClick(view: View) {
        if (showedDivisionByZero)
            resetClick(view)

        if (appliedOperation)
            secondNumberIsEntered = true

        if (view is Button) {
            val buttonText = view.text.toString()
            displayText(buttonText)
        }
    }

    fun operationsClick(view: View) {
        if (showedDivisionByZero)
            resetClick(view)


        if (!appliedOperation) {
            createOperation(view)
        } else if (!secondNumberIsEntered) {
            /*nothing*/
        } else {
            saveNumber(false)
            displayResult()
            if (secondNumber != 0.0 || !operationDivision) {
                createOperation(view)
                secondNumberIsEntered = false
            } else {
                resetData()
            }
        }
    }

    fun resultClick(view: View) {
        if (showedDivisionByZero)
            resetClick(view)

        if (!secondNumberIsEntered) {
            /*nothing*/
        } else {
            saveNumber(false)
            displayResult()
            resetData()
        }
    }

    fun resetClick(view: View) {
        scoreboard.text = getString(R.string.empty)
        resetData()
        showedDivisionByZero = false
    }

    private fun resetData() {
        appliedOperation = false
        operationDivision = false
        secondNumberIsEntered = false
        firstNumber = 0.0
        secondNumber = 0.0
    }

    private fun createOperation(view: View) {
        appliedOperation = true
        saveNumber(true)

        when (view.id) {
            R.id.addition -> {
                operation = { x, y -> x + y }
                displayText(getString(R.string.addition))
            }

            R.id.subtraction -> {
                operation = { x, y -> x - y }
                displayText(getString(R.string.subtraction))
            }

            R.id.multiplication -> {
                operation = { x, y -> x * y }
                displayText(getString(R.string.multiplication))
            }

            R.id.division -> {
                operationDivision = true
                operation = { x, y -> x / y }
                displayText(getString(R.string.division))
            }
        }
    }

    private fun saveNumber(firstNum: Boolean) {
        if (firstNum) {
            if (!scoreboard.text.toString().isEmpty())
                firstNumber = scoreboard.text.toString().toDouble()
            else  scoreboard.text = getString(R.string.zero)
        } else {
            val pattern = Regex("(?<=.)[+*/-]")
            val numbers = scoreboard.text.toString().split(pattern)
            if (!numbers[1].isEmpty())
                secondNumber = numbers[1].toDouble()
        }
    }

    private fun displayResult() {
        if (secondNumber == 0.0 && operationDivision) {
            scoreboard.text = getString(R.string.divided_by_zero)
            showedDivisionByZero = true
        } else {
            val textFormat = getString(R.string.format)
            val result = operation(firstNumber, secondNumber)
            scoreboard.text = String.format(textFormat, result)
        }
    }

    private fun displayText(text: String) {
        val scoreboardText = scoreboard.text.toString()
        val textFormat = getString(R.string.formatTwo)
        scoreboard.text = String.format(textFormat, scoreboardText, text)
    }
}