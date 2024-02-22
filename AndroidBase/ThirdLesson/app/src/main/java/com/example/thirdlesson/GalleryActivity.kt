package com.example.thirdlesson

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat


class GalleryActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var imageArray: Array<Drawable?>
    private var currentImageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery)

        imageView = findViewById(R.id.image)
        fillImageArray()
    }

    fun click(view: View) {
        if (view.id == R.id.right) {
            if (++currentImageIndex == imageArray.size)
                currentImageIndex = 0
            imageView.setImageDrawable(imageArray[currentImageIndex])
        } else {
            if (--currentImageIndex < 0)
                currentImageIndex = imageArray.size -1
            imageView.setImageDrawable(imageArray[currentImageIndex])
        }
    }

    private fun fillImageArray() {
        imageArray = arrayOf(
            ResourcesCompat.getDrawable(resources, R.drawable.a_001, theme),
            ResourcesCompat.getDrawable(resources, R.drawable.a_002, theme),
            ResourcesCompat.getDrawable(resources, R.drawable.a_003, theme),
            ResourcesCompat.getDrawable(resources, R.drawable.a_004, theme),
            ResourcesCompat.getDrawable(resources, R.drawable.a_005, theme)
        )
    }
}