package com.sousa.data

import splitties.preferences.Preferences


class Prefs : Preferences("prefs") {

    var fio by stringPref("fio", "")

    var age by stringPref("age", "")

    var city by stringPref("city", "")

    var address by stringPref("address", "")

    var urlPhoto by stringPref("urlPhoto", "")

    var userId by stringPref("userId", "")

}

