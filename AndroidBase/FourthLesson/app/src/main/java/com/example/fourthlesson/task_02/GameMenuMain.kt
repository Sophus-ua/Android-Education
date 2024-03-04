package com.example.fourthlesson.task_02


import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fourthlesson.R

class GameMenuMain : AppCompatActivity() {

    private lateinit var firstEditText: EditText
    private lateinit var secondEditText: EditText
    private lateinit var startButton: Button
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_menu)

        firstEditText = findViewById(R.id.firstNameEdit)
        secondEditText = findViewById(R.id.secondNameEdit)
        startButton = findViewById(R.id.start_game)
        alertDialog = getDialog()

        onBackPressedDispatcher.addCallback(this, getOnBackPressedCallback())

        firstEditText.addTextChangedListener(getTextWatcher())
        secondEditText.addTextChangedListener(getTextWatcher())
    }

    fun click(view: View) {
        val intent = Intent(this, GameTicTacToe::class.java)
        intent.putExtra("firstName", firstEditText.text.toString())
        intent.putExtra("secondName", secondEditText.text.toString())
        startActivity(intent)
    }

    private fun getOnBackPressedCallback(): OnBackPressedCallback{
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                alertDialog.show()
            }
        }
    }

    private fun getDialog(): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.exit))
            .setCancelable(false)
            .setMessage(getString(R.string.exit_dialog))
            .setNegativeButton(getString(R.string.no)) { _, _ -> finish()}
            .setPositiveButton(getString(R.string.yes)){ _, _ -> alertDialog.dismiss()}

        return dialogBuilder.create()
    }

    private fun getTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val firstNameText = firstEditText.text.toString()
                val secondNameText = secondEditText.text.toString()
                startButton.isEnabled = firstNameText.isNotEmpty() && secondNameText.isNotEmpty()

                if (startButton.isEnabled) {
                    startButton.text = getString(R.string.start_game)
                    startButton.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this@GameMenuMain, R.color.purple_500))
                } else {
                    startButton.text = getString(R.string.fill_names)
                    startButton.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this@GameMenuMain, R.color.purple_200))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
}