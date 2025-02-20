package com.example.otakuverse.repository

import com.example.otakuverse.datamodel.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeInterface {
    suspend fun insertAnime(anime: Anime)

    suspend fun deleteAnime(anime: Anime)

    suspend fun update(anime: Anime)

    val getAllAnimes: Flow<List<Anime>>
}