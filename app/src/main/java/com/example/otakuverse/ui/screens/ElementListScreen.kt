package com.example.otakuverse.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.otakuverse.model.Datasource
import com.example.otakuverse.ui.components.AnimeCard
import com.example.otakuverse.utils.getWindowSizeClass

@Composable
fun ElementListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    favorite: Boolean = false
) {
    val animes = if (!favorite) Datasource.getListXtimes(1) else Datasource.getAnimesFavorite()
    // Para controlar si es nulo
    val animeList = animes ?: emptyList()

    val windowSize = getWindowSizeClass(LocalContext.current as Activity)
    var columns: Int
    when (windowSize) {
        WindowWidthSizeClass.Compact -> { columns = 2 }
        else -> { columns = 4 }
    }

    Column (modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns), // Esto asegura dos columnas
            contentPadding = PaddingValues(8.dp) // Espaciado alrededor de la rejilla
        ) {
            items(animeList) { anime ->
                AnimeCard(anime, onClickCard = { navController.navigate("details/${anime.title}") })
            }
        }
    }
}