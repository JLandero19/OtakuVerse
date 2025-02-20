package com.example.otakuverse.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otakuverse.otakuverserelease.OtakuverseReleaseApplication
import com.example.otakuverse.repository.AnimeRepository
import com.example.otakuverse.ui.screens.elementList.ElementListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (
    private val animeRepository: AnimeRepository,
    id: Int
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OtakuverseReleaseApplication)
                DetailViewModel(
                    application.animeRepository,
                    id
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(
        ElementListUiState()
    )

    val uiState: StateFlow<ElementListUiState> = _uiState

    init {
        viewModelScope.launch {
            animeRepository.getAnime(id).map { result ->
                _uiState.value = DetailUiState( anime = result )
            }
        }
    }
}