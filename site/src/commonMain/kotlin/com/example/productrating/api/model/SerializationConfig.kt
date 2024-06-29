package com.example.productrating.api.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val json = Json {
    serializersModule = SerializersModule {
        polymorphic(ApiListResponse::class) {
            subclass(ApiListResponse.Loading::class)
            subclass(ApiListResponse.Success::class)
            subclass(ApiListResponse.Error::class)
        }
    }
    classDiscriminator = "type" // Polymorphic discriminator field name
}