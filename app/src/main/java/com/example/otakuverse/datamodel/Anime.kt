package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    @SerialName("aired_on") val aired_on: String,
    @SerialName("members") val members: Int,
    @SerialName("myanimelist_id") val myanimelist_id: Int,
    @SerialName("myanimelist_url") val myanimelist_url: String,
    @SerialName("picture_url") val picture_url: String,
    @SerialName("rank") val rank: Int,
    @SerialName("score") val score: Double,
    @SerialName("title") val title: String,
    @SerialName("type") val type: String
)