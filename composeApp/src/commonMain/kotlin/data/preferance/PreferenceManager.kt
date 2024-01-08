package data.preferance

import com.russhwolf.settings.Settings

class PreferenceManager(val settings: Settings) {

    fun saveToken(token: String) {
        settings.putString(TOKEN, token)
    }

    fun getToken(): String {
        return settings.getString(TOKEN, "")
    }


    companion object {
        const val TOKEN = "token"
    }
}