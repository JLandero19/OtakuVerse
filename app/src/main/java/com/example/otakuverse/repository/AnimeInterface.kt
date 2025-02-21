package com.example.otakuverse.repository

import com.example.otakuverse.datamodel.AnimeModel
import kotlinx.coroutines.flow.Flow

interface AnimeInterface {
    suspend fun insertAnime(anime: AnimeModel)

    suspend fun deleteAnime(anime: AnimeModel)

    suspend fun update(anime: AnimeModel)

    val getAllAnimes: Flow<List<AnimeModel>>
}