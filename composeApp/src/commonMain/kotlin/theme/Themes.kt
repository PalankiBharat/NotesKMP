package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val AppDarkColors = darkColors(
    // M2 dark Color parameters
    primary = themeYellow,
    secondary = themeBlue,
    background = darkColorBackground,
    surface = lightColorBackground,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White,
    error = errorColor,
)

@Composable
fun NotesAppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = AppDarkColors,
        content = content,
        typography = getTypography()
    )
}