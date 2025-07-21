package com.example.kitchenhelper.Recipes

import com.example.kitchenhelper.DataBases.IngredientsDataBase
import com.example.kitchenhelper.DataBases.ShoppingListDataBase
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenhelper.DataBases.RecipesDataBase
import com.example.kitchenhelper.R

class RecipeViewActivity : ComponentActivity() {

    private lateinit var recipeName: TextView
    private lateinit var about: TextView
    private lateinit var recipeCursor: Cursor
    private lateinit var actionButton: Button

    private lateinit var recipebd: RecipesDataBase
    private lateinit var ingredientdb : IngredientsDataBase
    private lateinit var shoppingListdb: ShoppingListDataBase

    private lateinit var recyclerView: RecyclerView
    private lateinit var productsCursor: Cursor
    private val indexList = ArrayList<Int>()
    private val ingredientList = ArrayList<String>()
    private val quantityList = ArrayList<Int>()
    private val unitList = ArrayList<String>()

    private var index = -1
    private var recipe = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_view_layout)

        index = intent.getIntExtra("index", -1)
        recipe = intent.getStringExtra("recipe").toString()

        createView()

        actionButton.setOnClickListener {
            val popup = PopupMenu(this, actionButton)
            popup.menuInflater.inflate(R.menu.recipe_action_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_add_ingredient -> {
                        val intent = Intent(this, AddIngredientActivity::class.java)
                        intent.putExtra("recipe", recipe)
                        startActivity(intent)
                        true
                    }
                    R.id.menu_add_to_list -> {
                        transferToList()
                        Toast.makeText(this, "Dodano do listy", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    override fun onStart() {
        super.onStart()
        createView()
    }

    private fun createView() {
        title = recipe

        recipebd = RecipesDataBase(this)
        recipeCursor = recipebd.getRecipe(index.toString())

        recipeName = findViewById(R.id.tvRecipeName)
        about = findViewById(R.id.tvAbout)
        actionButton = findViewById(R.id.btActionMenu)
        recyclerView = findViewById(R.id.recyclerView)

        recipeName.text = recipe
        recipeCursor = recipebd.getRecipe(index.toString())

        if (recipeCursor.moveToFirst()) {
            recipeName.text = recipe
            about.text = recipeCursor.getString(2)
        }


        ingredientdb = IngredientsDataBase(this)
        productsCursor = ingredientdb.getProducts()

        indexList.clear()
        ingredientList.clear()
        quantityList.clear()
        unitList.clear()

        while(productsCursor.moveToNext()) {
            if(productsCursor.getString(1).toString() == recipe) {
                indexList.add(productsCursor.getInt(0))
                ingredientList.add(productsCursor.getString(2))
                quantityList.add(productsCursor.getInt(3))
                unitList.add(productsCursor.getString(4))
            }
        }

        val customAdapter = RecycleViewRecipeViewActivity(indexList, ingredientList, quantityList, unitList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

    }

    private fun transferToList() {
        shoppingListdb = ShoppingListDataBase(this)
        for(item in 0..<indexList.size) {
            shoppingListdb.addProduct(ingredientList[item], quantityList[item], unitList[item])
        }
    }

}
