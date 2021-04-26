package com.example.mysamplemiddledev.di.component

import android.app.Application
import com.example.mysamplemiddledev.App
import com.example.mysamplemiddledev.di.builder.ActivityBuilder
import com.example.mysamplemiddledev.di.module.ContextModule
import com.example.mysamplemiddledev.di.module.NetworkModule
import com.example.mysamplemiddledev.di.module.StorageModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ContextModule::class,
        NetworkModule::class,
        StorageModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}