package com.example.productrating.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.init.initKobweb
import com.varabyte.kobweb.navigation.RoutePrefix
import com.varabyte.kobweb.navigation.Router
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable


val LocalRouter = staticCompositionLocalOf<Router> { error("No Router provided") }

@Composable
fun ErrorPage(message: String) {
    Text("Error: $message")
}

fun main() {
    RoutePrefix.set("")
    val router = Router()
    initKobweb(router) { ctx ->
        ctx.router.register("/") { com.example.productrating.pages.HomePage() }
        ctx.router.register("/product/{id}") { com.example.productrating.pages.product.ProductDetailPage() }
        ctx.router.register("/updateProduct") { com.example.productrating.pages.product.ProductUpdatePage() }
        ctx.router.register("/category/{category}") { com.example.productrating.pages.colas.CategoryPage() }
        ctx.router.register("/register") { com.example.productrating.pages.registerandlogin.RegisterPage() }
        ctx.router.register("/login") { com.example.productrating.pages.registerandlogin.LoginPage() }
        ctx.router.register("/search/{query}") { com.example.productrating.pages.product.SearchResultPage() }
        ctx.router.register("/user/reviews") { com.example.productrating.pages.product.UserReviewsPage() }
    }

    renderComposable(rootElementId = "root") {
        CompositionLocalProvider(LocalRouter provides router) {
            App()
        }
    }
}


