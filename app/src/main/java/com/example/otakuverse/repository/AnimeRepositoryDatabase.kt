package com.example.otakuverse.repository

import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.localdatabase.AnimeDAO
import kotlinx.coroutines.flow.Flow

class AnimeRepositoryDatabase(
    private val animeDAO: AnimeDAO
) : AnimeInterface {

    override suspend fun insertAnime(anime: Anime) = animeDAO.insertAnime(anime)

    override suspend fun deleteAnime(anime: Anime) = animeDAO.deleteAnime(anime)

    override suspend fun update(anime: Anime) = animeDAO.update(anime)

    override val getAllAnimes: Flow<List<Anime>> = animeDAO.getAllAnimes()
}