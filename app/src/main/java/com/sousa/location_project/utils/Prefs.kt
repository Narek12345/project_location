package com.sousa.location_project.utils

import splitties.preferences.Preferences

class Prefs : Preferences("mainPrefs") {

    var isDarkTheme by boolPref("isDarkTheme", false)
}