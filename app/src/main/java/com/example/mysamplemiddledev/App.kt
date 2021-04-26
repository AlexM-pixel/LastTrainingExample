package com.example.mysamplemiddledev

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.example.mysamplemiddledev.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

}