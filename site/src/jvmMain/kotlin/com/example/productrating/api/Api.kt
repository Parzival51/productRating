package com.example.productrating.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import com.example.productrating.api.data.MongoDbImpl
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.example.productrating.api.model.ApiListResponse
import com.example.productrating.api.model.Product
import com.example.productrating.api.model.json
import org.bson.codecs.ObjectIdGenerator

@Api(routeOverride = "addproduct")
suspend fun addProduct(context: ApiContext){
    try{
        val subscriber = context.req.body?.decodeToString()?.let { Json.decodeFromString<Product>(it) }
        val newSubscriber = subscriber?.copy(id = ObjectIdGenerator().generate().toString())
        val responseBody = Json.encodeToString(newSubscriber?.let { context.data.getValue<MongoDbImpl>().addProduct(it)})
        context.res.setBodyText(responseBody)
    }catch (e: Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}

@Api(routeOverride = "getallproducts")
suspend fun getAllSubscribers(context: ApiContext){
    try{
        val myUsers = context.data.getValue<MongoDbImpl>().getAllProducts()
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Success(data = myUsers)))
    }catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Error(message = e.message.toString())))
    }
}

@Api(routeOverride = "getallfoods")
suspend fun getAllFoods(context: ApiContext) {
    try {
        val foods = context.data.getValue<MongoDbImpl>().getAllFoods()
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Success(data = foods)))
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Error(message = e.message.toString())))
    }
}

@Api(routeOverride = "getalldrinks")
suspend fun getAllDrinks(context: ApiContext) {
    try {
        val drinks = context.data.getValue<MongoDbImpl>().getAllDrinks()
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Success(data = drinks)))
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Error(message = e.message.toString())))
    }
}



@Api(routeOverride = "getallcolas")
suspend fun getAllColas(context: ApiContext) {
    try {
        val colas = context.data.getValue<MongoDbImpl>().getAllColas()
        context.res.setBodyText(json.encodeToString(ApiListResponse.serializer(), ApiListResponse.Success(data = colas)))
    } catch (e: Exception) {
        context.res.setBodyText(json.encodeToString(ApiListResponse.serializer(), ApiListResponse.Error(message = e.message.toString())))
    }
}


