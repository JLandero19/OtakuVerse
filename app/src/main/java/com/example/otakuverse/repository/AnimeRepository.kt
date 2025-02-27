package com.example.otakuverse.repository

import com.example.otakuverse.api.AnimeApiService
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail
import com.example.otakuverse.datamodel.Comment
import com.example.otakuverse.localdatabase.AnimeDAO
import com.example.otakuverse.localdatabase.CommentDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class AnimeRepository(
    private val apiService: AnimeApiService,
    private val animeDAO: AnimeDAO
) {
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
    suspend fun insertAnime(anime: Anime) = animeDAO.insertAnime(anime)

    suspend fun deleteAnime(anime: Anime) = animeDAO.deleteAnime(anime)

    suspend fun update(anime: Anime) = animeDAO.update(anime)

    val getAllAnimes: Flow<List<Anime>> = animeDAO.getAllAnimes()
}