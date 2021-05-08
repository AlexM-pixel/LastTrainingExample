package com.example.mysamplemiddledev.di.builder

import com.example.mysamplemiddledev.ui.fragments.BookmarksFragment
import com.example.mysamplemiddledev.ui.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentsBuilder {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBookmarksFragment(): BookmarksFragment
}