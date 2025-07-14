import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class IngredientsDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "IngredientsDataBase.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "products"
        const val COLUMN_ID = "id"
        const val COLUMN_RECIPE = "recipe"
        const val COLUMN_NAME = "ingredient"
        const val COLUMN_QUANTITY = "quantity"
        const val COLUMN_UNIT = "unit"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RECIPE TEXT, 
                $COLUMN_NAME TEXT,
                $COLUMN_QUANTITY INTEGER,
                $COLUMN_UNIT TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProduct(recipe: String, ingredient: String, quantity: Int, unit: String): Long {
        val db = this.writableDatabase

        val cursor: Cursor = db.query(TABLE_NAME, arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_QUANTITY, COLUMN_UNIT), "$COLUMN_NAME=? AND $COLUMN_RECIPE=?", arrayOf(ingredient, recipe), null, null, null)

        return if (cursor.moveToFirst()) {
            val id = cursor.getString(0)
            val newQuantity = cursor.getInt(2) + quantity

            val values = ContentValues().apply {
                put(COLUMN_QUANTITY, newQuantity)
            }

            db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id))
            id.toLong()
        } else {
            val values = ContentValues().apply {
                put(COLUMN_RECIPE, recipe)
                put(COLUMN_NAME, ingredient)
                put(COLUMN_QUANTITY, quantity)
                put(COLUMN_UNIT, unit)
            }

            db.insert(TABLE_NAME, null, values)
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
