@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.otakuverse

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.otakuverse.model.Anime
import com.example.otakuverse.model.Datasource
import com.example.otakuverse.ui.components.BottomNavigationBar
import com.example.otakuverse.ui.theme.OtakuverseTheme
import com.example.otakuverse.ui.components.CenterAlignedTopAppBar
import com.example.otakuverse.ui.components.StandardAlertDialog
import com.example.otakuverse.ui.components.StandardSearchBar
import com.example.otakuverse.ui.screens.AboutScreen
import com.example.otakuverse.ui.screens.DetailScreen
import com.example.otakuverse.ui.screens.ElementListScreen
import com.example.otakuverse.ui.screens.LoginScreen
import com.example.otakuverse.ui.screens.ProfileScreen
import kotlinx.coroutines.flow.map

class AboutActivity : ComponentActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            OtakuverseTheme (dynamicColor = false) {
                OtakuverseApp(
                    onShare = { shareApp() },
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
fun OtakuverseApp(onShare: () -> Unit = {}) {
    // Solo tenemos una lista
    var animes by remember { mutableStateOf(Datasource.getListXtimes(1)) }
    var profile by remember { mutableStateOf(false) }
    var animeList by remember { mutableStateOf(animes) }
    var showSearchBar by remember { mutableStateOf(false) }

    // NavController
    val navController = rememberNavController()

    // Ruta inicial
    val currentRoute by navController.currentBackStackEntryFlow
        .map { it.destination.route }
        .collectAsState(initial = null)

    Scaffold(
        topBar = {
            if (showSearchBar) {
                StandardSearchBar(
                    onClickClearSearch = {
                        showSearchBar = !showSearchBar
                    },
                    onSearchText = { text ->
                        if  (text.isNotEmpty()) {
                            animeList = animes.filter { it.title.contains(text, ignoreCase = true) } as MutableList<Anime>
                        } else {
                            animeList = animes
                        }
                    }
                )
            } else {
                CenterAlignedTopAppBar(
                    text = stringResource(R.string.app_name),
                    navController = navController,
                    currentRouteInfo = currentRoute,
                    sesion = profile,
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
                    sesion = profile
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
                    listAnime = animeList,
                    onFavClicked = { anime ->
                        // Actualiza el estado de favorito del anime que recibe por parametro
                        val updatedAnimes = animeList.map {
                            // Busca el anime por el titulo, pero funcionaría igual buscando por ID
                            if (it.title == anime.title) {
                                // Cambia el atributo de favorito
                                it.copy(favorite = !it.favorite)
                            } else {
                                // En caso de no sea igual devuelve el iterador
                                it
                            }
                        }
                        // Actualiza la lista de Animes
                        animes = updatedAnimes.toMutableList() // Actualiza la lista
                        animeList = animes
                    }
                )
            }
            composable("fav_anime_list") {
                // Lista de animes favoritos
                ElementListScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    listAnime = animes.filter { it.favorite }.toMutableList(),
                    onFavClicked = { anime ->
                        // Actualiza el estado de favorito del anime que recibe por parametro
                        val updatedAnimes = animes.map {
                            // Busca el anime por el titulo, pero funcionaría igual buscando por ID
                            if (it.title == anime.title) {
                                // Cambia el atributo de favorito
                                it.copy(favorite = !it.favorite)
                            } else {
                                // En caso de no sea igual devuelve el iterador
                                it
                            }
                        }
                        // Actualiza la lista de Animes
                        animes = updatedAnimes.toMutableList() // Actualiza la lista
                    }
                )
            }
            composable("details/{itemId}") { backStackEntry ->
                // Por si no encuentra el heroe se pone ?: ""
                val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
                // Detalles Anime
                DetailScreen(itemId.toString(), modifier = Modifier.padding(innerPadding), navController = navController)
            }
            composable("profile") {
                // Información del usuario
                ProfileScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    sesion = profile,
                    onClickSesion = {
                        profile = !profile
                        navController.navigate("login")
                    }
                )
            }
            composable("login") {
                // Iniciar sesión
                LoginScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    onClickSesion = {
                        profile = !profile
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
        OtakuverseApp()
    }
}