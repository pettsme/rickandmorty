package com.pettsme.showcase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    val isDarkTheme: Boolean = isSystemInDarkTheme()
    val appColors: ThemedColors = remember(isDarkTheme) {
        if (isDarkTheme) DarkThemeColors else LightThemeColors
    }
    val appTypography = remember { DefaultAppTypography }

    CompositionLocalProvider(
        LocalColors provides appColors,
        LocalTypography provides appTypography,
    ) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                background = appColors.background,
                onBackground = appColors.primary,
            ),
            typography = materialTypography,
            content = content,
        )
    }
}

private val LocalColors = compositionLocalOf<ThemedColors> { LightThemeColors }
private val LocalTypography = staticCompositionLocalOf { DefaultAppTypography }

object ProjectTheme {
    val colors: ThemedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
