package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlternativeTitles(
    @SerialName("english") val english: String,
    @SerialName("japanese") val japanese: String,
    @SerialName("synonyms") val synonyms: String
)