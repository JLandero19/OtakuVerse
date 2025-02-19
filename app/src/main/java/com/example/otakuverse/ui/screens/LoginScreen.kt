package com.example.otakuverse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.otakuverse.R
import com.example.otakuverse.ui.components.PasswordTextField
import com.example.otakuverse.ui.components.StandardText
import com.example.otakuverse.ui.components.StandardTextField
import com.example.otakuverse.ui.components.StandardTextField2
import com.example.otakuverse.ui.screens.profile.ProfileViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory),
    onClickSesion: (String) -> Unit
) {
    val uiState by profileViewModel.uiState.collectAsState()
    var userState by remember { mutableStateOf("") }
    LazyColumn (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        item {
            StandardText(str = stringResource(R.string.title_login), style = MaterialTheme.typography.titleLarge, fontSize = 40.sp)
            Spacer(modifier = Modifier.height(10.dp))
            StandardTextField2(
                label = stringResource(R.string.username),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.TwoTone.AccountCircle,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(R.string.username)
                    )
                },
                onChange = { user ->
                    userState = user
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
//            PasswordTextField(stringResource(R.string.password), { Icon(imageVector = Icons.TwoTone.Lock, tint = MaterialTheme.colorScheme.primary, contentDescription = stringResource(R.string.password)) })
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    profileViewModel.setSettings(
                        username = userState,
                        themeMode = uiState.themeMode
                    )
                    onClickSesion(profileViewModel.uiState.value.username)
                }
            ) {
                Text(stringResource(R.string.title_login), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}