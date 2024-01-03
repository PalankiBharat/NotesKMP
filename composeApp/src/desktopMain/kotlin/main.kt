
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.composeAppModule
import di.initComposeKoin


fun main() = application {
    initComposeKoin {
        listOf(
            composeAppModule,
        )
    }
    Window(onCloseRequest = ::exitApplication, title = "Notes App KMP") {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}