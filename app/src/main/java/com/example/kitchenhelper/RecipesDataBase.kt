package com.example.kitchenhelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RecipesDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "RecipesDataBase.db"
        private const val DATABASE_VERSION = 2

        const val TABLE_NAME = "Recipes"
        const val COLUMN_ID = "id"
        const val COLUMN_RECIPE = "recipe"
        const val COLUMN_ABOUT = "about"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RECIPE TEXT,
                $COLUMN_ABOUT TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addRecipe(recipe: String, about: String): Long {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_RECIPE, recipe)
            put(COLUMN_ABOUT, about)
        }

        return db.insert(TABLE_NAME, null, values)
    }

    fun getRecipes(): Cursor {
        val query: String = "SELECT * FROM " + TABLE_NAME
        val db: SQLiteDatabase = this.readableDatabase

        val cursor: Cursor = db.rawQuery(query, null)

        return cursor
    }

    fun deleteRecipe(index: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(index.toString()))
        return result
    }
}