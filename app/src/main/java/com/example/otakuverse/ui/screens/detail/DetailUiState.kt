package com.example.otakuverse.ui.screens.detail

import com.example.otakuverse.datamodel.AnimeDetail
import com.example.otakuverse.ui.screens.elementList.UserMessages

data class DetailUiState(
    val anime: AnimeDetail? = null,
    val userMessage: UserMessages? = null
)

enum class UserMessages {
    ERROR_LOADING_ANIMES,
}