package com.example.kitchenhelper.Recipes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.kitchenhelper.R
import com.example.kitchenhelper.DataBases.RecipesDataBase

class AddRecipeActivity : ComponentActivity() {

    private lateinit var recipesdb: RecipesDataBase
    private lateinit var tvRecipeInput: EditText
    private lateinit var tvAboutInput: EditText
    private lateinit var btAddRecipe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_recipe_layout)

        recipesdb = RecipesDataBase(this)

        tvRecipeInput = findViewById(R.id.tvRecipeInput)
        tvAboutInput = findViewById(R.id.tvAboutInput)
        btAddRecipe = findViewById(R.id.btAddRecipe)

        btAddRecipe.setOnClickListener {
            val recipeInput = tvRecipeInput.text.toString().trim()
            val aboutInput = tvAboutInput.text.toString().trim()

            if(recipeInput.isNotEmpty() && aboutInput.isNotEmpty()) {
                recipesdb.addRecipe(recipeInput, aboutInput)
                Toast.makeText(this, "Recipe has been added", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(this, "Missing input", Toast.LENGTH_SHORT).show()
            }
        }

    }
}