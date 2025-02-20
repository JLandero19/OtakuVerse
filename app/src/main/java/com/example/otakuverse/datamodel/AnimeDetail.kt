package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetail(
    @SerialName("title_ov") val titleOv: String,
    @SerialName("title_en") val titleEn: String,
    @SerialName("synopsis") val synopsis: String,
//    @SerialName("alternative_titles") val alternativeTitles: AlternativeTitles,
    @SerialName("information") val information: Information,
    @SerialName("statistics") val statistics: Statistics,
//    @SerialName("characters") val characters: List<Character>,
    @SerialName("picture_url") val pictureUrl: String
)
