package com.example.productrating.navigation


import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.productrating.pages.HomePage
import com.example.productrating.pages.colas.ColaListPage
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.navigation.Router
import org.jetbrains.compose.web.renderComposable


val LocalRouter = staticCompositionLocalOf<Router> { error("No Router provided") }

fun main() {
    val router = Router()
    router.register("/", autoPrefix = true) { HomePage() }
    router.register("/colas", autoPrefix = true) { ColaListPage() }

    renderComposable(rootElementId = "root") {
        CompositionLocalProvider(LocalRouter provides router) {
            App()
        }
    }
}