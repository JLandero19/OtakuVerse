package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistics(
    @SerialName("score") val score: Double,
    @SerialName("ranked") val ranked: Int,
//    @SerialName("popularity") val popularity: Int,
//    @SerialName("members") val members: Int,
//    @SerialName("favorites") val favorites: Int
)