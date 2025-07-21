package com.example.kitchenhelper.Nutrition

import android.content.Context
import com.example.kitchenhelper.R

class Product(context: Context, name: String, weight: Double) {
    private var name: String
    private var weight: Double
    private var exist: Boolean = false

    private var kcal: Double = 0.0
    private var fats: Double = 0.0
    private var protein: Double = 0.0
    private var fiber: Double = 0.0
    private var carbs: Double = 0.0
    private var sugar: Double = 0.0

    init {
        this.name = name.trim()
        this.weight = weight

        val inputStream = context.resources.openRawResource(R.raw.food)
        val reader = inputStream.bufferedReader()

        reader.forEachLine { line ->
            val parts = line.split(";").map { it.trim() }
            if (parts.size == 7) {
                if (parts[0].equals(this.name, ignoreCase = true)) {
                    this.kcal = parts[1].toDouble()
                    this.fats = parts[2].toDouble()
                    this.protein = parts[3].toDouble()
                    this.fiber = parts[4].toDouble()
                    this.carbs = parts[5].toDouble()
                    this.sugar = parts[6].toDouble()
                    this.exist = true
                    return@forEachLine
                }
            }
        }

        reader.close()

    }

    fun getName(): String = name
    fun getWeight(): Double = weight
    fun doesExist(): Boolean = exist
    fun getKcal(): Double = kcal
    fun getFats(): Double = fats
    fun getProtein(): Double = protein
    fun getFiber(): Double = fiber
    fun getCarbs(): Double = carbs
    fun getSugar(): Double = sugar



}
