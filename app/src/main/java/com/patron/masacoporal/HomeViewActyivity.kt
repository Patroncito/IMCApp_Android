package com.patron.masacoporal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class HomeViewActyivity : AppCompatActivity() {

    private var isMaleSected: Boolean = true
    private var isFemaleSelected : Boolean = false
    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView

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
    }

    private fun changeGender(){
            isMaleSected = !isMaleSected
            isFemaleSelected = !isFemaleSelected

    }
    private fun initComponents() {
        cardMale = findViewById(R.id.cardMale)
        cardFemale = findViewById(R.id.cardFemale)
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