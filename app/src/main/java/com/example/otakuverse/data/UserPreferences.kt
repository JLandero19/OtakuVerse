package com.example.otakuverse.data

data class UserPreferences(
    val username: String = "",
    val themeMode: Boolean = ThemeMode.LIGHT.mode
) {
    companion object {
        const val SETTINGS_FILE = "settings"
    }
}

enum class ThemeMode(val mode: Boolean) {
    DARK(true), LIGHT(false)
}