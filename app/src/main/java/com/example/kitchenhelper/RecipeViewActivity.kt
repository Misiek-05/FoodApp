package com.example.kitchenhelper

import android.database.Cursor
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class RecipeViewActivity : ComponentActivity() {

    private lateinit var recipeName: TextView
    private lateinit var about: TextView
    private lateinit var recipebd: RecipesDataBase
    private lateinit var recipeCursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_view_layout)

        val index = intent.getIntExtra("index", -1)
        val recipe = intent.getStringExtra("recipe")
        title = "$recipe"

        recipebd = RecipesDataBase(this)
        recipeCursor = recipebd.getRecipe(index.toString())

        recipeName = findViewById(R.id.tvRecipeName)
        about = findViewById(R.id.tvAbout)


        recipeName.text = recipe

        recipeCursor = recipebd.getRecipe(index.toString())

        if (recipeCursor.moveToFirst()) {
            recipeName.text = recipe
            about.text = recipeCursor.getString(2)
        }
    }
}