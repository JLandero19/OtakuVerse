package com.example.otakuverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.OtakuverseTheme
import com.example.otakuverse.ui.screens.ElementListScreen

class ElementListFavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtakuverseTheme {
                ElementListScreen(
                    favorite = true,
                    titleScreen = stringResource(R.string.favoriteList)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementListScreenPreview() {
    OtakuverseTheme {
        ElementListScreen(favorite = true)
    }
}