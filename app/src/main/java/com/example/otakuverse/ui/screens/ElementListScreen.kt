package com.example.otakuverse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.otakuverse.R
import com.example.otakuverse.model.Datasource
import com.example.otakuverse.ui.components.AnimeCard
import com.example.otakuverse.ui.components.CenterAlignedTopAppBar
import com.example.otakuverse.ui.components.Greeting

@Composable
fun ElementListScreen(
    modifier: Modifier = Modifier
) {
    val animes = Datasource.getListXtimes(1)

    Scaffold(
        topBar = { CenterAlignedTopAppBar(text = stringResource(R.string.anime_list)) },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column (
            modifier.padding(innerPadding).fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Esto asegura dos columnas
                contentPadding = PaddingValues(8.dp) // Espaciado alrededor de la rejilla
            ) {
                items(animes) { anime ->
//                    when (windowSize) {
//                        WindowWidthSizeClass.Compact -> { HeroCard(hero) }
//                        else -> { HeroCardMedExp(hero) }
//                    }
                    AnimeCard(anime)
                }
            }
        }

    }
}