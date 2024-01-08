package expect_actuals

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun getSettings(): Settings {
    return NSUserDefaultsSettings(NSUserDefaults())
}