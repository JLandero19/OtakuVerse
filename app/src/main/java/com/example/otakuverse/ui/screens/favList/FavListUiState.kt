package com.example.otakuverse.ui.screens.favList

import com.example.otakuverse.datamodel.Anime

data class FavListUiState(
    val favListAnime: MutableList<Anime> = mutableListOf(),
    val isLoading: Boolean = true,
    val userMessage: String? = null
)