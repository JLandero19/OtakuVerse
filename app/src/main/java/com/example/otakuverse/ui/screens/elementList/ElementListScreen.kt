package com.example.otakuverse.ui.screens.elementList

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.otakuverse.R
import com.example.otakuverse.ui.components.AnimeCard
import com.example.otakuverse.ui.components.StandardAlertDialog
import com.example.otakuverse.utils.getWindowSizeClass

@SuppressLint("ContextCastToActivity")
@Composable
fun ElementListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    listAnimeVM: ElementListViewModel = viewModel(factory = ElementListViewModel.Factory),
) {
    val myAnime by remember { mutableStateOf("") }
    var openAlertDialog by remember { mutableStateOf(false) }
    val uiState by listAnimeVM.uiState.collectAsState()

    // Log para depurar la respuesta
    Log.d("TOPANIMES", "${uiState.topAnime}")

    // Dialog borrado de lista de favoritos.
    if (openAlertDialog) {
        StandardAlertDialog(
            dialogTitle = stringResource(R.string.delete_fav_anime_title),
            dialogText = stringResource(R.string.delete_fav_anime_text, myAnime),
            onConfirmation = {
//                openAlertDialog = false
//                listAnime.map {
//                    if (it.title == myAnime) {
//                        onFavClicked(it)
//                    } else {
//                        it // Deja el objeto sin cambios
//                    }
//                }.toMutableList()
            },
            onDismissRequest = { openAlertDialog = false }
        )
    }

    val windowSize = getWindowSizeClass(LocalContext.current as Activity)
    val columns = when (windowSize) {
        WindowWidthSizeClass.Compact -> 2
        else -> 4
    }

    when {
        uiState.isLoading -> {
            // Mostrar un indicador de progreso mientras se carga
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.userMessage != null -> {
            // Mostrar mensaje de error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${uiState.userMessage}", color = Color.Red)
            }
        }
        else -> {
            Column (modifier = modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns), // Esto asegura dos columnas
                    contentPadding = PaddingValues(8.dp) // Espaciado alrededor de la rejilla
                ) {
                    items(uiState.topAnime) { anime ->
                        AnimeCard(
                            anime,
                            favorite = uiState.favListAnime.contains(anime),
                            onClickCard = { navController.navigate("details/${anime.myanimelist_id}/${uiState.favListAnime.contains(anime)}") },
                            onClickFav = {
                                if (!uiState.favListAnime.contains(anime)) {
                                    listAnimeVM.saveAnime(anime)
                                } else {
                                    listAnimeVM.deleteAnime(anime)
                                }
                            },
                        )
                    }
                }
            }
        }
    }


}