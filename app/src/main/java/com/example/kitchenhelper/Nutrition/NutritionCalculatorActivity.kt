package com.example.kitchenhelper.Nutrition

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenhelper.DataBases.CalculatorDataBase
import com.example.kitchenhelper.R
import kotlin.math.floor

class NutritionCalculatorActivity : ComponentActivity() {

    private lateinit var btAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var calculatorDataBase: CalculatorDataBase
    private lateinit var productsCursor: Cursor
    private lateinit var gridLayout: GridLayout

    private val indexList = ArrayList<Int>()
    private val productsList = ArrayList<String>()
    private val quantityList = ArrayList<Int>()
    private val unitList = ArrayList<String>()

    private lateinit var tvKcal: TextView
    private lateinit var tvFats: TextView
    private lateinit var tvProtein: TextView
    private lateinit var tvFiber: TextView
    private lateinit var tvCarbs: TextView
    private lateinit var tvSugar: TextView

    private var kcal: Double = 0.0
    private var fats: Double = 0.0
    private var protein: Double = 0.0
    private var fiber: Double = 0.0
    private var carbs: Double = 0.0
    private var sugar: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutrition_calculator_activity_layout)

        btAdd = findViewById(R.id.btAddToCalculator)
        recyclerView = findViewById(R.id.recyclerView)
        gridLayout = findViewById(R.id.gridLayout2)

        tvKcal = findViewById(R.id.item2)
        tvFats = findViewById(R.id.item4)
        tvProtein = findViewById(R.id.item6)
        tvFiber = findViewById(R.id.item8)
        tvCarbs = findViewById(R.id.item10)
        tvSugar = findViewById(R.id.item12)

        createView()

        if(indexList.size != 0) {
            gridLayout.visibility = VISIBLE

            tvKcal.text = kcal.toString() + "g"
            tvFats.text = fats.toString() + "g"
            tvProtein.text = protein.toString() + "g"
            tvFiber.text = fiber.toString() + "g"
            tvCarbs.text = carbs.toString() + "g"
            tvSugar.text = sugar.toString() + "g"
        }

        btAdd.setOnClickListener {
            val intent = Intent(this, AddToCalculatorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        createView()
        if(indexList.size != 0) {
            gridLayout.visibility = VISIBLE

            tvKcal.text = kcal.toString() + "g"
            tvFats.text = fats.toString() + "g"
            tvProtein.text = protein.toString() + "g"
            tvFiber.text = fiber.toString() + "g"
            tvCarbs.text = carbs.toString() + "g"
            tvSugar.text = sugar.toString() + "g"
        }
    }

    private fun createView() {
        calculatorDataBase = CalculatorDataBase(this)
        productsCursor = calculatorDataBase.getProducts()

        indexList.clear()
        productsList.clear()
        quantityList.clear()
        unitList.clear()

        kcal = 0.0
        fats = 0.0
        protein = 0.0
        fiber = 0.0
        carbs = 0.0
        sugar = 0.0
        
        while(productsCursor.moveToNext()) {
            indexList.add(productsCursor.getInt(0))
            productsList.add(productsCursor.getString(1))
            quantityList.add(productsCursor.getInt(2))
            unitList.add(productsCursor.getString(3))
            kcal += floor(productsCursor.getDouble(4))
            fats += floor(productsCursor.getDouble(5))
            protein += floor(productsCursor.getDouble(6))
            fiber += floor(productsCursor.getDouble(7))
            carbs += floor(productsCursor.getDouble(8))
            sugar += floor(productsCursor.getDouble(9))
        }

        val customAdapter = RecycleViewCalculatorViewActivity(indexList, productsList, quantityList, unitList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
    }


}