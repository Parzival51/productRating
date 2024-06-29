package com.example.productrating.data

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.example.productrating.api.model.ApiListResponse
import com.example.productrating.api.model.Product
import com.example.productrating.api.model.json

suspend fun addProduct(product: Product): String{
    return window.api.tryPost(
        apiPath =  "addproduct",
        body = Json.encodeToString(product).encodeToByteArray()
    )?.decodeToString().toString()
}

suspend fun  getAllSubscriber(
    onSuccess : (ApiListResponse) -> Unit,
    onError: (Exception) -> Unit
)
{
    try{
        val result = window.api.tryGet(
            apiPath = "getallproducts"
        )?.decodeToString()
        if (result!= null)
        {
            onSuccess(Json.decodeFromString((result)))
        }else{
            onError(Exception("something went wrong"))
        }

    }catch (e: Exception){
        println(e)
        onError(e)
    }

}

suspend fun getAllFoods(
    onSuccess: (ApiListResponse) -> Unit,
    onError: (Exception) -> Unit
) {
    try {
        val result = window.api.tryGet(apiPath = "getallfoods")?.decodeToString()
        if (result != null) {
            onSuccess(Json.decodeFromString(result))
        } else {
            onError(Exception("Something went wrong"))
        }
    } catch (e: Exception) {
        println(e)
        onError(e)
    }
}

suspend fun getAllDrinks(
    onSuccess: (ApiListResponse) -> Unit,
    onError: (Exception) -> Unit
) {
    try {
        val result = window.api.tryGet(apiPath = "getalldrinks")?.decodeToString()
        if (result != null) {
            onSuccess(Json.decodeFromString(result))
        } else {
            onError(Exception("Something went wrong"))
        }
    } catch (e: Exception) {
        println(e)
        onError(e)
    }
}


suspend fun getAllColas(
    onSuccess: (ApiListResponse) -> Unit,
    onError: (Exception) -> Unit
) {
    try {
        val result = window.api.tryGet(apiPath = "getallcolas")?.decodeToString()
        if (result != null) {
            val apiResponse = json.decodeFromString(ApiListResponse.serializer(), result)
            onSuccess(apiResponse)
        } else {
            onError(Exception("Something went wrong"))
        }
    } catch (e: Exception) {
        onError(e)
    }
}


