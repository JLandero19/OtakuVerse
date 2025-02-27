package com.example.otakuverse

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.otakuverse.data.UserPreferencesRepository
import com.example.otakuverse.otakuverserelease.dataStore
import com.example.otakuverse.ui.components.BottomNavigationBar
import com.example.otakuverse.ui.theme.OtakuverseTheme
import com.example.otakuverse.ui.components.CenterAlignedTopAppBar
import com.example.otakuverse.ui.components.StandardSearchBar
import com.example.otakuverse.ui.screens.AboutScreen
import com.example.otakuverse.ui.screens.detail.DetailScreen
import com.example.otakuverse.ui.screens.elementList.ElementListScreen
import com.example.otakuverse.ui.screens.LoginScreen
import com.example.otakuverse.ui.screens.favList.FavListScreen
import com.example.otakuverse.ui.screens.profile.ProfileScreen
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {

    @SuppressLint("IntentReset")
    fun shareApp() {
        val subject = "Información sobre la aplicación"
        val msg = """
            En está aplicación verás en detalle la información sobre los animes y mangas actuales.
            Tenemos acceso a esta información gracias a MyAnimeList que nos probé de las ultimos estrenos.
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:") // Solo aplicaciones de correo manejan esto.
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, msg)
        }

        startActivity(intent)
    }

    private lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar el repositorio
        userPreferencesRepository = UserPreferencesRepository(dataStore)

        setContent {

            // Verificar el tema (dark o light) según el estado del ViewModel
            var isDarkTheme by remember { mutableStateOf(false) }
            var profile by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                // Obtenemos el valor del tema desde el repositorio
                userPreferencesRepository.userPrefs.collect { userPrefs ->
                    isDarkTheme = userPrefs.themeMode // Establecer el tema al valor almacenado
                    profile = userPrefs.username
                }
            }

            OtakuverseTheme (
                dynamicColor = false,
                darkTheme = isDarkTheme
            ) {
                OtakuverseApp(
                    sesion = profile,
                    onShare = { shareApp() },
                    onChangeSesion = { username ->
                        profile = username
                    },
                    onThemeMode = { theme ->
                        isDarkTheme = theme
                    }
                )
            }
        }

        // false -> hace que desaparezca inmediatamente la SplashScreen
        // true -> hace que permanezca la SplashScreen
        installSplashScreen().setKeepOnScreenCondition { false }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun OtakuverseApp(
    sesion: String,
    onShare: () -> Unit = {},
    onChangeSesion: (String) -> Unit = {},
    onThemeMode: (Boolean) -> Unit = {},
) {
    // Solo tenemos una lista
//    var animes by remember { mutableStateOf(Datasource.getListXtimes(1)) }
    var userProfile by remember { mutableStateOf(sesion) }
//    var animeList by remember { mutableStateOf(animes) }
    var showSearchBar by remember { mutableStateOf(false) }
//    var textValue = ""

    // NavController
    val navController = rememberNavController()

    // Ruta inicial
    val currentRoute by navController.currentBackStackEntryFlow
        .map { it.destination.route }
        .collectAsState(initial = null)

    Scaffold(
        topBar = {
            // Controlamos la topBar y si debe aparecer el buscador o no
            if (showSearchBar) {
                StandardSearchBar(
                    onClickClearSearch = {
                        showSearchBar = !showSearchBar
                        // Resetea el buscador
//                        if (!showSearchBar) animeList = animes
                    },
                    onSearchText = { text ->
//                        textValue = text
//                        animeList = if (text.trimIndent().isNotEmpty()) {
//                            animes.filter { it.title.contains(text, ignoreCase = true) } as MutableList<Anime>
//                        } else {
//                            animes
//                        }
                    }
                )
            } else {
                CenterAlignedTopAppBar(
                    text = stringResource(R.string.app_name),
                    navController = navController,
                    currentRouteInfo = currentRoute,
                    sesion = (userProfile != ""),
                    onClickSearch = {
                        showSearchBar = !showSearchBar
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            /* Aquí puedes agregar lógica dinámica para la bottom bar */
            if (currentRoute?.startsWith("details/") == false) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    sesion = (userProfile != "")
                )
            }
        },
    ) { innerPadding ->
        // NavHost
        NavHost(
            navController = navController,
            startDestination = "anime_list"
        ) {
            composable("anime_list") {
                // Lista de animes
                ElementListScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                )
            }
            composable("fav_anime_list") {
                // Lista de animes favoritos
                FavListScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                )
            }
            composable("details/{itemId}/{favorite}") { backStackEntry ->
                // Por si no encuentra el heroe se pone ?: ""
                val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
                val favorite = backStackEntry.arguments?.getBoolean("favorite") ?: false
                // Detalles Anime
                DetailScreen(
//                    animeList.find { it.title == itemId }!!,
                    itemId.toInt(),
                    session = userProfile,
                    favorite = favorite,
                    modifier = Modifier.padding(innerPadding),
//                    navController = navController,
                )
            }
            composable("profile") {
                // Información del usuario
                ProfileScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    onClickSesion = {
                        userProfile = ""
                        onChangeSesion(userProfile)
                        navController.navigate("login")
                    },
                    onTheme = { darkTheme ->
                        onThemeMode(darkTheme)
                    }
                )
            }
            composable("login") {
                // Iniciar sesión
                LoginScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    onClickSesion = { username ->
                        userProfile = username
                        onChangeSesion(userProfile)
                        navController.navigate("profile")
                    }
                )
            }
            composable("about") {
                // Sobre Nosotros
                AboutScreen(
                    onShareButton = { onShare() },
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    OtakuverseTheme {
        OtakuverseApp("Javier Landero")
    }
}