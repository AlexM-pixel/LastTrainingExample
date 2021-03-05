package com.example.mysamplemiddledev.ui.adapters.homefragment_adaper

import androidx.recyclerview.selection.ItemKeyProvider

class MyItemKeyProvider(private val adapter: UsersListRvAdapter) : ItemKeyProvider<Long>(SCOPE_CACHED) {
    override fun getKey(position: Int): Long = adapter.getItem(position)

    override fun getPosition(key: Long): Int {
        return adapter.getPosition(key)!!
    }

}