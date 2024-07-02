@file:PackageMapping("/product/{productId}")

package com.example.productrating.pages

import androidx.compose.runtime.*
import com.example.productrating.api.model.ApiListResponse
import com.example.productrating.api.model.Product
import com.example.productrating.components.HeaderLayout
import com.example.productrating.data.getProductById
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.PackageMapping
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.dom.Text

@Page("/product/{productId}")
@Composable
fun ProductDetailPage() {
    val context = rememberPageContext()
    val productId = context.route.params["productId"] ?: ""
    var product by remember { mutableStateOf<Product?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(productId) {
        if (productId.isNotEmpty()) {
            getProductById(
                productId = productId,
                onSuccess = {
                    when (it) {
                        is ApiListResponse.Success -> {
                            product = it.data.firstOrNull()
                        }
                        is ApiListResponse.Error -> {
                            error = it.message
                        }
                        else -> {
                            error = "Unexpected response"
                        }
                    }
                },
                onError = {
                    error = it.message
                }
            )
        } else {
            error = "Product ID is required"
        }
    }

    HeaderLayout(context = context) {
        if (product == null && error == null) {
            Text("Yükleniyor...")
        } else if (error != null) {
            Text("Hata: $error")
        } else if (product != null) {
            Column {
                Text("Ürün Adı: ${product!!.name}")
                Text("Fiyat: ${product!!.price}")
                // Diğer ürün detayları
            }
        }
    }
}