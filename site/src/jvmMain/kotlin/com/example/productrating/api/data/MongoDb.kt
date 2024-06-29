package com.example.productrating.api.data

import com.example.productrating.api.model.Product

interface MongoDb {

    suspend fun getAllProducts(): List<Product>
    suspend fun getAllFoods(): List<Product>
    suspend fun getAllDrinks(): List<Product>
    suspend fun addProduct(product: Product):Boolean
    suspend fun getAllColas(): List<Product>

}