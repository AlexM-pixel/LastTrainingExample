package com.example.mysamplemiddledev.utils

import com.chibatching.kotpref.KotprefModel

object MainPrefs : KotprefModel() {
    var firstStart: Boolean by booleanPref(default = false)
}