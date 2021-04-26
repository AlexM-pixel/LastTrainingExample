package com.example.mysamplemiddledev.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mysamplemiddledev.ui.NUM_PAGES
import com.example.mysamplemiddledev.ui.fragments.BookmarksFragment
import com.example.mysamplemiddledev.ui.fragments.HomeFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return BookmarksFragment()
        }
        return HomeFragment()
    }


}