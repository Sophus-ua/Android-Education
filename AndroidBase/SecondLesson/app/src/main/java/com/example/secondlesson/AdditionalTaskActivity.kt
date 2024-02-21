package com.example.secondlesson

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AdditionalTaskActivity: AppCompatActivity() {

    private lateinit var mainLayout: LinearLayout
    private lateinit var nameView: EditText
    private lateinit var loginView: EditText
    private lateinit var passwordView: EditText
    private lateinit var imageView: ImageView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_task)

        mainLayout = findViewById(R.id.parentLayout)
        nameView = findViewById(R.id.nameE)
        loginView = findViewById(R.id.loginE)
        passwordView = findViewById(R.id.passwordE)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
    }

    fun click(view: View){
        nameView.setText(R.string.empty)
        loginView.setText(R.string.empty)
        passwordView.setText(R.string.empty)

        val backgroundColor = (mainLayout.background as? ColorDrawable)?.color
        if (backgroundColor == ContextCompat.getColor(this, R.color.teal_700))
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.color1))
        else mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))

        if (imageView.visibility == View.INVISIBLE)
            imageView.visibility = View.VISIBLE
        else imageView.visibility = View.INVISIBLE
    }
}