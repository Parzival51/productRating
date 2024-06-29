package com.example.productrating.pages.colas

import androidx.compose.runtime.*
import com.example.productrating.api.model.ApiListResponse
import com.example.productrating.components.HeaderLayout
import com.example.productrating.data.getAllColas
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.px
import org.w3c.dom.Text

@Page
@Composable
fun ColaListPage() {
    val context = rememberPageContext()
    val categoryName = "Cola" // Kategori adı sabit olarak 'Cola'
    var response by remember { mutableStateOf<ApiListResponse>(ApiListResponse.Loading) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            getAllColas(
                onSuccess = {
                    response = it
                },
                onError = {
                    response = ApiListResponse.Error(it.message ?: "An error occurred")
                }
            )
        }
    }

    HeaderLayout(context = context) {
        Column(
            modifier = Modifier.fillMaxSize().backgroundColor(Color.rgb(224, 224, 224))
        ) {
            SpanText(
                text = categoryName,
                modifier = Modifier
                    .fontSize(50.px)
                    .margin(top = 75.px, bottom = 30.px)
                    .color(Colors.Black)
                    .fontFamily("Montserrat")
                    .fontWeight(FontWeight.Bold)
                    .align(Alignment.CenterHorizontally)
            )

            when (response) {
                is ApiListResponse.Loading -> {
                    Text("Yükleniyor...")
                }
                is ApiListResponse.Success -> {
                    val products = (response as ApiListResponse.Success).data
                    if (products.isNotEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.px)
                                .backgroundColor(Color.rgb(224, 224, 224))
                        ) {
                            val rows = products.chunked(5) // Her satırda 5 ürün olacak şekilde böl

                            for (row in rows) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().margin(bottom = 16.px),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    for (product in row) {
                                        Box(
                                            modifier = Modifier
                                                .size(200.px)
                                                .backgroundColor(Color.rgb(245, 245, 245))
                                                .borderRadius(16.px)
                                                .padding(16.px)
                                                .onClick {
                                                    // Ürün tıklanınca yapılacak işlemler buraya
                                                    println("${product.name} ürününe tıklandı")
                                                }
                                                .cursor(Cursor.Pointer)
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxSize(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Image(
                                                    src = product.imageUrl,
                                                    alt = "${product.name} resmi",
                                                    modifier = Modifier.size(150.px).borderRadius(16.px)
                                                )
                                                SpanText(
                                                    text = product.name,
                                                    modifier = Modifier.margin(top = 8.px).color(Colors.Black).fontSize(16.px)
                                                )
                                            }
                                        }
                                    }
                                    // Eğer satırda 5'ten az ürün varsa, boş alanları doldur
                                    for (i in row.size until 5) {
                                        Box(modifier = Modifier.size(200.px))
                                    }
                                }
                            }
                        }
                    } else {
                        SpanText("Cola kategorisinde ürün bulunmamaktadır.", modifier = Modifier.align(Alignment.CenterHorizontally).margin(top = 16.px))
                    }
                }
                is ApiListResponse.Error -> {
                    SpanText("Ürünler yüklenirken bir hata oluştu", modifier = Modifier.align(Alignment.CenterHorizontally).margin(top = 16.px))
                }
            }
        }
    }
}


