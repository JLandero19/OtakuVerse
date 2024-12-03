@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.otakuverse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuverse.R
import com.example.otakuverse.model.Anime
import com.example.otakuverse.model.Datasource

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun TitlePageStandard(str: String, modifier: Modifier = Modifier) {
    Text(
        text = str,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

// Esta función me crea un TopBar básico
@Composable
fun CenterAlignedTopAppBar(text: String = stringResource(R.string.title)) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        // Cambia los colores del TopBar
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        // Título del TopBar
        title = {
            Text(
                text = text,
                maxLines = 1,
                style = MaterialTheme.typography.headlineMedium,
                overflow = TextOverflow.Ellipsis
            )
        },
        // Icono de navegación
//        navigationIcon = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Volver a la pantalla anterior",
//                )
//            }
//        },
        // Icono de acción
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Image(
                    painter = painterResource(R.drawable.ace_perfil),
                    contentDescription = stringResource(R.string.image_user),
                    modifier = Modifier.clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun TitleCardStandard(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleSmall,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun ImageAnime(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
//    title: String = "",
    drawable: Int,
    contentDesc: String = "",
    favorite: Boolean = false,
    height: Int = 0,
    width: Int = 0
) {
    val contentDescription =
        if (contentDesc == "")
            stringResource(id = R.string.default_content_description)
        else
            contentDesc
    if(height != 0 && width != 0) {
        Box {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = contentDescription,
                modifier
                    .height(height.dp)
                    .width(width.dp),
                contentScale = contentScale
            )

            // Botón de acción con ícono
            // Botón de acción con ícono
            IconButton(
                onClick = { /* Acción futura, como mostrar más información del héroe */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(50.dp)
                    ),
            ) {
                Icon(
                    imageVector = if (!favorite) Icons.TwoTone.FavoriteBorder else Icons.TwoTone.Favorite,
                    modifier = Modifier.size(24.dp),
                    contentDescription = stringResource(R.string.favorite),
                    tint = MaterialTheme.colorScheme.error
                )
            }
//            TitleCardStandard(
//                text = title,
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .padding(16.dp)
//            )
        }
    } else {
        Box {
            Image(
                modifier = modifier,
                painter = painterResource(id = drawable),
                contentDescription = contentDescription,
                contentScale = contentScale
            )

            // Botón de acción con ícono
            IconButton(
                onClick = { /* Acción futura, como mostrar más información del héroe */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(50.dp)
                    ),
            ) {
                Icon(
                    imageVector = if (!favorite) Icons.TwoTone.FavoriteBorder else Icons.TwoTone.Favorite,
                    modifier = Modifier.size(24.dp),
                    contentDescription = stringResource(R.string.favorite),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp),

        shape = MaterialTheme.shapes.large,
        onClick = {}
    ) {
        // Imagen del anime
        ImageAnime(
            modifier = Modifier.fillMaxSize().height(250.dp),
            drawable = Datasource.getDrawableIdByName(anime.image_url),
//            title = anime.title,
            favorite = anime.favorite
        )
    }
}

