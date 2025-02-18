package com.example.otakuverse.ui.screens.profile

import com.example.otakuverse.data.ThemeMode

data class ProfileUiState(
    val username: String = "",
    val themeMode: Boolean = ThemeMode.LIGHT.mode,
    val isLoading: Boolean = true,
    val userMessage: UserMessage? = null
)

enum class UserMessage {
    ERROR_ACCESSING_DATASTORE,
    ERROR_WRITING_DATASTORE,
}