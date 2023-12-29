package com.patron.masacoporal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class HomeViewActyivity : AppCompatActivity() {

    private var currentWeight : Int = 60
    private var isMaleSected: Boolean = true
    private var isFemaleSelected : Boolean = false
    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView
    private lateinit var tvHeight : TextView
    private lateinit var rsHeight : RangeSlider
    private lateinit var tvWeight : TextView
    private lateinit var fbSubstractWeight : FloatingActionButton
    private lateinit var fbPlusWeight : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view_actyivity)
        // (":)")

        initComponents()
        initListeners()


    }

    private fun initListeners() {

        cardMale.setOnClickListener{
            changeGender()
            setGenderColor( )
        }

        cardFemale.setOnClickListener{
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, data, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(data)
            tvHeight.text = "$result cm"

        }

        fbSubstractWeight.setOnClickListener{
            if (currentWeight >= 21) {
                currentWeight--
                tvWeight.text = currentWeight.toString()
            }

        }
        fbPlusWeight.setOnClickListener{
            // Sumar a la edad
            if (currentWeight <= 199) {
                currentWeight++
                tvWeight.text = currentWeight.toString()
            }


        }

    }

    private fun changeGender(){
            isMaleSected = !isMaleSected
            isFemaleSelected = !isFemaleSelected

    }
    private fun initComponents() {
        cardMale = findViewById(R.id.cardMale)
        cardFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvWeight.text = currentWeight.toString()
        fbSubstractWeight = findViewById(R.id.fbSubtractWeight)
        fbPlusWeight = findViewById(R.id.fbPlusWeight)
    }

    private fun setGenderColor() {
        cardMale.setCardBackgroundColor(getBackgroundColor(isMaleSected))
        cardFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isCardSelected : Boolean): Int{
        val realColor  = if (isCardSelected){
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, realColor)
    }
}