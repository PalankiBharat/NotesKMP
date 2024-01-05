
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import presentation.Auth.LoginSignupScreen
import theme.NotesAppTheme

@Composable
fun App() {
    NotesAppTheme {
        LoginSignupScreen()
    }
}