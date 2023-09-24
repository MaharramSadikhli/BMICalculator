package com.imsoft.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imsoft.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightPicker.minValue = 20
        binding.weightPicker.maxValue = 200

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        binding.weightPicker.setOnValueChangedListener { _, _, _ ->
            calculatorBMI()
        }

        binding.heightPicker.setOnValueChangedListener { _, _, _ ->
            calculatorBMI()
        }
    }


    private fun calculatorBMI() {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100
        
        val weight = binding.weightPicker.value
        
        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)
        
        binding.resultsTV.text = String.format("BMI sonucunuz: %.2f", bmi)
        binding.healthyTV.text = String.format("Durum: %s", healthyMessage(bmi))
    }

    private fun healthyMessage(bmi: Double): String {
        if (bmi < 18.5)
            return "İdeal kilonun altında"
        if (bmi < 25.0)
            return "İdeal kiloda"
        if (bmi < 30.0)
            return "İdeal kilonun üstünde"
        if (bmi < 40)
            return "Obez"

        return "Morbid Obez"

    }
}