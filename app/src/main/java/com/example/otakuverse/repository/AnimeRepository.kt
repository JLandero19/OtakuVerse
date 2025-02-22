package com.example.otakuverse.repository

import com.example.otakuverse.api.AnimeApiService
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail
import com.example.otakuverse.datamodel.AnimeModel
import com.example.otakuverse.localdatabase.AnimeDAO
import kotlinx.coroutines.flow.Flow

class AnimeRepository(
    private val apiService: AnimeApiService,
    private val animeDAO: AnimeDAO
) : AnimeInterface {
    suspend fun getTopAnime(page : Int = 1): Result<List<Anime>> {
        return try {
            val response = apiService.getTopAnime(p = page)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("El cuerpo de la respuesta es nulo"))

            } else {
                Result.failure(Exception("Error en la consulta: Código ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAnime(id : Int): Result<AnimeDetail> {
        return try {
            val response = apiService.getAnimeById(id)
            if (response.isSuccessful) {
                response.body()?.let { anime ->
                    Result.success(anime)
                } ?: Result.failure(Exception("El cuerpo de la respuesta es nulo"))
            } else {
                Result.failure(Exception("Error en la consulta: Código ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Métodos sacados de la interfaces
    override suspend fun insertAnime(anime: AnimeModel) = animeDAO.insertAnime(anime)

    override suspend fun deleteAnime(anime: AnimeModel) = animeDAO.deleteAnime(anime)

    override suspend fun update(anime: AnimeModel) = animeDAO.update(anime)

    override val getAllAnimes: Flow<List<AnimeModel>> = animeDAO.getAllAnimes()
}