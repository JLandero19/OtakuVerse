package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Information(
    @SerialName("aired") val aired: String,
    @SerialName("broadcast") val broadcast: String,
    @SerialName("demographic") val demographic: List<Demographic>,
    @SerialName("duration") val duration: String,
    @SerialName("episodes") val episodes: String,
    @SerialName("genre") val genre: String,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("licensors") val licensors: String,
    @SerialName("premiered") val premiered: List<Premiered>,
    @SerialName("producers") val producers: List<Producer>,
    @SerialName("rating") val rating: String,
    @SerialName("source") val source: String,
    @SerialName("status") val status: String,
    @SerialName("studios") val studios: List<Studio>,
    @SerialName("theme") val theme: String,
    @SerialName("type") val type: List<Type>
)