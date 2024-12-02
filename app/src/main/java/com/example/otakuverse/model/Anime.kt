package com.example.otakuverse.model

data class Anime(
    val title: String,
    // Imagen de cartelera
    val image_url: String,
    val ranked: Int,
    val score: Float,
    val number_episodes: String?,
    val aired_on: String,
    val favorite: Boolean = false
)