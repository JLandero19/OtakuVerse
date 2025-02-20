package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Premiered(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)