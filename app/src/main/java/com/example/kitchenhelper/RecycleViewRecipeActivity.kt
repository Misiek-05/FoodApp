package com.example.kitchenhelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewRecipeActivity(private val indexList: ArrayList<Int>, private val recipesList: ArrayList<String>) :
    RecyclerView.Adapter<RecycleViewRecipeActivity.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecipe: TextView
        val btDelete: Button

        init {
            tvRecipe = view.findViewById(R.id.tvRecipe)
            btDelete = view.findViewById(R.id.btDelete)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recipe_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val index = indexList[position]
        viewHolder.tvRecipe.text = recipesList[position]

    }


    override fun getItemCount(): Int {
        return indexList.size
    }

}