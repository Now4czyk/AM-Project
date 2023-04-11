package com.kn.amproject.preferences

import android.content.Context

val PREFERENCE_NAME = "LanguagePreference"
val PREFERENCE_LANGUAGE = "Language"

class LanguagePreference(context: Context) {
    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getLanguage(): String? {
        return preference.getString(PREFERENCE_LANGUAGE, "en")
    }

    fun setLanguage(language: String) {
        val editor = preference.edit()
        editor.putString(PREFERENCE_LANGUAGE, language)
        editor.apply()
    }

}