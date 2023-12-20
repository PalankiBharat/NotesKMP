import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App() }
}