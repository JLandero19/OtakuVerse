package com.example.otakuverse.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuverse.R
import com.example.otakuverse.model.Anime
import com.example.otakuverse.ui.components.AnimeCard
import com.example.otakuverse.ui.components.CommentStandard
import com.example.otakuverse.ui.components.StandardAlertDialog
import com.example.otakuverse.utils.getWindowSizeClass

@Composable
fun DetailScreen(
    anime: Anime,
    modifier: Modifier,
//    navController: NavHostController,
    onFavClicked: (Anime) -> Unit = {}
) {
    val windowSize = getWindowSizeClass(LocalContext.current as Activity)
    LazyColumn(modifier = modifier) {
        item {
            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
                    CompactDetailScreen(
                        anime,
//                        navController,
                        onFavClick = onFavClicked)
                }
                else -> {
                    MedExpDetailScreen(
                        anime,
//                        navController,
                        onFavClick = onFavClicked
                    )
                }

            }
        }
    }
}

@Composable
fun CompactDetailScreen(
    anime: Anime,
//    navController: NavHostController,
    onFavClick: (Anime) -> Unit = {}
) {
    var openAlertDialog by remember { mutableStateOf(false) }

    // Dialog borrado de lista de favoritos.
    if (openAlertDialog) {
        StandardAlertDialog(
            dialogTitle = stringResource(R.string.delete_fav_anime_title),
            dialogText = stringResource(R.string.delete_fav_anime_text, anime.title),
            onConfirmation = {
                openAlertDialog = false
                anime.favorite = !anime.favorite
            },
            onDismissRequest = { openAlertDialog = false }
        )
    }

    Row (modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        // Queda programar el onClicCard
        AnimeCard(
            anime,
            modifier = Modifier.width(200.dp).height(300.dp),
            onClickFav = {
                if (!anime.favorite) {
                    onFavClick(anime)
                } else {
                    // Solo lo quiero utilizar para eliminar
                    openAlertDialog = true
                }
            }
        )
        Column (modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.anime_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 1.dp)
            )
            Text(
                text = anime.title,
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
                text = anime.ranked.toString(),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.score),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = anime.score.toString(),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.num_episodes),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Text(
                text = if (anime.number_episodes.toString() == "null") "..." else anime.number_episodes.toString(),
                fontSize = 18.sp
            )
        }
    }
    Row (modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
        Column (modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = anime.description,
                fontSize = 18.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row (modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
        Column (modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.comment_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            CommentStandard(userName = "Javier Landero", comment = "Este anime es increible, mucha historia")
            CommentStandard(userName = "Carlos Méndez", comment = "Me encanta este anime, los personajes son geniales")
            CommentStandard(userName = "Ana López", comment = "¡Totalmente de acuerdo! La trama es muy profunda")
            CommentStandard()
        }
    }
}

@Composable
fun MedExpDetailScreen(
    anime: Anime,
//    navController: NavHostController,
    onFavClick: (Anime) -> Unit = {}
) {
    var openAlertDialog by remember { mutableStateOf(false) }

    // Dialog borrado de lista de favoritos.
    if (openAlertDialog) {
        StandardAlertDialog(
            dialogTitle = stringResource(R.string.delete_fav_anime_title),
            dialogText = stringResource(R.string.delete_fav_anime_text, anime.title),
            onConfirmation = {
                openAlertDialog = false
                anime.favorite = !anime.favorite
            },
            onDismissRequest = { openAlertDialog = false }
        )
    }
    Row (modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        Column (modifier = Modifier.weight(1f)) {
            Row {
                AnimeCard(
                    anime,
                    modifier = Modifier.width(200.dp).height(300.dp),
                    onClickFav = {
                        if (!anime.favorite) {
                            onFavClick(anime)
                        } else {
                            // Solo lo quiero utilizar para eliminar
                            openAlertDialog = true
                        }
                    }
                )
                Column (modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(R.string.anime_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Text(
                        text = anime.title,
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
                        text = anime.ranked.toString(),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.score),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.score.toString(),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.num_episodes),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    )
                    Text(
                        text = if (anime.number_episodes.toString() == "null") "..." else anime.number_episodes.toString(),
                        fontSize = 18.sp
                    )
                }
            }
            Row (modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Column (modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = anime.description,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column (modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.comment_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            CommentStandard(userName = "Javier Landero", comment = "Este anime es increible, mucha historia")
            CommentStandard(userName = "Carlos Méndez", comment = "Me encanta este anime, los personajes son geniales")
            CommentStandard(userName = "Ana López", comment = "¡Totalmente de acuerdo! La trama es muy profunda")
            CommentStandard()
        }
    }
}