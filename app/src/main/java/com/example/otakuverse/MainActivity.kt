package com.example.otakuverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.otakuverse.ui.theme.OtakuverseTheme
import com.example.otakuverse.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtakuverseTheme {
                MainScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    OtakuverseTheme {
        MainScreen()
    }
}