package com.example.otakuverse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuverse.model.Anime
import com.example.otakuverse.ui.components.AnimeCard
import com.example.otakuverse.ui.components.CommentStandard

@Composable
fun DetailScreen(anime: Anime, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Row (modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
                AnimeCard(anime, modifier = Modifier.width(200.dp).height(300.dp))
                Column (modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Título Anime",
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
                        text = "Clasificación",
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
                        text = "Puntuación",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.score.toString(),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Número de episodios",
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
                        text = "Descripción",
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
                        text = "Comentarios",
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
    }
}