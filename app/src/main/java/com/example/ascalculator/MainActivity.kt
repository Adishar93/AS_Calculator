package com.example.ascalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.ascalculator.R

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {sendMessage()}
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(
            applicationContext, "This is fun!",
            Toast.LENGTH_SHORT).show()
    }

    private fun sendMessage() {
        val message = "Hi!"
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}
