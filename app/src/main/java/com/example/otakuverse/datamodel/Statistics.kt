package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistics(
    @SerialName("favorites") val favorites: Int,
    @SerialName("members") val members: Int,
    @SerialName("popularity") val popularity: Int,
    @SerialName("ranked") val ranked: Int,
    @SerialName("score") val score: Double
)