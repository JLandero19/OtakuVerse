package com.example.otakuverse.ui.screens.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.otakuverse.R
import com.example.otakuverse.data.UserPreferencesRepository
import com.example.otakuverse.datamodel.AnimeDetail
import com.example.otakuverse.datamodel.Comment
import com.example.otakuverse.ui.components.AnimeDetailCard
import com.example.otakuverse.ui.components.CommentStandard
import com.example.otakuverse.ui.components.StandardAlertDialog
import com.example.otakuverse.ui.components.StandardDialogForm
import com.example.otakuverse.utils.getWindowSizeClass

@SuppressLint("ContextCastToActivity")
@Composable
fun DetailScreen(
    id: Int,
    session: String,
    favorite: Boolean = false,
    modifier: Modifier,
//    navController: NavHostController,
    animeVM: DetailViewModel = viewModel(factory = DetailViewModel.Factory),
) {
    val uiState by animeVM.uiState.collectAsState()
    var addCommentForm by remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf("") }
    LaunchedEffect(id) {
        animeVM.animeDetail(id)
    }

    if (addCommentForm) {
        StandardDialogForm(
            dialogTitle = stringResource(R.string.add_comment),
            dialogText = stringResource(R.string.comment_label),
            textState = textState,
            onConfirmation = {
                val comment = Comment(
                    comment = textState.value,
                    user = session,
                    anime_id = id
                )
                animeVM.addComment(comment)
                addCommentForm = false
            },
            onDismiss = {
                addCommentForm = false
            },
            onDismissRequest = {
                addCommentForm = false
            }
        )
    }

    // Verifica en los logs si el estado cambia
    Log.d("DetailScreen", "UI State: $uiState")
    Log.d("DetailScreen", "id: $id")

    val windowSize = getWindowSizeClass(LocalContext.current as Activity)
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                // Mostrar un indicador de progreso mientras se carga
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.errorMessage != null -> {
                // Mostrar mensaje de error
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${uiState.errorMessage}", color = Color.Red)
                }
            }
            else -> {
                // Mostrar los detalles del anime
                val anime = uiState.anime
                if (anime != null) {
                    LazyColumn(modifier = modifier.fillMaxSize()) {
                        item {
                            when (windowSize) {
                                WindowWidthSizeClass.Compact -> CompactDetailScreen(anime, uiState.comment, favorite)
                                else -> MedExpDetailScreen(anime, uiState.comment, favorite)
                            }
                        }
                    }
                }
            }
        }
        // Botón flotante en la parte inferior derecha
        if (session != "") {
            FloatingActionButton(
                onClick = {
                    addCommentForm = true
                },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp)  // Agrega un pequeño margen para que no esté pegado a la esquina
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, // Aquí puedes usar cualquier ícono de la biblioteca de íconos de Jetpack Compose
                    contentDescription = stringResource(R.string.add_comment),
                    tint = Color.White
                )
            }
        }

    }

}

@Composable
fun CompactDetailScreen(
    anime: AnimeDetail,
    comments: MutableList<Comment>,
    favorite: Boolean = false,
//    navController: NavHostController,
    onFavClick: () -> Unit = {}
) {
    var openAlertDialog by remember { mutableStateOf(false) }

    // Dialog borrado de lista de favoritos.
    if (openAlertDialog) {
        StandardAlertDialog(
            dialogTitle = stringResource(R.string.delete_fav_anime_title),
            dialogText = stringResource(R.string.delete_fav_anime_text, anime.titleEn),
            onConfirmation = {
                openAlertDialog = false
                //anime.favorite = !anime.favorite
            },
            onDismissRequest = { openAlertDialog = false }
        )
    }

    Row(modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        // Queda programar el onClicCard
        AnimeDetailCard(
            anime,
            modifier = Modifier.width(200.dp).height(300.dp),
            favorite = favorite,
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.anime_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 1.dp)
            )
            Text(
                text = anime.titleEn,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.ranking),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 1.dp)
            )
            Text(
                text = anime.statistics.ranked.toString(),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.score),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = anime.statistics.score.toString(),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.num_episodes),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Text(
                text = if (anime.information.episodes == "null") "..." else anime.information.episodes,
                fontSize = 18.sp
            )
        }
    }
    Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = anime.synopsis,
                fontSize = 18.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    // Aquí también usé fillMaxHeight() para LazyColumn para evitar restricciones infinitas
    LazyColumn(
        modifier = Modifier.height(500.dp).padding(8.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.comment_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            if (comments.isEmpty()) {
                Text(
                    text = stringResource(R.string.not_found_comments),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        items(comments) { comment ->
            CommentStandard(userName = comment.user, comment = comment.comment)
        }
    }
}

@Composable
fun MedExpDetailScreen(
    anime: AnimeDetail,
    comments: MutableList<Comment>,
    favorite: Boolean = false,
    onFavClick: () -> Unit = {}
) {
    var openAlertDialog by remember { mutableStateOf(false) }

    // Dialog borrado de lista de favoritos.
    if (openAlertDialog) {
        StandardAlertDialog(
            dialogTitle = stringResource(R.string.delete_fav_anime_title),
            dialogText = stringResource(R.string.delete_fav_anime_text, anime.titleEn),
            onConfirmation = {
                openAlertDialog = false
            },
            onDismissRequest = { openAlertDialog = false }
        )
    }

    Row(modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        // Column conteniendo información de anime
        Column(modifier = Modifier.weight(1f)) {
            Row {
                AnimeDetailCard(
                    anime,
                    modifier = Modifier.width(200.dp).height(300.dp),
                    favorite = favorite,
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(R.string.anime_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Text(
                        text = anime.titleEn,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.ranking),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Text(
                        text = anime.statistics.ranked.toString(),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.score),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.statistics.score.toString(),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.num_episodes),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    )
                    Text(
                        text = if (anime.information.episodes == "null") "..." else anime.information.episodes,
                        fontSize = 18.sp
                    )
                }
            }

            Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = anime.synopsis,
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // LazyColumn para comentarios
        LazyColumn(
            modifier = Modifier.height(500.dp).padding(8.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.comment_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                if (comments.isEmpty()) {
                    Text(
                        text = stringResource(R.string.not_found_comments),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            items(comments) { comment ->
                CommentStandard(userName = comment.user, comment = comment.comment)
            }
        }
    }
}
