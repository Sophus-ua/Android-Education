package com.example.secondlesson

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class ListOfBirdsActivity : AppCompatActivity() {
    private lateinit var gridlayout: GridLayout
    private lateinit var colorArray: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_birds)

        ListOfBirds.fillListOfBirds()
        fillColorsArray()

        gridlayout = findViewById(R.id.grid_of_birds)
        fillGridOfBirds()
    }

    private fun fillGridOfBirds() {
        var row = 0
        for (bird in ListOfBirds.listOfBirds) {
            val color = colorArray.get(Random.nextInt(0, colorArray.size))
            var column = -1
            while (++column < 3) {
                fillRowGridOfBirds(bird, row, column, color)
            }
            row++
        }
    }

    private fun fillRowGridOfBirds(bird: Bird, row: Int, column: Int, color: Int) {
        val textView = TextView(this)
        val params = GridLayout.LayoutParams()

        params.rowSpec = GridLayout.spec(row)
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 20f
        textView.gravity = Gravity.CENTER

        textView.setBackgroundColor(color)

        when (column) {
            0 -> {
                textView.text = bird.name
                params.columnSpec = GridLayout.spec(column, 1f)
            }
            1 -> {
                textView.text = bird.age.toString()
                params.columnSpec = GridLayout.spec(column, 0.6f)
            }
            2 -> {
                textView.text = bird.height.toString()
                params.columnSpec = GridLayout.spec(column, 0.6f)
            }
        }
        textView.layoutParams = params
        gridlayout.addView(textView)
    }

    private fun fillColorsArray() {
        colorArray = arrayOf(
        ContextCompat.getColor(this, R.color.purple_200),
        ContextCompat.getColor(this, R.color.purple_500),
        ContextCompat.getColor(this, R.color.purple_700),
        ContextCompat.getColor(this, R.color.teal_200),
        ContextCompat.getColor(this, R.color.teal_700),
        ContextCompat.getColor(this, R.color.color1),
        ContextCompat.getColor(this, R.color.color2),
        ContextCompat.getColor(this, R.color.color3),
        ContextCompat.getColor(this, R.color.color4),
        ContextCompat.getColor(this, R.color.color5),
        ContextCompat.getColor(this, R.color.color6),
        ContextCompat.getColor(this, R.color.color7),
        ContextCompat.getColor(this, R.color.color8),
        ContextCompat.getColor(this, R.color.color9)
        )
    }

    class Bird (val name: String, var age: Int, var height: Int)

    class ListOfBirds {
        companion object {
            lateinit var listOfBirds: List<Bird>

            fun fillListOfBirds (){
                listOfBirds = listOf(
                    Bird("Robin", 2, 15),
                    Bird("Sparrow", 1, 10),
                    Bird("Eagle", 5, 50),
                    Bird("Parrot", 3, 20),
                    Bird("Owl", 4, 25),
                    Bird("Falcon", 3, 30),
                    Bird("Penguin", 6, 40),
                    Bird("Hawk", 4, 45),
                    Bird("Swan", 3, 35),
                    Bird("Duck", 2, 12)
                )
            }
        }
    }
}