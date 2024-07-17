package com.example.productrating.api.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val username: String,
    val password: String
)

