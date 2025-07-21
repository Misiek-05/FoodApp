package com.example.kitchenhelper.ShoppingList

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenhelper.DataBases.ShoppingListDataBase
import com.example.kitchenhelper.R

class RecycleViewListActivity(private val indexList: ArrayList<Int>, private val productList: ArrayList<String>, private val quantityList: ArrayList<Int>, private val unitList: ArrayList<String>) :
    RecyclerView.Adapter<RecycleViewListActivity.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProduct: TextView
        val tvQuantity: TextView
        val tvUnit: TextView
        val btDelete: Button

        init {
            tvProduct = view.findViewById(R.id.tvProduct)
            tvQuantity = view.findViewById(R.id.tvQuantity)
            tvUnit = view.findViewById(R.id.tvUnit)
            btDelete = view.findViewById(R.id.btDelete)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val index = indexList[position]
        viewHolder.tvProduct.text = productList[position]
        viewHolder.tvQuantity.text = quantityList[position].toString()
        viewHolder.tvUnit.text = unitList[position]

        viewHolder.btDelete.setOnClickListener {
            val context = viewHolder.itemView.context
            val db = ShoppingListDataBase(context)
            db.deleteProduct(index)

            Toast.makeText(context, "com.example.kitchenhelper.Nutrition.Product has been deleted",Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, ListActivity::class.java))
            (context as Activity).finish()
        }
    }

    override fun getItemCount() = productList.size

}