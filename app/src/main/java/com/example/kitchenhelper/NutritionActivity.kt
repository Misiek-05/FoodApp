package com.example.kitchenhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class NutritionActivity : ComponentActivity() {

    private lateinit var btSingleIngredients: Button
    private lateinit var btCalculator: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutririon_activity)

        btSingleIngredients = findViewById(R.id.btSingleIngredient)
        btCalculator = findViewById(R.id.btCalculator)

        btSingleIngredients.setOnClickListener {
            val intent = Intent(this, NutritionSingleIngredientActivity::class.java)
            startActivity(intent)
        }

        btCalculator.setOnClickListener {

        }
    }
}