package expect_actuals

import AppContext
import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import expect_actuals.Pref.NOTES_SETTINGS

actual fun getSettings(): Settings {
    val context = AppContext().context
    return SharedPreferencesSettings(
        context.getSharedPreferences(
            NOTES_SETTINGS,
            Context.MODE_PRIVATE
        )
    )
}

object Pref {
    const val NOTES_SETTINGS = "Notes_App_Kmp_Settings"
}