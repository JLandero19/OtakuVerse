@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.otakuverse.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.otakuverse.R
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.AnimeDetail


@Composable
fun StandardText(
    str: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.primary,
    fontSize: TextUnit = 25.sp
) {
    Text(
        text = str,
        style = style,
        color = color,
        fontSize = fontSize,
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
fun CenterAlignedTopAppBar(
    text: String = stringResource(R.string.app_name),
    navController: NavHostController,
    sesion: Boolean,
    currentRouteInfo: String? = "",
    onClickSearch: () -> Unit
) {
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
        navigationIcon = {
            if (currentRouteInfo?.startsWith("details/") == true) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_page),
                    )
                }
            }
        },
        // Icono de acción
        actions = {
            if (currentRouteInfo?.contains("anime_list") == true) {
                IconButton(
                    onClick = onClickSearch
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search))
                }
            }

            IconButton(onClick = {
                if (sesion) {
                    navController.navigate("profile")
                } else {
                    navController.navigate("login")
                }
            }) {
                Image(
                    painter = if (sesion) painterResource(R.drawable.ace_perfil) else painterResource(R.drawable.user_default),
                    contentDescription = stringResource(R.string.image_user),
                    modifier = Modifier.clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

        },
        scrollBehavior = scrollBehavior,
    )
}

//@Composable
//fun TitleCardStandard(text: String, modifier: Modifier = Modifier) {
//    Text(
//        text = text,
//        fontWeight = FontWeight.Bold,
//        fontSize = 25.sp,
//        color = MaterialTheme.colorScheme.onPrimary,
//        style = MaterialTheme.typography.titleSmall,
//        textAlign = TextAlign.Center,
//        modifier = modifier.padding(8.dp)
//    )
//}

@Composable
fun CommentStandard(
    modifier: Modifier = Modifier,
    userName: String = stringResource(R.string.anonymous_user),
    date: String = "05-12-2024",
    comment: String = stringResource(R.string.no_comment)
) {
    Row (modifier = modifier
        .padding(8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.user_default),
            contentDescription = stringResource(R.string.image_user),
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(60.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Row (verticalAlignment = Alignment.CenterVertically) {
                StandardText(
                    userName,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(15.dp))
                StandardText(
                    "Última modficación: $date",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 10.sp)
            }
            StandardText(
                comment,
                style = MaterialTheme.typography.bodyMedium, fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ImageAnime(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    drawable: Int,
    contentDesc: String = "",
    favorite: Boolean = false,
    height: Int = 0,
    width: Int = 0,
    onClickFav: () -> Unit = {}
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
                onClick = onClickFav,
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
                    tint = MaterialTheme.colorScheme.error,
                )
            }
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
                onClick = onClickFav,
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
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Composable
fun AsyncImageAnime(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    image: String,
    contentDesc: String = "",
    useButton: Boolean = true,
    favorite: Boolean = false,
    onClickFav: () -> Unit = {}
) {
    val context = LocalContext.current
    val validate = if (!favorite) stringResource(R.string.message_fav_okey) else stringResource(R.string.message_fav_error)
    val contentDescription =
        if (contentDesc == "")
            stringResource(id = R.string.default_content_description)
        else
            contentDesc

    Surface(modifier = modifier) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.mh_icono),
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier
            )
            // Botón de acción con ícono
            IconButton(
                onClick = {
                    onClickFav()
                    if (useButton) Toast.makeText(context, validate, Toast.LENGTH_SHORT).show()

                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(50.dp)
                    ),
            ) {
                Icon(
                    imageVector = if (!favorite) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                    modifier = Modifier.size(24.dp),
                    contentDescription = stringResource(R.string.favorite),
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Composable
fun AnimeCard(
    anime: Anime,
    favorite: Boolean = false,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit = {},
    onClickFav: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.large,
        onClick = onClickCard
    ) {
        // Imagen del anime
        AsyncImageAnime(
            modifier = Modifier
                .fillMaxSize()
                .height(250.dp),
            image = anime.picture_url,
            favorite = favorite,
            onClickFav = onClickFav
        )
    }
}

@Composable
fun AnimeDetailCard(
    anime: AnimeDetail?,
    favorite: Boolean = false,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.large,
        onClick = onClickCard
    ) {
        // Imagen del anime
//        ImageAnime(
//            modifier = Modifier
//                .fillMaxSize()
//                .height(250.dp),
//            drawable = Datasource.getDrawableIdByName(anime.picture_url),
//            favorite = false,
//            onClickFav = onClickFav
//        )
        // Imagen del anime
        anime?.let {
            AsyncImageAnime(
                modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp),
                image = it.pictureUrl,
                useButton = false,
                favorite = favorite,
            )
        }
    }
}

@Composable
fun StandardTextField(label: String, leadingIcon: @Composable () -> Unit = {}) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        placeholder =  { Text(label) },
        leadingIcon = leadingIcon
    )
}

@Composable
fun StandardTextField2(
    label: String,
    leadingIcon: @Composable () -> Unit = {},
    onChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onChange(text)
        },
        label = { Text(label) },
        placeholder =  { Text(label) },
        leadingIcon = leadingIcon
    )
}

@Composable
fun PasswordTextField(label: String, leadingIcon: @Composable () -> Unit = {}) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        placeholder = { Text(label) },
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Composable
fun StandardAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun StandardDialogForm(
    dialogTitle: String = "",
    dialogText: String = "",
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onDismiss: () -> Unit,
    textState: MutableState<String>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(dialogTitle) },
        text = {
            Column {
                OutlinedTextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    label = { Text(dialogText) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5, // Esto hace que el TextField actúe como un TextArea
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}
