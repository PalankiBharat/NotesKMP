import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppContext : KoinComponent {
    val context: Context by inject()
}