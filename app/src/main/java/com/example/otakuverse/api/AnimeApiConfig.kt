package com.example.otakuverse.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit

class AnimeApiConfig {
    companion object {
        private val contentType = "application/json".toMediaType()

        private const val HOST_HEADER = "x-rapidapi-host"
        private const val KEY_HEADER = "x-rapidapi-key"

        private const val BASE_URL = "https://myanimelist.p.rapidapi.com/"
        private const val HOST = "myanimelist.p.rapidapi.com"
//        private const val ACCESS_KEY = "27e07c91dbmshe3dd03433cf4e59p14d0a1jsn3848365a7a20"
        private const val ACCESS_KEY = "de194dc7e4msh560ff4a3ab8c4b2p1b8a0cjsn0995d47e813b"
//        private const val ACCESS_KEY = "199ec9a7e6msh87c61419e175409p157b55jsn63cebcb56aa9"

        // Configura el objeto Json con opciones para Kotlinx.serialization
        private val json = Json {
            ignoreUnknownKeys = true
        }

        // Configura y proporciona Retrofit con un cliente HTTP personalizado
        fun provideRetrofit(
            baseUrl: String = BASE_URL,
            host: String = HOST,
            key: String = ACCESS_KEY
        ): Retrofit {
            // Interceptor para agregar cabeceras a todas las solicitudes
            val headerInterceptor = Interceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .addHeader(KEY_HEADER, key)
                    .addHeader(HOST_HEADER, host)
                    .build()
                chain.proceed(request)
            }

            // Configura el cliente HTTP con el interceptor
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor { chain -> // Manejo básico de errores
                    val response = chain.proceed(chain.request())
                    if (!response.isSuccessful) {
                        // Puedes manejar casos como 401, 403 o 500 aquí
                        throw Exception("HTTP Error: ${response.code}")
                    }
                    response
                }
                .build()

            // Retorna el objeto Retrofit
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
        }
    }
}