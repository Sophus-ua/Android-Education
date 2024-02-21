package com.example.secondlesson

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    private lateinit var resultView: TextView
    private lateinit var firstNumberView: EditText
    private lateinit var secondNumberView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        resultView = findViewById(R.id.result)
        firstNumberView = findViewById(R.id.firstNumber)
        secondNumberView = findViewById(R.id.secondNumber)
    }

    fun click(view: View) {
        if (TextUtils.isEmpty(firstNumberView.getText())
            || TextUtils.isEmpty(secondNumberView.getText()))
            resultView.text = getString(R.string.no_value)

        else if (secondNumberView.getText().toString().toDouble() == 0.0
            && view.id == R.id.division)
            resultView.text = getString(R.string.divided_by_zero)

        else performOperation(view)
    }

    private fun performOperation (view: View) {
        val num1 = firstNumberView.getText().toString().toDouble()
        val num2 = secondNumberView.getText().toString().toDouble()
        val textFormat = getString(R.string.format)

        when (view.id) {
            R.id.addition -> resultView.text = String.format(textFormat, num1 + num2)
            R.id.subtraction -> resultView.text = String.format(textFormat, num1 - num2)
            R.id.multiplication -> resultView.text = String.format(textFormat, num1 * num2)
            R.id.division -> resultView.text = String.format(textFormat, num1 / num2)
        }
    }
}