package com.example.kitchenhelper

import IngredientsDataBase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity

class AddIngredientActivity : ComponentActivity() {

    private lateinit var unitSpinner: Spinner
    private lateinit var tvIngredient: EditText
    private lateinit var tvQuantity: EditText
    private lateinit var btAddIngredient: Button
    private lateinit var ingredientsDataBase: IngredientsDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)

        val recipe = intent.getStringExtra("recipe").toString()

        ingredientsDataBase = IngredientsDataBase(this)

        tvIngredient = findViewById(R.id.tvProductInput)
        tvQuantity = findViewById(R.id.tvQuantityInput)
        btAddIngredient = findViewById(R.id.btAddProduct)

        spinnerSetting()

        btAddIngredient.setOnClickListener {
            val ingredient = tvIngredient.text.toString().trim()
            val quantity = tvQuantity.text.toString().trim()
            val value = quantity.toIntOrNull()
            val unit = unitSpinner.selectedItem.toString()

            if (ingredient.isNotEmpty() && value != null) {
                ingredientsDataBase.addProduct(recipe,ingredient,value,unit)
                Toast.makeText(this,"Składnik został dodany", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Uzupełnij dane", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun spinnerSetting() {
        unitSpinner = findViewById(R.id.SpinnerUnit)
        val adapter = ArrayAdapter.createFromResource(this, R.array.unit_options, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter
    }
}