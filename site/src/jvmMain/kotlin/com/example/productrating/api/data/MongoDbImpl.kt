package com.example.productrating.api.data

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.toList
import com.example.productrating.api.model.Product

@InitApi
fun initializeMongo(context: InitApiContext){
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDbImpl())
}


class MongoDbImpl(): MongoDb {

    private val client = MongoClient.create()
    private val database = client.getDatabase("productrating")
    private val productCollection = database.getCollection<Product>("products")



    override suspend fun getAllProducts(): List<Product> {
        return productCollection.find().toList()
    }

    override suspend fun getAllFoods(): List<Product> {
        return productCollection.find(eq("category", "Food")).toList()
    }

    override suspend fun getAllDrinks(): List<Product> {
        return productCollection.find(eq("category", "Drink")).toList()
    }

    override suspend fun addProduct(product: Product): Boolean {
        return productCollection.insertOne(product).wasAcknowledged()
    }

    override suspend fun getAllColas(): List<Product> {
        return productCollection.find((eq("category","Cola"))).toList()
    }


}