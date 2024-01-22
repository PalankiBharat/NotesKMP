import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import data.preferance.PreferenceManager
import org.koin.compose.koinInject
import presentation.Auth.AuthScreen
import presentation.Notes.NotesScreen
import theme.NotesAppTheme

@Composable
fun App() {
    val preferenceManager: PreferenceManager = koinInject()
    NotesAppTheme {
        if (preferenceManager.getToken().isBlank()) {
            Navigator(AuthScreen())
        } else {
            Navigator(NotesScreen())
        }
    }
}