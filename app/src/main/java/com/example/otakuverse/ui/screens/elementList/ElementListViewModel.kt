package com.example.otakuverse.ui.screens.elementList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.otakuverserelease.OtakuverseReleaseApplication
import com.example.otakuverse.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ElementListViewModel (
    private val animeRepository: AnimeRepository
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OtakuverseReleaseApplication)
                ElementListViewModel(
                    application.animeRepository
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(
        ElementListUiState()
    )

    val uiState: StateFlow<ElementListUiState> = _uiState.asStateFlow()

    init {
        topAnime()
    }

    private fun topAnime() {
        loadFavListAnime()
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true) // Mostrar estado de carga
            try {
                animeRepository.getTopAnime().map { result ->
                    _uiState.value = ElementListUiState( topAnime = result.shuffled().toMutableList(), isLoading = false )
                }
            } catch (e: Exception) {
                _uiState.value = ElementListUiState(isLoading = false, userMessage = e.message)
            }
        }
    }

    private fun loadFavListAnime() {
        viewModelScope.launch {
            animeRepository.getAllAnimes.collect { favList ->
                _uiState.update {
                    it.copy(favListAnime = favList.toMutableList())
                }
            }
        }
    }

    fun saveAnime(anime: Anime) {
        viewModelScope.launch {
            try {
                animeRepository.insertAnime(anime)
                _uiState.update { currentState ->
                    val updatedFavList = currentState.favListAnime.toMutableList()  // Convertir la lista inmutable a mutable
                    updatedFavList.add(anime)  // Agregar el nuevo anime
                    currentState.copy(
                        favListAnime = updatedFavList  // Usamos el operador + para agregar el elemento
                    )
                }
            } catch (e: Exception) {
                // Aquí puedes manejar cualquier error de inserción o mostrar un mensaje al usuario
                Log.d("SaveAnime", "Error al guardar anime: ${e.message}")
            }
        }
    }

    fun deleteAnime(anime: Anime) {
        viewModelScope.launch {
            try {
                animeRepository.deleteAnime(anime)
                _uiState.update { currentState ->
                    val updatedFavList = currentState.favListAnime.toMutableList()  // Convertir la lista inmutable a mutable
                    updatedFavList.remove(anime)  // Agregar el nuevo anime
                    currentState.copy(
                        favListAnime = updatedFavList  // Usamos el operador + para agregar el elemento
                    )
                }
            } catch (e: Exception) {
                // Aquí puedes manejar cualquier error de inserción o mostrar un mensaje al usuario
                Log.d("SaveAnime", "Error al eliminar anime: ${e.message}")
            }
        }
    }
}