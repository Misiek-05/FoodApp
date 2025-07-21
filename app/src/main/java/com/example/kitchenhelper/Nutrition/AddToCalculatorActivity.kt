package com.example.kitchenhelper.Nutrition

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.kitchenhelper.DataBases.CalculatorDataBase
import com.example.kitchenhelper.R

class AddToCalculatorActivity : ComponentActivity() {

    private lateinit var unitSpinner: Spinner
    private lateinit var tvProduct: EditText
    private lateinit var tvQuantity: EditText
    private lateinit var btAddProduct: Button
    private lateinit var calculatorDataBase: CalculatorDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)

        calculatorDataBase = CalculatorDataBase(this)
        tvProduct = findViewById(R.id.tvProductInput)
        tvQuantity = findViewById(R.id.tvQuantityInput)
        btAddProduct = findViewById(R.id.btAddProduct)

        spinnerSetting()

        btAddProduct.setOnClickListener {

            val productInput = tvProduct.text.toString().trim()
            val quantityInput = tvQuantity.text.toString().trim()
            val value = quantityInput.toIntOrNull()
            val spinnerInput = unitSpinner.selectedItem.toString()

            if (productInput.isNotEmpty() && value != null) {
                calculatorDataBase.addProduct(productInput, value, spinnerInput, this)
                Toast.makeText(this,"Product has been added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Missing input", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun spinnerSetting() {
        unitSpinner = findViewById(R.id.SpinnerUnit)
        val adapter = ArrayAdapter.createFromResource(this, R.array.unit_options_calculator, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter
    }
}