package com.example.ascalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ascalculator.R
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    var decimalAllowed=true
    var operationAllowed=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calScreen=findViewById<TextView>(R.id.calcScreen)

        val button7 = findViewById<Button>(R.id.bSeven)
        button7.setOnClickListener {updateCalculator("7")}
        val button8 = findViewById<Button>(R.id.bEight)
        button8.setOnClickListener {updateCalculator("8")}
        val button9 = findViewById<Button>(R.id.bNine)
        button9.setOnClickListener {updateCalculator("9")}
        val button4 = findViewById<Button>(R.id.bFour)
        button4.setOnClickListener {updateCalculator("4")}
        val button5 = findViewById<Button>(R.id.bFive)
        button5.setOnClickListener {updateCalculator("5")}
        val button6 = findViewById<Button>(R.id.bSix)
        button6.setOnClickListener {updateCalculator("6")}
        val button1 = findViewById<Button>(R.id.bOne)
        button1.setOnClickListener {updateCalculator("1")}
        val button2 = findViewById<Button>(R.id.bTwo)
        button2.setOnClickListener {updateCalculator("2")}
        val button3 = findViewById<Button>(R.id.bThree)
        button3.setOnClickListener {updateCalculator("3")}
        val buttonDecimal=findViewById<Button>(R.id.bDecimal)
        buttonDecimal.setOnClickListener { updateCalculator(".") }
        val button0 = findViewById<Button>(R.id.bZero)
        button0.setOnClickListener {updateCalculator("0")}

        val buttonBackspace = findViewById<Button>(R.id.bBackspace)
        buttonBackspace.setOnClickListener {updateCalculator("C")}
        buttonBackspace.setOnLongClickListener { updateCalculator("CLEAR") }


    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(
//            applicationContext, "This is fun!",
//            Toast.LENGTH_SHORT).show()
    }

    private fun updateCalculator(input:String): Boolean {

        //C Button is Pressed
        if(input=="C")
        {
            if(calcScreen.text.length==1&&calcScreen.text.toString()!="0")
            {
                calcScreen.text="0"
            }
            else if(calcScreen.text[calcScreen.length()-1]=='.')
            {
                calcScreen.text=calcScreen.text.toString().subSequence(0,calcScreen.text.length-1)
                decimalAllowed=true
            }
            else if(calcScreen.text.toString()!="0")
            {
                calcScreen.text=calcScreen.text.toString().subSequence(0,calcScreen.text.length-1)
            }

        }
        //C button was long pressed
        else if(input=="CLEAR")
        {
            calcScreen.text="0"
            decimalAllowed=true
            return true;
        }
        //Decimal Button Pressed
        else if(input==".")
        {
            if(decimalAllowed&&Character.isDigit(calcScreen.text[calcScreen.length()-1]))
            {
                calcScreen.text = calcScreen.text.toString() + input
                decimalAllowed=false;
            }

        }
        // Number on Keypad is pressed
        else {
            if (calcScreen.text.toString() == "0") {
                calcScreen.text = input
            } else {
                calcScreen.text = calcScreen.text.toString() + input
            }

        }
        return false;
    }
}
