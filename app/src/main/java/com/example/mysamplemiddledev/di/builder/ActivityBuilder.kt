package com.example.mysamplemiddledev.di.builder

import com.example.mysamplemiddledev.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentsBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}