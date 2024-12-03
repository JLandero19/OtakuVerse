package com.example.otakuverse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuverse.model.Anime
import com.example.otakuverse.ui.components.AnimeCard

@Composable
fun DetailScreen(anime: Anime, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Row (modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
                AnimeCard(anime, modifier = Modifier.width(200.dp).height(300.dp))
                Column (modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Título Anime",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Text(
                        text = anime.title,
                        style = TextStyle(fontSize = 16.sp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Clasificación",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Text(
                        text = anime.ranked.toString(),
                        style = TextStyle(fontSize = 16.sp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Puntuación",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    )
                    Text(
                        text = anime.score.toString(),
                        style = TextStyle(fontSize = 16.sp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Número de episodios",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    )
                    Text(
                        text = if (anime.number_episodes.toString() == "null") "..." else anime.number_episodes.toString(),
                        style = TextStyle(fontSize = 16.sp),
                    )
                }
            }
            Row (modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Column (modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Descripción",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = anime.description,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }
        }
    }
}