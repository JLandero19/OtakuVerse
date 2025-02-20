package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlternativeTitles(
    @SerialName("synonyms") val synonyms: String?,
    @SerialName("japanese") val japanese: String?,
    @SerialName("english") val english: String?
)