package com.example.otakuverse.ui.screens.elementList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeModel
import com.example.otakuverse.otakuverserelease.OtakuverseReleaseApplication
import com.example.otakuverse.repository.AnimeRepository
import com.example.otakuverse.repository.AnimeRepositoryDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ElementListViewModel (
    private val animeRepository: AnimeRepository,
    private val animeRepositoryDatabase: AnimeRepositoryDatabase
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OtakuverseReleaseApplication)
                ElementListViewModel(
                    application.animeRepository,
                    application.animeRepositoryDatabase
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(
        ElementListUiState()
    )

    val uiState: StateFlow<ElementListUiState> = _uiState

    init {
        topAnime()
    }

    private fun topAnime() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true) // Mostrar estado de carga
            try {
                animeRepository.getTopAnime().map { result ->
                    _uiState.value = ElementListUiState( topAnime =  result.toMutableList(), isLoading = false )
                }
            } catch (e: Exception) {
                _uiState.value = ElementListUiState(isLoading = false, userMessage = e.message)
            }
        }
    }

    fun saveAnime(anime: Anime) {
        val animeModel = AnimeModel(anime.myanimelist_id,anime.aired_on, anime.members, anime.myanimelist_url, anime.picture_url, anime.rank, anime.score, anime.title, anime.type)
        viewModelScope.launch {
            animeRepositoryDatabase.insertAnime(animeModel)
        }
    }
}