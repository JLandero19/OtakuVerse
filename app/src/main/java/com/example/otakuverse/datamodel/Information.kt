package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Information(
//    @SerialName("type") val type: List<Type>,
    @SerialName("episodes") val episodes: String,
//    @SerialName("status") val status: String,
//    @SerialName("aired") val aired: String,
//    @SerialName("premiered") val premiered: List<Type>,
//    @SerialName("broadcast") val broadcast: String,
//    @SerialName("producers") val producers: List<Producer>,
//    @SerialName("licensors") val licensors: String,
//    @SerialName("studios") val studios: List<Studio>,
//    @SerialName("source") val source: String,
//    @SerialName("genre") val genre: String,
    @SerialName("duration") val duration: String,
    @SerialName("rating") val rating: String,
//    @SerialName("genres") val genres: List<Genre>,
//    @SerialName("demographic") val demographic: List<Demographic>
)