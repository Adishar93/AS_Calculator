package com.example.ascalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.Scroller
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    var decimalAllowed=true
    var operationAllowed=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calScreen=findViewById<EditText>(R.id.calcScreen)

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

        val buttonAllClear = findViewById<Button>(R.id.bAllClear)
        buttonAllClear.setOnClickListener {updateCalculator("CLEAR")}

        val buttonmul = findViewById<Button>(R.id.bMul)
        buttonmul.setOnClickListener {updateCalculator("*")}
        val buttondivide = findViewById<Button>(R.id.bDivide)
        buttondivide.setOnClickListener {updateCalculator("/")}
        val buttonadd = findViewById<Button>(R.id.bAdd)
        buttonadd.setOnClickListener {updateCalculator("+")}
        val buttonsub = findViewById<Button>(R.id.bSub)
        buttonsub.setOnClickListener {updateCalculator("-")}

        val buttonequals=findViewById<Button>(R.id.bEquals)
        buttonequals.setOnClickListener {updateCalculator("=")}

    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(
//            applicationContext, "This is fun!",
//            Toast.LENGTH_SHORT).show()
    }

    private fun updateCalculator(input:String): Boolean {



        //Operation button pressed
        if(operationAllowed&&(input=="*"||input=="/"||input=="+"||input=="-"))
        {
            calcScreen.setText( calcScreen.text.toString() + input)
            operationAllowed=false;
        }

        // Number on Keypad is pressed
        else if(Character.isDigit(input[0])) {
            if (calcScreen.text.toString() == "0") {
                calcScreen.setText(input)
            }
            else if(calcScreen.length()>1&&(calcScreen.text[calcScreen.length()-1]=='*'||calcScreen.text[calcScreen.length()-1]=='/'||calcScreen.text[calcScreen.length()-1]=='+'||calcScreen.text[calcScreen.length()-1]=='-'))
            {
                calcScreen.setText(calcScreen.text.toString() + input)
                decimalAllowed=true
            }
            else {
                calcScreen.setText(calcScreen.text.toString() + input)
            }

        }
        //Decimal Button Pressed
        else if(input==".")
        {
            if(decimalAllowed&&Character.isDigit(calcScreen.text[calcScreen.length()-1]))
            {
                calcScreen.setText( calcScreen.text.toString() + input)
                decimalAllowed=false;
            }

        }
        //Equals button pressed
        else if(input=="=")
        {
            val screenString=calcScreen.text.toString()
            if(screenString.contains('*'))
            {
                val candidates=screenString.split('*')
                val first=candidates[0].toDouble()
                val second=candidates[1].toDouble()
                calcScreen.setText((first*second).toString())
            }
            else if(screenString.contains('/'))
            {
                val candidates=screenString.split('/')
                val first=candidates[0].toDouble()
                val second=candidates[1].toDouble()
                calcScreen.setText((first/second).toString())
            }
            else if(screenString.contains('+'))
            {
                val candidates=screenString.split('+')
                val first=candidates[0].toDouble()
                val second=candidates[1].toDouble()
                calcScreen.setText((first+second).toString())
            }
            else if(screenString.contains('-'))
            {
                val candidates=screenString.split('-')
                val first=candidates[0].toDouble()
                val second=candidates[1].toDouble()
                calcScreen.setText((first-second).toString())
            }

            operationAllowed=true;
        }
        //C Button is Pressed
        else if(input=="C")
        {
            if(calcScreen.text.length==1&&calcScreen.text.toString()!="0")
            {
                calcScreen.setText("0")
            }
            else if(calcScreen.text[calcScreen.length()-1]=='.')
            {
                calcScreen.setText(calcScreen.text.toString().subSequence(0,calcScreen.text.length-1))
                decimalAllowed=true
            }
            else if(calcScreen.text[calcScreen.length()-1]=='*'||calcScreen.text[calcScreen.length()-1]=='/'||calcScreen.text[calcScreen.length()-1]=='+'||calcScreen.text[calcScreen.length()-1]=='-')
            {
                calcScreen.setText(calcScreen.text.toString().subSequence(0,calcScreen.text.length-1))
                operationAllowed=true
            }
            else if(calcScreen.text.toString()!="0")
            {
                calcScreen.setText(calcScreen.text.toString().subSequence(0,calcScreen.text.length-1))
            }

        }
        //C button was long pressed
        else if(input=="CLEAR")
        {
            calcScreen.setText("0")
            decimalAllowed=true
            operationAllowed=true
            return true;
        }


        return false;
    }
}
