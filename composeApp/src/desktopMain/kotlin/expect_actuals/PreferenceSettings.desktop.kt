package expect_actuals

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual fun getSettings(): Settings {
    val preferences = Preferences.userRoot()
    return PreferencesSettings(preferences)
}