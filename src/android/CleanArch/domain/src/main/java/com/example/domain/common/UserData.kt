package com.example.domain.common

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int = 0,
    val title: String = "",
    val name: String = ""
)
