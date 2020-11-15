package com.example.android1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android1.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    var returnString: Float = 0.0f

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_second)
        returnString = intent.getFloatExtra("Value", 0.0f)
        chooseDescription(returnString)
        binding.apply {
            val textView = findViewById<TextView>(R.id.yourBMI)
            textView.text = "Your BMI is: $returnString"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.apply {
            outState?.run {
                putFloat("Value", returnString)
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.apply {
            returnString = savedInstanceState.getFloat("Value")
            chooseDescription(returnString)
            val textView = findViewById<TextView>(R.id.yourBMI)
            textView.text = "Your BMI is: $returnString"
        }
    }

    fun chooseDescription(result: Float?)
    {
        binding.apply {
            val data = findViewById<TextView>(R.id.value)
            if (result != null) {
                when {
                    result < 16.0 -> {
                        data.text = getString(R.string.underweight3)
                    }
                    result < 16.99 -> {
                        data.text = getString(R.string.underweight2)
                    }
                    result < 18.49 -> {
                        data.text = getString(R.string.underweight1)
                    }
                    result < 24.99 -> {
                        data.text = getString(R.string.normal)
                    }
                    result < 29.99 -> {
                        data.text = getString(R.string.overweight)
                    }
                    result < 34.99 -> {
                        data.text = getString(R.string.obese1)
                    }
                    result < 39.99 -> {
                        data.text = getString(R.string.obese2)
                    }
                    else -> {
                        data.text = getString(R.string.obese3)
                    }
                }
            }
        }
    }


    fun buttonClick2(view: View) {

        val stringToPassBack = "done"
        val intent = Intent()
        intent.putExtra("keyName", stringToPassBack)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}