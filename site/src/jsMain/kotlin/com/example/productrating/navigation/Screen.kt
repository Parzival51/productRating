package com.example.productrating.navigation

sealed class Screen(val route: String) {
    object HomePage : Screen(route = "/")
    object ProductListPage : Screen(route = "/products")
    object FoodPage : Screen(route = "/foods")
    object DrinkPage : Screen(route = "/drinks")
    object ColaPage: Screen(route =  "/colas")
}