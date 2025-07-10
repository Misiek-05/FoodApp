package com.example.kitchenhelper

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class RecipesActivity : ComponentActivity() {

    private lateinit var recipesdb: RecipesDataBase
    private lateinit var btAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeCursor: Cursor
    private val indexList = ArrayList<Int>()
    private val recipeList =  ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_layout)

        btAdd = findViewById(R.id.btAdd)
        recyclerView = findViewById(R.id.recyclerView)

        recipesdb = RecipesDataBase(this)
        recipeCursor = recipesdb.getRecipes()

        indexList.clear()
        recipeList.clear()

        while (recipeCursor.moveToNext()) {
            indexList.add(recipeCursor.getInt(0))
            recipeList.add(recipeCursor.getString(1))
        }

        val customAdapter = RecycleViewRecipeActivity(indexList, recipeList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        btAdd.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }

    }
}