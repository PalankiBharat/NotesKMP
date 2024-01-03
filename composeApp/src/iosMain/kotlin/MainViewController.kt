
import androidx.compose.ui.window.ComposeUIViewController
import di.composeAppModule
import di.initComposeKoin

fun MainViewController() = ComposeUIViewController {
    initComposeKoin {
        listOf(
            composeAppModule,
        )
    }
    App()
}
