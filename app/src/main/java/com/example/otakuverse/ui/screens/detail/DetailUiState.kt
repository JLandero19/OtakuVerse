package com.example.otakuverse.ui.screens.detail

import com.example.otakuverse.datamodel.AnimeDetail
import com.example.otakuverse.ui.screens.elementList.UserMessages

data class DetailUiState(
    val anime: AnimeDetail? = null,
    var favorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class UserMessages {
    ERROR_LOADING_ANIMES,
}