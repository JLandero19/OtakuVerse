@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.otakuverse.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.otakuverse.R

@Composable
fun StandardSearchBar(
    expanded: Boolean = false,
    onClickClearSearch: () -> Unit,
    onSearchText: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    SearchBar(
        inputField = {
            TextField(
                value = text, // valor inicial del campo de búsqueda
                onValueChange = {
                    text = it
                    onSearchText(text)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(id = R.string.search)) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search))
                },
                trailingIcon = {
                    IconButton(onClick = onClickClearSearch) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = stringResource(R.string.close_icon))
                    }
                }
            )
        },
        onExpandedChange = {

        },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large, // forma del SearchBar
        tonalElevation = 8.dp, // sombra cuando el SearchBar está expandido
        shadowElevation = 4.dp, // sombra cuando el SearchBar no está expandido
        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Top), // ajusta el SearchBar a las barras del sistema
        content = {

        },
        expanded = expanded,
        colors = SearchBarDefaults.colors(
            dividerColor = MaterialTheme.colorScheme.background
        )
    )
}