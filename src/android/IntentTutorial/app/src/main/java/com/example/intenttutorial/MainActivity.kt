package com.example.intenttutorial

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val randomCode = Random.nextInt(1000,9999)

    private var previousCode:String? = null
    private var secondActivityUserInput:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.random_code)
        val tryButton = findViewById<Button>(R.id.try_button)



        // get previous code and user's input from SecondActivity
        val bundle: Bundle? = intent.extras
        previousCode = bundle?.getInt(SecondActivity.CODE).toString()
        secondActivityUserInput = bundle?.getString(SecondActivity.USER_INPUT_CODE)

        // if user's input is correct
        if (previousCode == secondActivityUserInput && previousCode != null) {
            textView.text = getString(R.string.secret)
            tryButton.isEnabled = false
            tryButton.setBackgroundColor(Color.GREEN)
            tryButton.text = "accepted"
        }

        // if it's the first time OR invalid
        else{

            textView.text = randomCode.toString()
            tryButton.setOnClickListener {
                intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(SecondActivity.CODE, randomCode)
                startActivity(intent)
            }
        }


    }

}