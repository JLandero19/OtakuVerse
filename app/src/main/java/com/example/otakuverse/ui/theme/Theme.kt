package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.otakuverse.ui.theme.backgroundDark
import com.example.otakuverse.ui.theme.backgroundDarkHighContrast
import com.example.otakuverse.ui.theme.backgroundDarkMediumContrast
import com.example.otakuverse.ui.theme.backgroundLight
import com.example.otakuverse.ui.theme.backgroundLightHighContrast
import com.example.otakuverse.ui.theme.backgroundLightMediumContrast
import com.example.otakuverse.ui.theme.customColor1ContainerDark
import com.example.otakuverse.ui.theme.customColor1ContainerDarkHighContrast
import com.example.otakuverse.ui.theme.customColor1ContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.customColor1ContainerLight
import com.example.otakuverse.ui.theme.customColor1ContainerLightHighContrast
import com.example.otakuverse.ui.theme.customColor1ContainerLightMediumContrast
import com.example.otakuverse.ui.theme.customColor1Dark
import com.example.otakuverse.ui.theme.customColor1DarkHighContrast
import com.example.otakuverse.ui.theme.customColor1DarkMediumContrast
import com.example.otakuverse.ui.theme.customColor1Light
import com.example.otakuverse.ui.theme.customColor1LightHighContrast
import com.example.otakuverse.ui.theme.customColor1LightMediumContrast
import com.example.otakuverse.ui.theme.customColor2ContainerDark
import com.example.otakuverse.ui.theme.customColor2ContainerDarkHighContrast
import com.example.otakuverse.ui.theme.customColor2ContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.customColor2ContainerLight
import com.example.otakuverse.ui.theme.customColor2ContainerLightHighContrast
import com.example.otakuverse.ui.theme.customColor2ContainerLightMediumContrast
import com.example.otakuverse.ui.theme.customColor2Dark
import com.example.otakuverse.ui.theme.customColor2DarkHighContrast
import com.example.otakuverse.ui.theme.customColor2DarkMediumContrast
import com.example.otakuverse.ui.theme.customColor2Light
import com.example.otakuverse.ui.theme.customColor2LightHighContrast
import com.example.otakuverse.ui.theme.customColor2LightMediumContrast
import com.example.otakuverse.ui.theme.errorContainerDark
import com.example.otakuverse.ui.theme.errorContainerDarkHighContrast
import com.example.otakuverse.ui.theme.errorContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.errorContainerLight
import com.example.otakuverse.ui.theme.errorContainerLightHighContrast
import com.example.otakuverse.ui.theme.errorContainerLightMediumContrast
import com.example.otakuverse.ui.theme.errorDark
import com.example.otakuverse.ui.theme.errorDarkHighContrast
import com.example.otakuverse.ui.theme.errorDarkMediumContrast
import com.example.otakuverse.ui.theme.errorLight
import com.example.otakuverse.ui.theme.errorLightHighContrast
import com.example.otakuverse.ui.theme.errorLightMediumContrast
import com.example.otakuverse.ui.theme.inverseOnSurfaceDark
import com.example.otakuverse.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.otakuverse.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.otakuverse.ui.theme.inverseOnSurfaceLight
import com.example.otakuverse.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.otakuverse.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.otakuverse.ui.theme.inversePrimaryDark
import com.example.otakuverse.ui.theme.inversePrimaryDarkHighContrast
import com.example.otakuverse.ui.theme.inversePrimaryDarkMediumContrast
import com.example.otakuverse.ui.theme.inversePrimaryLight
import com.example.otakuverse.ui.theme.inversePrimaryLightHighContrast
import com.example.otakuverse.ui.theme.inversePrimaryLightMediumContrast
import com.example.otakuverse.ui.theme.inverseSurfaceDark
import com.example.otakuverse.ui.theme.inverseSurfaceDarkHighContrast
import com.example.otakuverse.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.otakuverse.ui.theme.inverseSurfaceLight
import com.example.otakuverse.ui.theme.inverseSurfaceLightHighContrast
import com.example.otakuverse.ui.theme.inverseSurfaceLightMediumContrast
import com.example.otakuverse.ui.theme.onBackgroundDark
import com.example.otakuverse.ui.theme.onBackgroundDarkHighContrast
import com.example.otakuverse.ui.theme.onBackgroundDarkMediumContrast
import com.example.otakuverse.ui.theme.onBackgroundLight
import com.example.otakuverse.ui.theme.onBackgroundLightHighContrast
import com.example.otakuverse.ui.theme.onBackgroundLightMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor1ContainerDark
import com.example.otakuverse.ui.theme.onCustomColor1ContainerDarkHighContrast
import com.example.otakuverse.ui.theme.onCustomColor1ContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor1ContainerLight
import com.example.otakuverse.ui.theme.onCustomColor1ContainerLightHighContrast
import com.example.otakuverse.ui.theme.onCustomColor1ContainerLightMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor1Dark
import com.example.otakuverse.ui.theme.onCustomColor1DarkHighContrast
import com.example.otakuverse.ui.theme.onCustomColor1DarkMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor1Light
import com.example.otakuverse.ui.theme.onCustomColor1LightHighContrast
import com.example.otakuverse.ui.theme.onCustomColor1LightMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor2ContainerDark
import com.example.otakuverse.ui.theme.onCustomColor2ContainerDarkHighContrast
import com.example.otakuverse.ui.theme.onCustomColor2ContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor2ContainerLight
import com.example.otakuverse.ui.theme.onCustomColor2ContainerLightHighContrast
import com.example.otakuverse.ui.theme.onCustomColor2ContainerLightMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor2Dark
import com.example.otakuverse.ui.theme.onCustomColor2DarkHighContrast
import com.example.otakuverse.ui.theme.onCustomColor2DarkMediumContrast
import com.example.otakuverse.ui.theme.onCustomColor2Light
import com.example.otakuverse.ui.theme.onCustomColor2LightHighContrast
import com.example.otakuverse.ui.theme.onCustomColor2LightMediumContrast
import com.example.otakuverse.ui.theme.onErrorContainerDark
import com.example.otakuverse.ui.theme.onErrorContainerDarkHighContrast
import com.example.otakuverse.ui.theme.onErrorContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.onErrorContainerLight
import com.example.otakuverse.ui.theme.onErrorContainerLightHighContrast
import com.example.otakuverse.ui.theme.onErrorContainerLightMediumContrast
import com.example.otakuverse.ui.theme.onErrorDark
import com.example.otakuverse.ui.theme.onErrorDarkHighContrast
import com.example.otakuverse.ui.theme.onErrorDarkMediumContrast
import com.example.otakuverse.ui.theme.onErrorLight
import com.example.otakuverse.ui.theme.onErrorLightHighContrast
import com.example.otakuverse.ui.theme.onErrorLightMediumContrast
import com.example.otakuverse.ui.theme.onPrimaryContainerDark
import com.example.otakuverse.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.otakuverse.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.onPrimaryContainerLight
import com.example.otakuverse.ui.theme.onPrimaryContainerLightHighContrast
import com.example.otakuverse.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.otakuverse.ui.theme.onPrimaryDark
import com.example.otakuverse.ui.theme.onPrimaryDarkHighContrast
import com.example.otakuverse.ui.theme.onPrimaryDarkMediumContrast
import com.example.otakuverse.ui.theme.onPrimaryLight
import com.example.otakuverse.ui.theme.onPrimaryLightHighContrast
import com.example.otakuverse.ui.theme.onPrimaryLightMediumContrast
import com.example.otakuverse.ui.theme.onSecondaryContainerDark
import com.example.otakuverse.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.otakuverse.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.onSecondaryContainerLight
import com.example.otakuverse.ui.theme.onSecondaryContainerLightHighContrast
import com.example.otakuverse.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.otakuverse.ui.theme.onSecondaryDark
import com.example.otakuverse.ui.theme.onSecondaryDarkHighContrast
import com.example.otakuverse.ui.theme.onSecondaryDarkMediumContrast
import com.example.otakuverse.ui.theme.onSecondaryLight
import com.example.otakuverse.ui.theme.onSecondaryLightHighContrast
import com.example.otakuverse.ui.theme.onSecondaryLightMediumContrast
import com.example.otakuverse.ui.theme.onSurfaceDark
import com.example.otakuverse.ui.theme.onSurfaceDarkHighContrast
import com.example.otakuverse.ui.theme.onSurfaceDarkMediumContrast
import com.example.otakuverse.ui.theme.onSurfaceLight
import com.example.otakuverse.ui.theme.onSurfaceLightHighContrast
import com.example.otakuverse.ui.theme.onSurfaceLightMediumContrast
import com.example.otakuverse.ui.theme.onSurfaceVariantDark
import com.example.otakuverse.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.otakuverse.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.otakuverse.ui.theme.onSurfaceVariantLight
import com.example.otakuverse.ui.theme.onSurfaceVariantLightHighContrast
import com.example.otakuverse.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.otakuverse.ui.theme.onTertiaryContainerDark
import com.example.otakuverse.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.otakuverse.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.onTertiaryContainerLight
import com.example.otakuverse.ui.theme.onTertiaryContainerLightHighContrast
import com.example.otakuverse.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.otakuverse.ui.theme.onTertiaryDark
import com.example.otakuverse.ui.theme.onTertiaryDarkHighContrast
import com.example.otakuverse.ui.theme.onTertiaryDarkMediumContrast
import com.example.otakuverse.ui.theme.onTertiaryLight
import com.example.otakuverse.ui.theme.onTertiaryLightHighContrast
import com.example.otakuverse.ui.theme.onTertiaryLightMediumContrast
import com.example.otakuverse.ui.theme.outlineDark
import com.example.otakuverse.ui.theme.outlineDarkHighContrast
import com.example.otakuverse.ui.theme.outlineDarkMediumContrast
import com.example.otakuverse.ui.theme.outlineLight
import com.example.otakuverse.ui.theme.outlineLightHighContrast
import com.example.otakuverse.ui.theme.outlineLightMediumContrast
import com.example.otakuverse.ui.theme.outlineVariantDark
import com.example.otakuverse.ui.theme.outlineVariantDarkHighContrast
import com.example.otakuverse.ui.theme.outlineVariantDarkMediumContrast
import com.example.otakuverse.ui.theme.outlineVariantLight
import com.example.otakuverse.ui.theme.outlineVariantLightHighContrast
import com.example.otakuverse.ui.theme.outlineVariantLightMediumContrast
import com.example.otakuverse.ui.theme.primaryContainerDark
import com.example.otakuverse.ui.theme.primaryContainerDarkHighContrast
import com.example.otakuverse.ui.theme.primaryContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.primaryContainerLight
import com.example.otakuverse.ui.theme.primaryContainerLightHighContrast
import com.example.otakuverse.ui.theme.primaryContainerLightMediumContrast
import com.example.otakuverse.ui.theme.primaryDark
import com.example.otakuverse.ui.theme.primaryDarkHighContrast
import com.example.otakuverse.ui.theme.primaryDarkMediumContrast
import com.example.otakuverse.ui.theme.primaryLight
import com.example.otakuverse.ui.theme.primaryLightHighContrast
import com.example.otakuverse.ui.theme.primaryLightMediumContrast
import com.example.otakuverse.ui.theme.scrimDark
import com.example.otakuverse.ui.theme.scrimDarkHighContrast
import com.example.otakuverse.ui.theme.scrimDarkMediumContrast
import com.example.otakuverse.ui.theme.scrimLight
import com.example.otakuverse.ui.theme.scrimLightHighContrast
import com.example.otakuverse.ui.theme.scrimLightMediumContrast
import com.example.otakuverse.ui.theme.secondaryContainerDark
import com.example.otakuverse.ui.theme.secondaryContainerDarkHighContrast
import com.example.otakuverse.ui.theme.secondaryContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.secondaryContainerLight
import com.example.otakuverse.ui.theme.secondaryContainerLightHighContrast
import com.example.otakuverse.ui.theme.secondaryContainerLightMediumContrast
import com.example.otakuverse.ui.theme.secondaryDark
import com.example.otakuverse.ui.theme.secondaryDarkHighContrast
import com.example.otakuverse.ui.theme.secondaryDarkMediumContrast
import com.example.otakuverse.ui.theme.secondaryLight
import com.example.otakuverse.ui.theme.secondaryLightHighContrast
import com.example.otakuverse.ui.theme.secondaryLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceBrightDark
import com.example.otakuverse.ui.theme.surfaceBrightDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceBrightDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceBrightLight
import com.example.otakuverse.ui.theme.surfaceBrightLightHighContrast
import com.example.otakuverse.ui.theme.surfaceBrightLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerDark
import com.example.otakuverse.ui.theme.surfaceContainerDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighDark
import com.example.otakuverse.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighLight
import com.example.otakuverse.ui.theme.surfaceContainerHighLightHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighestDark
import com.example.otakuverse.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighestLight
import com.example.otakuverse.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerLight
import com.example.otakuverse.ui.theme.surfaceContainerLightHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowDark
import com.example.otakuverse.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowLight
import com.example.otakuverse.ui.theme.surfaceContainerLowLightHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowestDark
import com.example.otakuverse.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowestLight
import com.example.otakuverse.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.otakuverse.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceDark
import com.example.otakuverse.ui.theme.surfaceDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceDimDark
import com.example.otakuverse.ui.theme.surfaceDimDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceDimDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceDimLight
import com.example.otakuverse.ui.theme.surfaceDimLightHighContrast
import com.example.otakuverse.ui.theme.surfaceDimLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceLight
import com.example.otakuverse.ui.theme.surfaceLightHighContrast
import com.example.otakuverse.ui.theme.surfaceLightMediumContrast
import com.example.otakuverse.ui.theme.surfaceVariantDark
import com.example.otakuverse.ui.theme.surfaceVariantDarkHighContrast
import com.example.otakuverse.ui.theme.surfaceVariantDarkMediumContrast
import com.example.otakuverse.ui.theme.surfaceVariantLight
import com.example.otakuverse.ui.theme.surfaceVariantLightHighContrast
import com.example.otakuverse.ui.theme.surfaceVariantLightMediumContrast
import com.example.otakuverse.ui.theme.tertiaryContainerDark
import com.example.otakuverse.ui.theme.tertiaryContainerDarkHighContrast
import com.example.otakuverse.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.otakuverse.ui.theme.tertiaryContainerLight
import com.example.otakuverse.ui.theme.tertiaryContainerLightHighContrast
import com.example.otakuverse.ui.theme.tertiaryContainerLightMediumContrast
import com.example.otakuverse.ui.theme.tertiaryDark
import com.example.otakuverse.ui.theme.tertiaryDarkHighContrast
import com.example.otakuverse.ui.theme.tertiaryDarkMediumContrast
import com.example.otakuverse.ui.theme.tertiaryLight
import com.example.otakuverse.ui.theme.tertiaryLightHighContrast
import com.example.otakuverse.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

@Immutable
data class ExtendedColorScheme(
    val customColor1: ColorFamily,
    val customColor2: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1Light,
  onCustomColor1Light,
  customColor1ContainerLight,
  onCustomColor1ContainerLight,
  ),
  customColor2 = ColorFamily(
  customColor2Light,
  onCustomColor2Light,
  customColor2ContainerLight,
  onCustomColor2ContainerLight,
  ),
)

val extendedDark = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1Dark,
  onCustomColor1Dark,
  customColor1ContainerDark,
  onCustomColor1ContainerDark,
  ),
  customColor2 = ColorFamily(
  customColor2Dark,
  onCustomColor2Dark,
  customColor2ContainerDark,
  onCustomColor2ContainerDark,
  ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1LightMediumContrast,
  onCustomColor1LightMediumContrast,
  customColor1ContainerLightMediumContrast,
  onCustomColor1ContainerLightMediumContrast,
  ),
  customColor2 = ColorFamily(
  customColor2LightMediumContrast,
  onCustomColor2LightMediumContrast,
  customColor2ContainerLightMediumContrast,
  onCustomColor2ContainerLightMediumContrast,
  ),
)

val extendedLightHighContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1LightHighContrast,
  onCustomColor1LightHighContrast,
  customColor1ContainerLightHighContrast,
  onCustomColor1ContainerLightHighContrast,
  ),
  customColor2 = ColorFamily(
  customColor2LightHighContrast,
  onCustomColor2LightHighContrast,
  customColor2ContainerLightHighContrast,
  onCustomColor2ContainerLightHighContrast,
  ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1DarkMediumContrast,
  onCustomColor1DarkMediumContrast,
  customColor1ContainerDarkMediumContrast,
  onCustomColor1ContainerDarkMediumContrast,
  ),
  customColor2 = ColorFamily(
  customColor2DarkMediumContrast,
  onCustomColor2DarkMediumContrast,
  customColor2ContainerDarkMediumContrast,
  onCustomColor2ContainerDarkMediumContrast,
  ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1DarkHighContrast,
  onCustomColor1DarkHighContrast,
  customColor1ContainerDarkHighContrast,
  onCustomColor1ContainerDarkHighContrast,
  ),
  customColor2 = ColorFamily(
  customColor2DarkHighContrast,
  onCustomColor2DarkHighContrast,
  customColor2ContainerDarkHighContrast,
  onCustomColor2ContainerDarkHighContrast,
  ),
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun OtakuverseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

