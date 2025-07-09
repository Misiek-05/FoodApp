package com.example.kitchenhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var btList: Button
    private lateinit var btRecipe: Button
    private lateinit var btNutrition: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)

        btList = findViewById(R.id.btList)
        btRecipe = findViewById(R.id.btRecipe)
        btNutrition = findViewById(R.id.btNutrition)

        btList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        btRecipe.setOnClickListener {

        }

        btNutrition.setOnClickListener {

        }
    }
}
