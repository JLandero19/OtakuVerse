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

        // private const val BASE_URL = "https://myanimelist.p.rapidapi.com"
        // private const val ACCESS_KEY = "27e07c91dbmshe3dd03433cf4e59p14d0a1jsn3848365a7a20"

        // Configura el objeto Json con algunas opciones (por ejemplo, ignorar claves desconocidas)
        private val json = Json {
            ignoreUnknownKeys = true
            // Puedes configurar otras opciones según lo necesites
        }

        // Proveedor de Retrofit con el cliente personalizado
        fun provideRetrofit(baseUrl: String, host: String, key: String): Retrofit {
            // Interceptor para agregar cabeceras a todas las solicitudes
            val headerInterceptor = Interceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader(HOST_HEADER, host)
                    .addHeader(KEY_HEADER, key)
                    .build()
                chain.proceed(request)
            }

            // Cliente HTTP con los interceptores
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build()


            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
        }
    }
}