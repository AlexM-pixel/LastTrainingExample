package com.example.mysamplemiddledev.di.module

import android.app.Application
import android.content.Context
import com.example.mysamplemiddledev.di.builder.ViewModelBuilder
import com.example.mysamplemiddledev.di.builder.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelBuilder::class
    ]
)
class ContextModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}