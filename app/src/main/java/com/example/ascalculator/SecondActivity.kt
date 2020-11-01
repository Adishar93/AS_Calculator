package com.example.ascalculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import com.example.ascalculator.R


class SecondActivity : AppCompatActivity() {

    private lateinit var animFadein: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)

        val button = findViewById<Button>(R.id.button2)

        animFadein = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.fade_from_top
        )

        button.startAnimation(animFadein)

        animFadein.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

                val message = intent.getStringExtra(EXTRA_MESSAGE)
                button.apply {
                    text = message
                }
                button.setOnClickListener { startMainActivity() }
            }

            override fun onAnimationStart(animation: Animation?) {
                button.apply {
                    text = "animating"
                }
            }
        })

    }

    private fun startMainActivity() {
        finish()
    }
}
