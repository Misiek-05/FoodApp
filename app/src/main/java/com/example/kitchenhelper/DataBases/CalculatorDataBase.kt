package com.example.kitchenhelper.DataBases

import com.example.kitchenhelper.Nutrition.Product
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.math.floor

class CalculatorDataBase(context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "CalculatorDataBase.db"
        private const val DATABASE_VERSION = 2

        const val TABLE_NAME = "Calculator"
        const val COLUMN_ID = "id"
        const val COLUMN_PRODUCT = "product"
        const val COLUMN_QUANTITY = "quantity"
        const val COLUMN_UNIT = "unit"
        const val COLUMN_KCAL = "kcal"
        const val COLUMN_FATS = "fats"
        const val COLUMN_PROTEIN = "protein"
        const val COLUMN_FIBER = "fiber"
        const val COLUMN_CARBS = "carbs"
        const val COLUMN_SUGAR = "sugar"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_PRODUCT TEXT,
                $COLUMN_QUANTITY INTEGER,
                $COLUMN_UNIT TEXT,
                $COLUMN_KCAL FLOAT, 
                $COLUMN_FATS FLOAT,
                $COLUMN_PROTEIN FLOAT,
                $COLUMN_FIBER FLOAT,
                $COLUMN_CARBS FLOAT,
                $COLUMN_SUGAR FLOAT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProduct(product: String, quantity: Int, unit: String, context: Context): Long {
        val db = this.writableDatabase

        val food: Product = Product(context, product, quantity.toDouble())

        val multiplier: Double = quantity.toDouble() / 100

        val cursor: Cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ID, COLUMN_PRODUCT, COLUMN_QUANTITY, COLUMN_UNIT, COLUMN_KCAL, COLUMN_FATS, COLUMN_PROTEIN, COLUMN_FIBER, COLUMN_CARBS, COLUMN_SUGAR),
            "$COLUMN_PRODUCT=?",
            arrayOf(product),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val id = cursor.getString(0)
            val newQuantity = cursor.getInt(2) + quantity
            val newKcal = floor(cursor.getDouble(4) * multiplier + food.getKcal())
            val newFats = floor(cursor.getDouble(5) * multiplier + food.getFats())
            val newProtein = floor(cursor.getDouble(6) * multiplier + food.getProtein())
            val newFiber = floor(cursor.getDouble(7) * multiplier + food.getFiber())
            val newCarbs = floor(cursor.getDouble(8) * multiplier + food.getCarbs())
            val newSugar = floor(cursor.getDouble(9) * multiplier + food.getSugar())

            val values = ContentValues().apply {
                put(COLUMN_QUANTITY, newQuantity)
                put(COLUMN_KCAL, newKcal)
                put(COLUMN_FATS, newFats)
                put(COLUMN_PROTEIN, newProtein)
                put(COLUMN_FIBER, newFiber)
                put(COLUMN_CARBS, newCarbs)
                put(COLUMN_SUGAR, newSugar)
            }

            db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id))
            return id.toLong()
        } else {
            val values = ContentValues().apply {
                put(COLUMN_PRODUCT, product)
                put(COLUMN_QUANTITY, quantity)
                put(COLUMN_UNIT, unit)
                put(COLUMN_KCAL, food.getKcal() * multiplier)
                put(COLUMN_FATS, food.getFats() * multiplier)
                put(COLUMN_PROTEIN, food.getProtein() * multiplier)
                put(COLUMN_FIBER, food.getFiber() * multiplier)
                put(COLUMN_CARBS, food.getCarbs() * multiplier)
                put(COLUMN_SUGAR, food.getSugar() * multiplier)
            }

            return db.insert(TABLE_NAME, null, values)
        }
    }

    fun getProducts(): Cursor {
        val query: String = "SELECT * FROM " + TABLE_NAME
        val db: SQLiteDatabase = this.readableDatabase

        val cursor: Cursor = db.rawQuery(query, null)

        return cursor
    }

    fun deleteProduct(index: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(index.toString()))
        return result
    }
}