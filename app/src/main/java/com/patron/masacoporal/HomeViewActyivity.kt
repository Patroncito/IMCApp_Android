package com.patron.masacoporal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text
import java.text.DecimalFormat
import kotlin.math.pow


class HomeViewActyivity : AppCompatActivity() {
    private var currentWeight: Int = 60
    private var currentAge: Int = 20
    private var currentHeight: Int = 120
    private var isMaleSected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var fbSubstractWeight: FloatingActionButton
    private lateinit var fbPlusWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var fbSubstractAge: FloatingActionButton
    private lateinit var fbPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button


    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view_actyivity)
        // (":)")
        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {

        cardMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        cardFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, data, _ ->
            rsSlider(data)

        }

        fbSubstractWeight.setOnClickListener {
            fbSubstract(currentWeight, 51, tvWeight, true)


        }
        fbPlusWeight.setOnClickListener {
            fbPlus(currentWeight, 69, tvWeight, true)

        }

        fbSubstractAge.setOnClickListener {
            fbSubstract(currentAge, 17, tvAge, false)

        }

        fbPlusAge.setOnClickListener {
            fbPlus(currentAge, 99, tvAge, false)
        }


        btnCalculate.setOnClickListener {
            var result = calculateIMC()
            navigateToResult(result)
        }

    }


    private fun initComponents() {
        cardMale = findViewById(R.id.cardMale)
        cardFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        fbSubstractWeight = findViewById(R.id.fbSubtractWeight)
        fbPlusWeight = findViewById(R.id.fbPlusWeight)
        tvAge = findViewById(R.id.tvAge)
        fbSubstractAge = findViewById(R.id.fbSubtractAge)
        fbPlusAge = findViewById(R.id.fbPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initUI() {
        setGenderColor()
        tvWeight.text = currentWeight.toString()
        tvAge.text = currentAge.toString()
        setGenderColor()
    }

    private fun fbSubstract(
        currentValue: Int,
        minLimit: Int,
        textView: TextView,
        isWeightBtn: Boolean
    ) {

        var updatedValue = currentValue
        if (currentValue >= minLimit) {
            updatedValue--
            textView.text = updatedValue.toString()

            if (isWeightBtn) {
                currentWeight--
            } else {
                currentAge--
            }

        }
    }

    private fun fbPlus(
        currentValue: Int,
        maxValue: Int,
        textView: TextView,
        isWeightBtn: Boolean
    ) {

        var updateValue = currentValue
        if (currentValue <= maxValue) {
            updateValue++
            textView.text = updateValue.toString()

            if (isWeightBtn) {
                currentWeight++
            } else {
                currentAge++
            }

        }
    }

    private fun rsSlider(data: Float) {
        val df = DecimalFormat("#.##")
        currentHeight = df.format(data).toInt()
        tvHeight.text = "$currentHeight cm"
    }

    private fun changeGender() {
        isMaleSected = !isMaleSected
        isFemaleSelected = !isFemaleSelected

    }

    private fun setGenderColor() {
        cardMale.setCardBackgroundColor(getBackgroundColor(isMaleSected))
        cardFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isCardSelected: Boolean): Int {
        val realColor = if (isCardSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, realColor)
    }


    private fun calculateIMC(): String {

        val resultIMC =
            String.format("%.2f", (currentHeight / (currentWeight / 100.toDouble().pow(2.0))))
        return resultIMC

    }

    private fun navigateToResult(result: String) {
        val intentResultIMC = Intent(this, ResultIMCActivity::class.java)

        intentResultIMC.putExtra("IMC_KEY", result)
        startActivity(intent)
    }


}

