package com.example.otakuverse.model

data class Anime(
    val title: String,
    val description: String,
    val genre: List<String>,

    // Imagen de cartelera
    val image_url: String,

    // Número de episodios
    val number_episodes: Int?,
    val score: Float,
    val ranked: Int

)