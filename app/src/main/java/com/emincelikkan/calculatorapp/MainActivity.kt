package com.emincelikkan.calculatorapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.emincelikkan.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            numberZero.appendVal("0", false)
            numberOne.appendVal("1", false)
            numberTwo.appendVal("2", false)
            numberThree.appendVal("3", false)
            numberFour.appendVal("4", false)
            numberFive.appendVal("5", false)
            numberSix.appendVal("6", false)
            numberSeven.appendVal("7", false)
            numberEight.appendVal("8", false)
            numberNine.appendVal("9", false)

            dot.appendVal(".", false)
            clear.appendVal("", true)
            divide.appendVal("/", false)
            multiply.appendVal("*", false)
            minus.appendVal("-", false)
            plus.appendVal("+", false)

            delete.setOnClickListener {
                val expression = txtPlaceHolder.text.toString()
                if (expression.isNotEmpty()) {
                    txtPlaceHolder.text = expression.substring(0, expression.length - 1)
                }
            }

            equals.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(txtPlaceHolder.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()) {
                        "= $longResult".also { txtResult.text = it }
                    } else {
                        "= $result".also { txtResult.text = it }
                    }
                } catch (e: Exception) {
                    Log.d("Exception", "Message: ${e.message}")
                }
            }
        }
    }

    private fun View.appendVal(string: String, isClear: Boolean) {
        setOnClickListener {
            if (isClear) {
                binding.txtPlaceHolder.text = ""
                binding.txtResult.text = ""
            } else {
                binding.txtPlaceHolder.append(string)
            }
        }
    }
}