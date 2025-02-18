package com.example.otakuverse.repository

import android.util.Log
import com.example.otakuverse.api.AnimeApiService
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail

class AnimeRepository(
    private val apiService: AnimeApiService
) {
    suspend fun getTopAnime(page : Int = 1): Result<List<Anime>> {
        return try {
            val response = apiService.getTopAnime(page)
            if (response.isSuccessful) {
                response.body()?.let { animelist ->
                    Result.success(animelist)
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
}