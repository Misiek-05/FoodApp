package com.example.kitchenhelper

import ShoppingListDataBase
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : ComponentActivity() {

    private lateinit var shoppingListdb: ShoppingListDataBase
    private lateinit var btAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsCursor: Cursor
    private val indexList = ArrayList<Int>()
    private val productsList = ArrayList<String>()
    private val quantityList = ArrayList<Int>()
    private val unitList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_list_layout)

        btAdd = findViewById(R.id.btAdd)
        recyclerView = findViewById(R.id.recyclerView)

        shoppingListdb = ShoppingListDataBase(this)
        productsCursor = shoppingListdb.getProducts()

        indexList.clear()
        productsList.clear()
        quantityList.clear()
        unitList.clear()

        while(productsCursor.moveToNext()) {
            indexList.add(productsCursor.getString(0).toInt())
            productsList.add(productsCursor.getString(1))
            quantityList.add(productsCursor.getString(2).toInt())
            unitList.add(productsCursor.getString(3))
        }

        val customAdapter = CustomAdapter(indexList, productsList, quantityList, unitList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter


        btAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}