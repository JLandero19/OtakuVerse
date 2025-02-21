package com.example.otakuverse.repository

import com.example.otakuverse.datamodel.AnimeModel
import com.example.otakuverse.localdatabase.AnimeDAO
import kotlinx.coroutines.flow.Flow

class AnimeRepositoryDatabase(
    private val animeDAO: AnimeDAO
) : AnimeInterface {

    override suspend fun insertAnime(anime: AnimeModel) = animeDAO.insertAnime(anime)

    override suspend fun deleteAnime(anime: AnimeModel) = animeDAO.deleteAnime(anime)

    override suspend fun update(anime: AnimeModel) = animeDAO.update(anime)

    override val getAllAnimes: Flow<List<AnimeModel>> = animeDAO.getAllAnimes()
}