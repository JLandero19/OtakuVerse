package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName("name") val name: String,
    @SerialName("picture_url") val pictureUrl: String,
    @SerialName("myanimelist_url") val myAnimeListUrl: String,
    @SerialName("voice_actor_name") val voiceActorName: String,
    @SerialName("voice_actor_picture_url") val voiceActorPictureUrl: String,
    @SerialName("voice_actor_myanimelist_url") val voiceActorMyAnimeListUrl: String
)
