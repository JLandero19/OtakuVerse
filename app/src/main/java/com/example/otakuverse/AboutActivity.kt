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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.otakuverse.model.Anime
import com.example.otakuverse.ui.theme.OtakuverseTheme
import com.example.otakuverse.ui.components.CenterAlignedTopAppBar
import com.example.otakuverse.ui.screens.AboutScreen
import com.example.otakuverse.ui.screens.DetailScreen
import com.example.otakuverse.ui.screens.ElementListScreen
import com.example.otakuverse.ui.screens.ProfileScreen

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
            OtakuverseTheme {
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

@Composable
fun OtakuverseApp(onShare: () -> Unit = {}, title: String = stringResource(R.string.about)) {
    val anime = Anime(
        "One Piece",
        "one_piece",
        2,
        8.71F,
        null,
        "Oct 1999 -",
        "One Piece narra la historia de un joven llamado Monkey D. Luffy, que inspirado por su amigo pirata Shanks, comienza un viaje para alcanzar su sueño, ser el Rey de los piratas, para lo cual deberá encontrar el tesoro One Piece dejado por el anterior rey de los piratas Gol D. Roger.",
        true
    )

    Scaffold(
        topBar = { CenterAlignedTopAppBar(text = title) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
//        AboutScreen(
//            onShareButton = { onShare() },
//            modifier = Modifier.padding(innerPadding)
//        )

        // Lista de animes
//        ElementListScreen(modifier = Modifier.padding(innerPadding))

        // Lista de animes favoritos
//        ElementListScreen(
//            favorite = true,
//            modifier = Modifier.padding(innerPadding)
//        )

        // Detalles Anime
//        DetailScreen(anime, modifier = Modifier.padding(innerPadding))

        // Información del usuario
        ProfileScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    OtakuverseTheme {
        OtakuverseApp(title = stringResource(R.string.about))
    }
}