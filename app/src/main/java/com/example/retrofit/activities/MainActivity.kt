package com.example.retrofit.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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

        val name = findViewById<TextView>(R.id.tv_name)
        val description = findViewById<TextView>(R.id.tv_description)
        val image = findViewById<ImageView>(R.id.iv_image)
        val button = findViewById<Button>(R.id.btn_more)

        callMyApi(name, description, image)
        button.setOnClickListener { callMyApi(name,description,image) }

    }

    private fun callMyApi(
        name: TextView,
        description: TextView,
        image: ImageView
    ) {
        CoroutineScope(Dispatchers.IO).launch { // run in the Background
            val repo = HttpRepository()
            //val allEntries = repo.getAllEntries()
            val randomNumber = (0..385).random()
            val entry = repo.getAnEntry(randomNumber)

            withContext(Dispatchers.Main) { // run in the foreground (UI Thread)

                // Name
                name.text = "${entry?.data?.name}".uppercase()
                // Description
                description.text = "${entry?.data?.description}"
                // Image
                Glide.with(this@MainActivity).load("${entry?.data?.image}").placeholder(R.drawable.loading_buffer).into(image)
            }
        }
    }
}