package com.example.fourthlesson.task_01

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fourthlesson.R

class PhoneAdd : AppCompatActivity() {

    private lateinit var infoBoard: TextView
    private lateinit var nameEdit: EditText
    private lateinit var phoneEdit: EditText
    private lateinit var addPhoneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_add)

        infoBoard = findViewById(R.id.infoBoard)
        nameEdit = findViewById(R.id.nameEdit)
        phoneEdit = findViewById(R.id.phoneEdit)
        addPhoneButton = findViewById(R.id.addPhone2)

        infoBoard.text = getInfoBoardText()
        nameEdit.addTextChangedListener(getTextWatcher())
        phoneEdit.addTextChangedListener(getTextWatcher())
    }

    fun click(view: View) {
        val nameAndPhone = nameEdit.text.toString() + "\n" + phoneEdit.text.toString()
        val intent = Intent()
        intent.putExtra("nameAndPhone", nameAndPhone)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun getInfoBoardText() : String {
        val countOfPhones =
            intent.getIntExtra("countOfPhones", 0)
        return if (countOfPhones == 0) getString(R.string.added_0_numbers)
        else String.format(getString(R.string.added_numbers), countOfPhones)
    }

    private fun getTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val firstNameText = nameEdit.text.toString()
                val secondNameText = phoneEdit.text.toString()
                addPhoneButton.isEnabled = firstNameText.isNotEmpty() && secondNameText.isNotEmpty()

                if (addPhoneButton.isEnabled) {
                    addPhoneButton.text = getString(R.string.add_name_phone)
                    addPhoneButton.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this@PhoneAdd, R.color.purple_500))
                } else {
                    addPhoneButton.text = getString(R.string.fill_name_phone)
                    addPhoneButton.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this@PhoneAdd, R.color.purple_200))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
}