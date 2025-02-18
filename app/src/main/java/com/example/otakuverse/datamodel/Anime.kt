package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Anime (
    @SerialName("myanimelist_id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("picture_url") val imageUrl: String,
    @SerialName("myanimelist_url") val animeUrl: String,
    @SerialName("rank") val rank: Int,
    @SerialName("score") val score: Double,
    @SerialName("type") val type: String,
    @SerialName("aired_on") val airedOn: String,
    @SerialName("members") val members: Int
)

@Serializable
data class AnimeDetail (
    @SerialName("title_ov") val titleOv: String,
    @SerialName("title_en") val titleEn: String,
    @SerialName("sinopsis") val sinopsis: String,
    @SerialName("alternative_titles") val alternativeTitles: AlternativeTitle,
    @SerialName("information") val information: Information,
    @SerialName("statistics") val statistics: Statistics,
    @SerialName("characters") val characters: Characters,
)

@Serializable
data class AlternativeTitle (
    @SerialName("synonyms") val synonyms: String,
    @SerialName("japanese") val japanese: String,
    @SerialName("english") val english: String
)

@Serializable
data class Information (
    @SerialName("type") val type: Type,
    @SerialName("episodes") val episodes: Int,
    @SerialName("status") val status: String,
    @SerialName("aired") val aired: String,
    @SerialName("premiered") val premiered: Premiered,
    @SerialName("broadcast") val broadcast: String,
    @SerialName("producers") val producers: Producers,
    @SerialName("licensors") val licensors: String,
    @SerialName("studios") val studios: Studios,
    @SerialName("source") val source: String,
    @SerialName("genre") val genre: String,
    @SerialName("theme") val theme: String,
    @SerialName("duration") val duration: String,
    @SerialName("rating") val rating: String,
    @SerialName("genres") val genres: Genres,
    @SerialName("demographic") val demographic: Demographic,
    @SerialName("picture_url") val pictureUrl: String,
)

@Serializable
data class Type (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Premiered (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Producers (
    @SerialName("producers") val name: List<Producer>,
)

@Serializable
data class Producer (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Studios (
    @SerialName("producers") val name: List<Studio>,
)

@Serializable
data class Studio (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Genres (
    @SerialName("genre") val name: List<Genre>,
)

@Serializable
data class Genre (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Demographic (
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Statistics (
    @SerialName("score") val score: Double,
    @SerialName("ranked") val ranked: Int,
    @SerialName("popularity") val popularity: Int,
    @SerialName("members") val members: Long,
    @SerialName("favorites") val favorites: Long,
)

@Serializable
data class Characters (
    @SerialName("characters") val score: List<Character>,
)

@Serializable
data class Character (
    @SerialName("name") val name: String,
    @SerialName("picture_url") val pictureUrl: String,
    @SerialName("myanimelist_url") val animeUrl: String,
    @SerialName("voice_actor_name") val voiceActorName: String,
    @SerialName("voice_actor_picture_url") val voiceActorImageUrl: String,
    @SerialName("voice_actor_myanimelist_url") val voiceActorUrl: String,
)