package com.example.retrofit.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.retrofit.R
import com.example.retrofit.httpRepository.HttpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomNumber = (0..385).random()
        val myText = findViewById<TextView>(R.id.tv_showRequest)
        val name = findViewById<TextView>(R.id.tv_name)
        val description = findViewById<TextView>(R.id.tv_description)
        val image = findViewById<ImageView>(R.id.iv_image)

        CoroutineScope(Dispatchers.IO).launch { // run in the Background
            val repo = HttpRepository()
            val allEntries = repo.getAllEntries()
            val entry = repo.getAnEntry(randomNumber)

            withContext(Dispatchers.Main) { // run in the foreground (UI Thread)
                myText.text = "Found ${allEntries?.data?.creatures?.food?.size} and entry called ${entry?.data?.name}"

            }
        }

    }
}