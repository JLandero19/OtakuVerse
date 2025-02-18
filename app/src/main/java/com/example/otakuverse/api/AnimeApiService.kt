package com.example.otakuverse.api

import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AnimeApiService {
    @Headers(
        "X-RapidAPI-Host: myanimelist.p.rapidapi.com",
        "X-RapidAPI-Key: 27e07c91dbmshe3dd03433cf4e59p14d0a1jsn3848365a7a20"
    )

    @GET("/anime/top/all?p={page}")
    suspend fun getTopAnime(@Path("page") page: Int = 1): Response<List<Anime>>

    @GET("/anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): Response<AnimeDetail>
}