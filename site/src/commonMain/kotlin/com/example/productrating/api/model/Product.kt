package com.example.productrating.api.model

import kotlinx.serialization.Serializable


@Serializable
data class Product (
    val id: String = "",
    val name: String = "",
    val price: Long = 0L,
    val rating: Long = 0L,
    val comments: List<String> = emptyList(),
    val category: String = "",
    val imageUrl: String = ""
)
