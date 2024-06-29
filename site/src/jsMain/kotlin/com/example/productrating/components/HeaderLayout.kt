package com.example.productrating.components

import androidx.compose.runtime.Composable
import com.example.productrating.navigation.Screen
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*

@Composable
fun HeaderLayout(
    context: PageContext,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .backgroundColor(Colors.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.px)
                .padding(30.px)
                .backgroundColor(Colors.White)
                .styleModifier {
                    property("position", "sticky")
                    property("top", "0")
                    property("z-index", "1000")
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.px),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(16.px)
                ) {
                    SpanText(
                        text = "tadıYorum",
                        modifier = Modifier
                            .color(Colors.Black)
                            .fontSize(36.px)
                            .margin(bottom = 4.px, left = 32.px, top = 4.px) // Add some margin below the new text
                            .fontFamily("Montserrat")
                            .fontWeight(FontWeight.Bold)

                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.padding(16.px)
                    ) {
                        SpanText(
                            text = "Home Page",
                            modifier = Modifier
                                .color(Colors.Black)
                                .cursor(Cursor.Pointer)
                                .margin(topBottom = 0.px, leftRight = 16.px) // Adjust margin for better spacing
                                .fontSize(20.px)
                                .onClick {
                                    context.router.navigateTo(Screen.HomePage.route)
                                }
                        )
                        SpanText(
                            text = "Product List",
                            modifier = Modifier
                                .color(Colors.Black)
                                .cursor(Cursor.Pointer)
                                .margin(topBottom = 0.px, leftRight = 16.px) // Adjust margin for better spacing
                                .fontSize(20.px)
                                .onClick {
                                    context.router.navigateTo(Screen.ProductListPage.route)
                                }
                        )
                        SpanText(
                            text = "Food List",
                            modifier = Modifier
                                .color(Colors.Black)
                                .cursor(Cursor.Pointer)
                                .margin(topBottom = 0.px, leftRight = 16.px) // Adjust margin for better spacing
                                .fontSize(20.px)
                                .onClick {
                                    context.router.navigateTo(Screen.FoodPage.route)
                                }
                        )
                        SpanText(
                            text = "Drink List",
                            modifier = Modifier
                                .color(Colors.Black)
                                .cursor(Cursor.Pointer)
                                .margin(topBottom = 0.px, leftRight = 16.px) // Adjust margin for better spacing
                                .fontSize(20.px)
                                .onClick {
                                    context.router.navigateTo(Screen.DrinkPage.route)
                                }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .backgroundColor(Colors.White)
                        .padding(8.px)
                        .borderRadius(20.px)
                        .width(250.px)
                        .height(40.px),
                    contentAlignment = Alignment.Center
                ) {
                    SpanText(
                        text = "🔍 Ara",
                        modifier = Modifier
                            .color(Colors.Black)
                            .fontSize(18.px)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.padding(16.px)
                ) {
                    SpanText(
                        text = "Login",
                        modifier = Modifier
                            .color(Colors.Black)
                            .cursor(Cursor.Pointer)
                            .margin(topBottom = 0.px, leftRight = 16.px) // Adjust margin for better spacing
                            .fontSize(20.px)
                            .padding(8.px)
                            .onClick {
                                // Define the login functionality here
                            }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.px)
                .margin(top = 100.px),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

