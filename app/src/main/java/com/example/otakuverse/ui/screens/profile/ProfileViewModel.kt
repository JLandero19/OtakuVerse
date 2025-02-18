package com.example.otakuverse.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otakuverse.data.ThemeMode
import com.example.otakuverse.data.UserPreferences
import com.example.otakuverse.data.UserPreferencesRepository
import com.example.otakuverse.otakuverserelease.OtakuverseReleaseApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OtakuverseReleaseApplication)
                ProfileViewModel(application.userPreferencesRepository)
            }
        }
    }

    //Estado de la interfaz de usuario.
    private val _uiState = MutableStateFlow(ProfileUiState())

    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    //Bloque que se ejecuta con la creación del objeto.
    init {
        viewModelScope.launch {
            userPreferencesRepository.userPrefs
                .onStart {
                    _uiState.update { currentState ->
                        currentState.copy(isLoading = true)
                    }
                }
                .catch { e ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            userMessage = UserMessage.ERROR_ACCESSING_DATASTORE
                        )
                    }
                }
                .collect { preferences ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            username = preferences.username,
                            themeMode = preferences.themeMode,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun setSettings(username: String = "", themeMode: Boolean = ThemeMode.LIGHT.mode) {
        viewModelScope.launch {
            try {
                _uiState.update { currentState ->
                    currentState.copy(
                        username = username,
                        themeMode = themeMode
                    )
                }
                userPreferencesRepository.savePreferences(
                    UserPreferences(uiState.value.username, themeMode)
                )
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        userMessage = UserMessage.ERROR_WRITING_DATASTORE
                    )
                }
            }
        }
    }
}