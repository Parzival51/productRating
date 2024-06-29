package com.example.productrating.pages

import androidx.compose.runtime.*
import com.example.productrating.api.model.Product
import com.example.productrating.components.HeaderLayout
import com.example.productrating.data.addProduct
import com.example.productrating.navigation.Screen
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun HomePage() {

    val scope = rememberCoroutineScope()
    val context = rememberPageContext()
    var isFormVisible by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0.0) }
    var category by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    if (isFormVisible) {
        LaunchedEffect(Unit) {
            window.scrollTo(0.0, window.innerHeight.toDouble())
        }
    }

    HeaderLayout(context = context) {
        Column(
            modifier = Modifier.fillMaxSize().backgroundColor(Color.rgb(224, 224, 224))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.px),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SpanText(
                    text = "Ürünleri Değerlendir, Yorumları Oku, En İyi Seçimi Yap!",
                    modifier = Modifier
                        .fontSize(50.px)
                        .margin(top = 75.px)
                        .color(Colors.Black)
                        .fontFamily("Montserrat")
                        .fontWeight(FontWeight.Bold)
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier
                        .margin(top = 30.px)
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        attrs = Modifier
                            .height(75.px)
                            .width(300.px)
                            .onClick {
                                isFormVisible = true
                            }
                            .backgroundColor(Color.rgb(245, 245, 245))
                            .borderRadius(8.px)
                            .padding(16.px)
                            .cursor(Cursor.Pointer)
                            .toAttrs()
                    ) {
                        SpanText(
                            text = "Ürün Ekle",
                            modifier = Modifier.color(Colors.Black).fontSize(16.px)
                        )
                    }

                    Button(
                        attrs = Modifier
                            .height(75.px)
                            .width(300.px)
                            .margin(left = 50.px)
                            .onClick {
                                isFormVisible = !isFormVisible
                            }
                            .backgroundColor(Color.rgb(245, 245, 245))
                            .borderRadius(8.px)
                            .padding(16.px)
                            .cursor(Cursor.Pointer)
                            .toAttrs()
                    ) {
                        SpanText(
                            text = "Ürün Yıldızla",
                            modifier = Modifier.color(Colors.Black).fontSize(16.px)
                        )
                    }
                }
            }

            // Kategoriler
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.px)
                    .backgroundColor(Color.rgb(224, 224, 224))
            ) {
                val categories = listOf(
                    Pair("url_to_image1.jpg", "Kategori 1"),
                    Pair("url_to_image2.jpg", "Kategori 2"),
                    Pair("url_to_image3.jpg", "Kategori 3"),
                    Pair("url_to_image4.jpg", "Kategori 4"),
                    Pair("url_to_image5.jpg", "Kategori 5"),
                    Pair("url_to_image6.jpg", "Kategori 6"),
                    Pair("url_to_image7.jpg", "Kategori 7"),
                    Pair("url_to_image8.jpg", "Kategori 8"),
                    Pair("url_to_image9.jpg", "Kategori 9"),
                    Pair("url_to_image10.jpg", "Kategori 10")
                )

                for (i in categories.indices step 5) {
                    Row(
                        modifier = Modifier.fillMaxWidth().margin(bottom = 16.px),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        for (j in 0 until 5) {
                            if (i + j < categories.size) {
                                val (imageUrl, categoryName) = categories[i + j]
                                Box(
                                    modifier = Modifier
                                        .size(200.px)
                                        .backgroundColor(Color.rgb(245, 245, 245))
                                        .borderRadius(16.px)
                                        .padding(16.px)
                                        .onClick {
                                            println("$categoryName kategorisine tıklandı")
                                            when (categoryName) {
                                                "Kategori 1" -> {
                                                    context.router.navigateTo(Screen.ColaPage.route)
                                                    println("Kategori 1 için özel işlem yapıldı")
                                                }
                                                "Kategori 2" -> {
                                                    println("Kategori 2 için özel işlem yapıldı")
                                                }
                                                "Kategori 3" -> {
                                                    println("Kategori 3 için özel işlem yapıldı")
                                                }
                                                else -> {
                                                    println("Başka bir kategoriye tıklandı")
                                                }
                                            }
                                        }
                                        .cursor(Cursor.Pointer)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Image(
                                            src = imageUrl,
                                            alt = "$categoryName resmi",
                                            modifier = Modifier.size(150.px).borderRadius(16.px)
                                        )
                                        SpanText(
                                            text = categoryName,
                                            modifier = Modifier.margin(top = 8.px).color(Colors.Black).fontSize(16.px)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (isFormVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .backgroundColor(Color.rgba(0, 0, 0, 0.5f)) // Arka planı yarı saydam yapar
                        .display(DisplayStyle.Flex)
                        .justifyContent(JustifyContent.Center)
                        .alignItems(AlignItems.Center)
                        .position(Position.Fixed) // Ekranın ortasında konumlandırır
                        .top(0.px)
                        .left(0.px)
                ) {
                    Box(
                        modifier = Modifier
                            .width(400.px)
                            .backgroundColor(Colors.White)
                            .borderRadius(16.px)
                            .padding(16.px)
                            .onClick { } // Tıklamaların modalı kapatmaması için
                    ) {
                        Column {
                            // Ürün Adı
                            Input(
                                type = InputType.Text,
                                attrs = Modifier
                                    .width(320.px)
                                    .height(50.px)
                                    .color(Colors.Navy)
                                    .backgroundColor(Colors.White)
                                    .padding(12.px)
                                    .fontSize(18.px)
                                    .borderRadius(8.px)
                                    .margin(8.px)
                                    .border(1.px, LineStyle.Solid, Colors.Navy)
                                    .toAttrs {
                                        attr("placeholder", "Product Name")
                                        onInput {
                                            name = it.value
                                        }
                                    }
                            )

                            // Fiyat
                            Input(
                                type = InputType.Number,
                                attrs = Modifier
                                    .width(320.px)
                                    .height(50.px)
                                    .color(Colors.Navy)
                                    .backgroundColor(Colors.White)
                                    .padding(12.px)
                                    .fontSize(18.px)
                                    .borderRadius(8.px)
                                    .margin(8.px)
                                    .border(1.px, LineStyle.Solid, Colors.Navy)
                                    .toAttrs {
                                        attr("placeholder", "Price")
                                        onInput {
                                            price = it.value?.toDouble() ?: 0.0
                                        }
                                    }
                            )



                            // Kategori
                            Input(
                                type = InputType.Text,
                                attrs = Modifier
                                    .width(320.px)
                                    .height(50.px)
                                    .color(Colors.Navy)
                                    .backgroundColor(Colors.White)
                                    .padding(12.px)
                                    .fontSize(18.px)
                                    .borderRadius(8.px)
                                    .margin(8.px)
                                    .border(1.px, LineStyle.Solid, Colors.Navy)
                                    .toAttrs {
                                        attr("placeholder", "Category")
                                        onInput {
                                            category = it.value
                                        }
                                    }
                            )

                            // Resim URL
                            Input(
                                type = InputType.Text,
                                attrs = Modifier
                                    .width(320.px)
                                    .height(50.px)
                                    .color(Colors.Navy)
                                    .backgroundColor(Colors.White)
                                    .padding(12.px)
                                    .fontSize(18.px)
                                    .borderRadius(8.px)
                                    .margin(8.px)
                                    .border(1.px, LineStyle.Solid, Colors.Navy)
                                    .toAttrs {
                                        attr("placeholder", "Image URL")
                                        onInput {
                                            image = it.value
                                        }
                                    }
                            )
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .margin(top = 16.px),
                                horizontalArrangement = Arrangement.spacedBy(32.px) // Butonlar arasına 32px boşluk ekler
                            ) {
                                Button(
                                    attrs = {
                                            onClick {
                                                scope.launch {
                                                    addProduct(
                                                        Product(
                                                            id = "",
                                                            name = name,
                                                            price = price.toLong(),
                                                            category = category,
                                                            imageUrl = image

                                                        )
                                                    )
                                                    isFormVisible = false
                                                }
                                            }
                                        style {
                                            backgroundColor(Color.rgb(0, 122, 255))
                                            borderRadius(8.px)
                                            padding(16.px)
                                            cursor("pointer")
                                            color(Colors.White)
                                            width(150.px) // Buton genişliği ayarlandı
                                        }

                                    }
                                )
                                {
                                    SpanText(text = "Ekle", modifier = Modifier.fontSize(16.px))
                                }


                                Button(
                                    attrs = {
                                        onClick {
                                            isFormVisible = false
                                        }
                                        style {
                                            backgroundColor(Color.rgb(255, 0, 0))
                                            borderRadius(8.px)
                                            padding(16.px)
                                            cursor("pointer")
                                            color(Colors.White)
                                            width(150.px) // Buton genişliği ayarlandı
                                        }
                                    }
                                ) {
                                    SpanText(text = "İptal", modifier = Modifier.fontSize(16.px))
                                }
                            }
                            }
                        }

                        }

                    }
                }
            }
        }


