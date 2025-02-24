package com.example.otakuverse.api

import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {
    @GET("anime/top/{category}")
    suspend fun getTopAnime(@Path("category") category: String = "all", @Query("p") p: Int = 1): Response<List<Anime>>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): Response<AnimeDetail>
}