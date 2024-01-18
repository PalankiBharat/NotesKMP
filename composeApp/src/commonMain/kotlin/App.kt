
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.Auth.AuthScreen
import theme.NotesAppTheme

@Composable
fun App() {
    NotesAppTheme {
        Navigator(AuthScreen())
    }
}