package com.example.productrating.api.data

import com.example.productrating.api.model.Product
import com.example.productrating.api.model.User
import org.bson.types.ObjectId

interface MongoDb {

    suspend fun getAllProducts(): List<Product>
    suspend fun getAllFoods(): List<Product>
    suspend fun getAllDrinks(): List<Product>
    suspend fun addProduct(product: Product):Boolean
    suspend fun getAllColas(): List<Product>
    suspend fun getProductById(productId: String): Product?
    suspend fun updateProduct(product:Product): Boolean
    suspend fun addReview(productId: String, username: String, rating: Int, comment: String): Boolean
    suspend fun addUser(user: User): Boolean
    suspend fun getUserByUsernameOrEmail(usernameOrEmail: String): User?
    suspend fun authenticateUser(usernameOrEmail: String, password: String): User?
    suspend fun deleteReview(productId: String, username: String): Boolean


}