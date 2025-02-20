package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetail(
    @SerialName("alternative_titles") val alternative_titles: AlternativeTitles,
    @SerialName("characters") val characters: List<Character>,
    @SerialName("information") val information: Information,
    @SerialName("picture_url") val picture_url: String,
    @SerialName("statistics") val statistics: Statistics,
    @SerialName("synopsis") val synopsis: String,
    @SerialName("title_en") val title_en: String,
    @SerialName("title_ov") val title_ov: String
)