package com.example.otakuverse.otakuverserelease

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.otakuverse.data.UserPreferences
import com.example.otakuverse.data.UserPreferencesRepository
import com.example.otakuverse.api.AnimeApiConfig
import com.example.otakuverse.api.AnimeApiService
import com.example.otakuverse.localdatabase.OtakuverseDatabase
import com.example.otakuverse.repository.AnimeRepository
import com.example.otakuverse.repository.AnimeRepositoryDatabase

// Datastore. Configuración básica de la app.
val Context.dataStore by preferencesDataStore(name = UserPreferences.SETTINGS_FILE)

class OtakuverseReleaseApplication: Application() {

    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var animeRepositoryDatabase: AnimeRepositoryDatabase

    // Contenedor de dependencias manuales que se usa por completo en la app
    override fun onCreate() {
        super.onCreate()
        // Creación de la instancia del repositorio de preferencias de usuario
        userPreferencesRepository = UserPreferencesRepository(dataStore)

        animeRepositoryDatabase = AnimeRepositoryDatabase(OtakuverseDatabase.getDatabase(this).animesDAO())
    }

    val animeRepository: AnimeRepository by lazy {
        AnimeRepository(AnimeApiConfig.provideRetrofit().create(AnimeApiService::class.java))
    }
}