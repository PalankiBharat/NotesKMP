import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.initDi
import presentation.Auth.LoginSignupScreen

@Composable
fun App() {
    initDi()
    MaterialTheme {
        LoginSignupScreen()
    }
}