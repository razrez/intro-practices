package com.example.intenttutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    companion object {
        const val CODE = "code"
        const val USER_INPUT_CODE = "user_code"

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // get code from MainActivity
        val bundle: Bundle? = intent.extras
        val code = bundle?.getInt(CODE)
        findViewById<TextView>(R.id.textview_label).text = code.toString()

        // send input_code to MainActivity
        findViewById<EditText>(R.id.code_input).setOnEditorActionListener { textView, i, _ ->
            if ( i == EditorInfo.IME_ACTION_DONE ){
                intent = Intent(this, MainActivity::class.java)
                intent.putExtra(CODE, code)
                intent.putExtra(USER_INPUT_CODE, textView.text.toString())
                startActivity(intent)
            }
            true
        }
    }


}