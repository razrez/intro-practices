package com.example.domain.common

import java.util.Date

@kotlinx.serialization.Serializable
data class TimeData(
    val abbreviation: String = "",
    val datetime: String = ""
)
