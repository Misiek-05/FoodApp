package com.example.kitchenhelper

import android.os.Bundle
import android.widget.Spinner
import androidx.activity.ComponentActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import ShoppingListDataBase
import android.content.Intent
import android.widget.Toast

class AddActivity : ComponentActivity() {

    private lateinit var unitSpinner: Spinner
    private lateinit var tvProduct: EditText
    private lateinit var tvQuantity: EditText
    private lateinit var btAddProduct: Button
    private lateinit var shoppingListdb: ShoppingListDataBase

    private fun spinnerSetting() {
        unitSpinner = findViewById(R.id.SpinnerUnit)
        val adapter = ArrayAdapter.createFromResource(this, R.array.unit_options, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)

        shoppingListdb = ShoppingListDataBase(this)

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
                shoppingListdb.addProduct(productInput, value, spinnerInput)
                Toast.makeText(this,"Produkt został dodany", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Podaj Produkt i ilość", Toast.LENGTH_SHORT).show()
            }

        }

    }

}