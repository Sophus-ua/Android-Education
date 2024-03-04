package com.example.fourthlesson.task_01

import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.fourthlesson.R

class PhoneBookMain : AppCompatActivity() {

    private lateinit var phoneList: MutableList<String>
    private lateinit var addPhoneButton: Button
    private lateinit var listView: ListView
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_book)

        phoneList = mutableListOf()
        addPhoneButton = findViewById(R.id.addPhone)
        listView = findViewById(R.id.phone_list)
        activityResultLauncher = getActivityResultLauncher()
    }

    fun click(view: View) {
        val intent = Intent(this, PhoneAdd::class.java)
        intent.putExtra("countOfPhones", phoneList.size)
        activityResultLauncher.launch(intent)
    }

    private fun getActivityResultLauncher(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = result.data
                if (intent != null) {
                    val nameAndPhone = intent.getStringExtra("nameAndPhone")
                    phoneList.add(nameAndPhone!!)

                    val adapter = ArrayAdapter(this, R.layout.simple_text, phoneList)
                    listView.adapter = adapter
                }
            } else {/* nothing */}
        }
    }
}