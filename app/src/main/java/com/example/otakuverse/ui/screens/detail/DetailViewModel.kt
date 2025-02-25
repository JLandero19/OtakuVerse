package com.example.otakuverse.ui.screens.detail

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
import com.example.otakuverse.ui.screens.elementList.ElementListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel (
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OtakuverseReleaseApplication)
                DetailViewModel(
                    application.animeRepository,
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(
        DetailUiState()
    )

    val uiState: StateFlow<DetailUiState> = _uiState

    fun animeDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true) // Mostrar estado de carga
            try {
                animeRepository.getAnime(id).map { result ->
                    _uiState.value = DetailUiState( anime = result, isLoading = false )
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState(isLoading = false, errorMessage = e.message)
            }
        }
    }
}