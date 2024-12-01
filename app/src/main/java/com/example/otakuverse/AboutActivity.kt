package com.example.otakuverse

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.otakuverse.ui.screens.AboutScreen
import com.example.otakuverse.ui.screens.ElementListScreen
import com.example.otakuverse.ui.theme.OtakuverseTheme

class AboutActivity : ComponentActivity() {

    @SuppressLint("IntentReset")
    private fun shareApp() {
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

                ElementListScreen()

//                AboutScreen(
//                    onShareButton = { shareApp() }
//                )
            }
        }

        // false -> hace que desaparezca inmediatamente la SplashScreen
        // true -> hace que permanezca la SplashScreen
        installSplashScreen().setKeepOnScreenCondition { false }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    OtakuverseTheme {
        AboutScreen(
            onShareButton = { }
        )
    }
}