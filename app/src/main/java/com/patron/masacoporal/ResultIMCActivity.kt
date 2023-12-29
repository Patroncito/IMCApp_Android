package com.patron.masacoporal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.patron.masacoporal.HomeViewActyivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvStatus : TextView
    private lateinit var tvResult : TextView
    private lateinit var tvDescription : TextView
    private  lateinit var btnReCalculate : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val resultIMC : String = intent.extras?.getString(IMC_KEY) ?: ""

        initComponents()
        initUI(resultIMC)
    }



    private fun initComponents() {

        tvStatus = findViewById(R.id.tvStatus)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)

    }
    private fun initUI(result : String) {
        tvResult.text = result
        val resultInDouble : Double =  result.toDouble()

        when (resultInDouble) {
            in 0.0..18.5 -> {
                tvStatus.text = getString(R.string.weightLow)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.azulCyan))
                tvDescription.text = getString(R.string.weightLowDescription)

            }

            in 18.5..24.9 -> {
                tvStatus.text = getString(R.string.normalWeight)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.azulClaro))
                tvDescription.text = getString(R.string.weightNormal)

            }
            in 25.0..29.9 -> {
                tvStatus.text = getString(R.string.weightHigh)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.verde))
                tvDescription.text = getString(R.string.weightHighDescription)

            }
            in 30.0..34.9 -> {
                tvStatus.text = getString(R.string.overweight1)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.amarillo))
                tvDescription.text = getString(R.string.weightHigDescription)
            }
            in 35.0..39.9 -> {
                tvStatus.text = getString(R.string.overweight2)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.naranja))
                tvDescription.text = getString(R.string.overweight2Description)
            }
            in 40.0..100.0 -> {
                tvStatus.text = getString(R.string.overweight3)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.naranja))
                tvDescription.text = getString(R.string.overweight3Description)
            }
            else -> {
                tvResult.text = "0"
                tvStatus.text = getString(R.string.errorStatus)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvDescription.text = getString(R.string.errorDescription)
            }
        }

    }
}