package com.example.android1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android1.databinding.ActivityMainBinding

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var unit_kg: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Memory.loadData(applicationContext)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.kg -> {
                unit_kg = true
                binding.apply {
                    massTV.text = getString(R.string.mass_kg)
                    heightTV.text = getString(R.string.height_cm)
                }
                true
            }
            R.id.lb -> {
                unit_kg = false
                binding.apply {
                    massTV.text = getString(R.string.mass_lb)
                    heightTV.text = getString(R.string.height_in)
                }
                true
            }
            R.id.history -> {
                loadHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.unit_menu, menu)
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        binding.apply {
            outState?.run {
                putString("massET", massET.text.toString())
                putString("heightET", heightET.text.toString())
                putString("bmiTV", bmiTV.text.toString())
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.apply {
            massET.setText(savedInstanceState.getString("massET"))
            heightET.setText(savedInstanceState.getString("heightET"))
            bmiTV.text = savedInstanceState.getString("bmiTV")
        }
    }

    fun loadHistory() {
        val intent = Intent(this@MainActivity, History::class.java)
        startActivity(intent)
    }

    fun viewResult(view: View) {
        var bmi: Float
        binding.apply {
            bmi = bmiTV.text.toString().toFloat()
        }
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("Value", bmi)

        startActivityForResult(intent, 2)
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormatter.setLenient(false)
        val today = Date()
        val s: String = dateFormatter.format(today)
        return s
    }


    fun count(view: View) {
        binding.apply {
            when {
                massET.text.isBlank() -> massET.error = getString(R.string.mass_is_empty)
                heightET.text.isBlank() -> heightET.error = getString(R.string.height_is_empty)
                massET.text.toString().toDouble() < 0 -> massET.error = getString(R.string.mass_value_bad)
                heightET.text.toString().toDouble() < 0 -> heightET.error = getString(R.string.height_value_bad)
                else -> {
                    val mass: Double = massET.text.toString().toDouble()
                    val height: Double = heightET.text.toString().toDouble()
                    val result: Double
                    when {
                        unit_kg -> {
                            result = BMICount(mass, height).bmi_kg()
                            bmiTV.text = result.toString()
                            var historyLog =
                                historyRecord(result, mass, height, getCurrentDate(), 1)
                            Memory.addLogToHistory(historyLog)
                        }
                        else -> {
                            result = BMICount(mass, height).bmi_lb()
                            bmiTV.text = result.toString()
                            var historyLog =
                                historyRecord(result, mass, height, getCurrentDate(), 2)
                            Memory.addLogToHistory(historyLog)
                        }
                    }
                    Memory.saveData(applicationContext)
                    setColor(result)
                }
            }
        }
    }

    fun setColor(result: Double) {
        binding.apply {
            when {
                result < 16.0 -> {
                    bmiTV.setTextColor(Color.parseColor("#082E79"))
                }
                result < 16.99 -> {
                    bmiTV.setTextColor(Color.parseColor("#4169E1"))
                }
                result < 18.49 -> {
                    bmiTV.setTextColor(Color.parseColor("#ACE1AF"))
                }
                result < 24.99 -> {
                    bmiTV.setTextColor(Color.parseColor("#CDEBA7"))
                }
                result < 29.99 -> {
                    bmiTV.setTextColor(Color.parseColor("#FFFF99"))
                }
                result < 34.99 -> {
                    bmiTV.setTextColor(Color.parseColor("#FDE456"))
                }
                result < 39.99 -> {
                    bmiTV.setTextColor(Color.parseColor("#CF2929"))
                }
                else -> {
                    bmiTV.setTextColor(Color.parseColor("#801818"))
                }
            }

        }
    }
}

