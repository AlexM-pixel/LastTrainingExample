package com.example.mysamplemiddledev.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.ActivityMainBinding
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.ui.adapters.ViewPagerAdapter
import com.example.mysamplemiddledev.ui.base.BaseActivity
import com.example.mysamplemiddledev.viewModel.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.view.*

const val NUM_PAGES = 2

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val pagerAdapter = ViewPagerAdapter(this)
        binding.vp.apply {
            adapter = pagerAdapter
        }
        val tabLayout = binding.root.tab_layout
        TabLayoutMediator(tabLayout, binding.vp) { tab, position ->
            tab.text = "fragment: $position"
        }.attach()
    }
}