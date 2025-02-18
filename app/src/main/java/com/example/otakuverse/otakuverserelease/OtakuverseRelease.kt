package com.example.otakuverse.otakuverserelease

import com.example.otakuverse.api.AnimeApiConfig
import com.example.otakuverse.api.AnimeApiService
import com.example.otakuverse.repository.AnimeRepository

class OtakuverseRelease {
    val animeRepository: AnimeRepository by lazy {
        AnimeRepository(
            AnimeApiConfig.provideRetrofit(BuildConfig.BASE_URL + BuildConfig.ACCESS_TOKEN + "/")
                .create(AnimeApiService::class.java)
        )
    }
}