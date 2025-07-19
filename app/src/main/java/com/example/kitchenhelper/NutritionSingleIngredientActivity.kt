package com.example.kitchenhelper

import Product
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class NutritionSingleIngredientActivity : ComponentActivity() {

    private lateinit var grid: GridLayout
    private lateinit var btSingleIngredientNutritionCheck: Button
    private lateinit var tvSingleIngredient: EditText

    private lateinit var tvKcal: TextView
    private lateinit var tvFats: TextView
    private lateinit var tvProtein: TextView
    private lateinit var tvFiber: TextView
    private lateinit var tvCarbs: TextView
    private lateinit var tvSugar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutrition_single_ingredient_layout)

        grid = findViewById(R.id.gridLayout)
        btSingleIngredientNutritionCheck = findViewById(R.id.btSingleIngredientNutritionCheck)
        tvSingleIngredient = findViewById(R.id.tvSingleIngredient)

        tvKcal = findViewById(R.id.item2)
        tvFats = findViewById(R.id.item4)
        tvProtein = findViewById(R.id.item6)
        tvFiber = findViewById(R.id.item8)
        tvCarbs = findViewById(R.id.item10)
        tvSugar = findViewById(R.id.item12)

        btSingleIngredientNutritionCheck.setOnClickListener {
            val input = tvSingleIngredient.text.toString().trim()
            if(input.isNotEmpty()) {
                val food = Product(this, input, 10.0)
                if(food.doesExist()) {
                    grid.visibility = VISIBLE
                    tvKcal.text = food.getKcal().toString() + "g"
                    tvFats.text = food.getFats().toString() + "g"
                    tvProtein.text = food.getProtein().toString() + "g"
                    tvFiber.text = food.getFiber().toString() + "g"
                    tvCarbs.text = food.getCarbs().toString() + "g"
                    tvSugar.text = food.getSugar().toString() + "g"
                }
                else {
                    title = food.getName()
                    grid.visibility = INVISIBLE
                    Toast.makeText(this, "There isn't", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                grid.visibility = INVISIBLE
                Toast.makeText(this, "Fill the input", Toast.LENGTH_SHORT).show()
            }
        }
    }

}