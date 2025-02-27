package com.example.otakuverse.ui.screens.detail

import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail
import com.example.otakuverse.datamodel.Comment
import com.example.otakuverse.ui.screens.elementList.UserMessages

data class DetailUiState(
    val anime: AnimeDetail? = null,
    val comment: MutableList<Comment> = mutableListOf(),
    var favorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class UserMessages {
    ERROR_LOADING_ANIMES,
}