package com.example.otakuverse.ui.screens.profile

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.MailOutline
import androidx.compose.material.icons.twotone.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.otakuverse.R
import com.example.otakuverse.data.ThemeMode
import com.example.otakuverse.utils.getWindowSizeClass

@SuppressLint("ContextCastToActivity", "StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory),
    onClickSesion: () -> Unit,
    onTheme: (Boolean) -> Unit
) {
    val uiState by profileViewModel.uiState.collectAsState()
    val windowSize = getWindowSizeClass(LocalContext.current as Activity)
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            InformationUser(
                windowSize,
                onClickSesion = {
                    profileViewModel.setSettings(
                        username = "",
                        uiState.themeMode
                    )
                    onClickSesion()
                },
                sesion = uiState.username
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ThemeMode.entries.forEach { theme ->
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        RadioButton(
                            selected = (theme.mode == uiState.themeMode),
                            onClick = {
                                profileViewModel.setSettings(
                                    username = uiState.username,
                                    themeMode = theme.mode
                                )
                                onTheme(theme.mode)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Tema ${if(theme.mode) "oscuro" else "claro"}")
                    }
                }
            }

        }
    }
}

@Composable
fun InformationUser(
    windowSize: WindowWidthSizeClass,
    sesion: String,
    onClickSesion: () -> Unit
) {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            Image(
                painter = painterResource(R.drawable.ace_perfil),
                contentDescription = stringResource(R.string.image_user),
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = sesion,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                Row {
                    Icon(Icons.TwoTone.Phone, contentDescription = stringResource(R.string.icon_phone))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "+34 123456789",
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Icon(Icons.TwoTone.MailOutline, contentDescription = stringResource(R.string.icon_mail))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "jlanrod2009@g.educaand.es",
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(onClick = { /* code... */ }) {
                    Text("Editar Perfil", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = onClickSesion
                ) {
                    Text("Cerrar sesión", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        else -> {
            Row {
                Image(
                    painter = painterResource(R.drawable.ace_perfil),
                    contentDescription = stringResource(R.string.image_user),
                    modifier = Modifier
                        .size(200.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = sesion,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold

                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column {
                        Row {
                            Icon(Icons.TwoTone.Phone, contentDescription = stringResource(R.string.icon_phone))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "+34 123456789",
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Icon(Icons.TwoTone.MailOutline, contentDescription = stringResource(R.string.icon_mail))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "jlanrod2009@g.educaand.es",
                                fontSize = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Button(onClick = { /* code... */ }) {
                            Text(stringResource(R.string.text_button_edit_profile), style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        if (sesion != "") {
                            Button(onClick = onClickSesion) {
                                Text(stringResource(R.string.logout_button), style = MaterialTheme.typography.bodyMedium)
                            }
                        } else {
                            Button(onClick = onClickSesion) {
                                Text(stringResource(R.string.login_button), style = MaterialTheme.typography.bodyMedium)
                            }
                        }

                    }
                }
            }
        }
    }
}