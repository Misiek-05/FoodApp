package com.example.kitchenhelper.Recipes

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenhelper.DataBases.RecipesDataBase
import com.example.kitchenhelper.R

class RecycleViewRecipeActivity(private val indexList: ArrayList<Int>, private val recipesList: ArrayList<String>) :
    RecyclerView.Adapter<RecycleViewRecipeActivity.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecipe: TextView
        val btDelete: Button
        val itemContainer: View

        init {
            tvRecipe = view.findViewById(R.id.tvRecipe)
            btDelete = view.findViewById(R.id.btDelete)
            itemContainer = itemView.findViewById(R.id.item_container)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recipe_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val index = indexList[position]
        val recipeName = recipesList[position]
        viewHolder.tvRecipe.text = recipesList[position]

        viewHolder.btDelete.setOnClickListener {
            val context = viewHolder.btDelete.context
            val db : RecipesDataBase = RecipesDataBase(context)
            db.deleteRecipe(index)

            Toast.makeText(context, "Recipe has been deleted", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, RecipesActivity::class.java))
            (context as Activity).finish()
        }

        viewHolder.itemContainer.setOnClickListener {
            val context = viewHolder.itemContainer.context
            val intent = Intent(context, RecipeViewActivity::class.java)
            intent.putExtra("recipe", recipeName)
            intent.putExtra("index", index)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return indexList.size
    }

}