package com.example.simplecalculator

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val allButtons = findViewById<GridLayout>(R.id.calculator_buttons).touchables
        allButtons.map { btn ->
            btn.setOnClickListener{ mapButtons(it) }
        }

    }

    private fun mapButtons(btn:View) = when(btn.tag) {
        "=" -> {
            val parser = Calculator(binding.expressionText.text.toString())
            val calculateResult = parser.calculate()

            if (calculateResult == Calculator.ErrorMessage.DIVIDE_BY_ZERO.message) {
                Toast(this).showCustomToast(getString(R.string.zeroException))

            }
            else {
                binding.result.text = calculateResult
            }
        }

        "clear" -> {
            binding.expressionText.text = ""
            binding.result.text = ""
        }

        else -> {
            val prevExp = binding.expressionText.text.toString()
            binding.expressionText.text = prevExp.plus(btn.tag)
        }
    }

    private fun Toast.showCustomToast(message: String)
    {
        // use the application extension function
        this.apply {
            setGravity(Gravity.BOTTOM, 0, 40)
            setText(message)
            duration = Toast.LENGTH_LONG
            show()
        }
    }


}