package com.example.otakuverse.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName("myanimelist_url") val myanimelist_url: String,
    @SerialName("name") val name: String,
    @SerialName("picture_url") val picture_url: String,
    @SerialName("voice_actor_myanimelist_url") val voice_actor_myanimelist_url: String,
    @SerialName("voice_actor_name") val voice_actor_name: String,
    @SerialName("voice_actor_picture_url") val voice_actor_picture_url: String
)