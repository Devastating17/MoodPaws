package com.example.moodpaws.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.moodpaws.ui.theme.Shapes
private val LightColorScheme = lightColorScheme(
    primary = Flax,
    secondary = Olivine,
    background = SoftIvory,
    surface = PalePeach,
    onPrimary = CharcoalGray,
    onSecondary = Color.White,
    onBackground = CharcoalGray,
    onSurface = CharcoalGray,
)

private val DarkColorScheme = darkColorScheme(
    primary = Flax,
    secondary = Olivine,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun MoodPawsTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
