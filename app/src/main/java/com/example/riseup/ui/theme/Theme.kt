package com.example.riseup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Olive,
    secondary = LightGold,
    tertiary = LightBronzeGold,
    background = DarkGrayBlue,
    surface = DeepGrayBrown,
    onPrimary = DarkGrayBlue,
    onSecondary = DarkGrayBlue,
    onTertiary = CoalGray,
    onBackground = LightGray,
    onSurface = LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = DarkGreen,
    secondary = Gold,
    tertiary = GoldYellow,
    background = LightGrayBeige,
    surface = LightWarmBeige,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = DarkGray,
    onBackground = DarkGray,
    onSurface = DarkGray
)

@Composable
fun RiseUpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}