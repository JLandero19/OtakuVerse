package com.example.otakuverse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen(
    onShareButton : () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar() },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
        ) {
            TitlePageStandard(
                str = "Sobre la aplicación",
            )
            Text(
                text = """
                    En está aplicación verás en detalle la información sobrelos animes y mangas actuales.
                    Tenemos acceso a esta información gracias a MyAnimeList que nos probé de las ultimos estrenos.
                """.trimIndent(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(all = 20.dp)
            )

            Text(
                text = "Versión 1.0.0",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(all = 20.dp)
            )
            IconButton(
                onClick = {
                    onShareButton()
                },
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Compartir",
                    modifier = Modifier.size(56.dp)
                )
            }
        }
    }
}