package com.example.otakuverse.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBarExample() },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            Greeting(
                name = "About"
            )
        }
    }


}