package com.example.productrating.api.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
data class UserState(
    var username: String? = null
)

