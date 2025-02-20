package com.example.otakuverse.ui.screens.elementList

import com.example.otakuverse.datamodel.Anime

data class ElementListUiState(
    val topAnime: MutableList<Anime> = mutableListOf(),
    val isLoading: Boolean = true,
    val userMessage: String? = null
)

enum class UserMessages {
    ERROR_LOADING_TOP_ANIMES,
}