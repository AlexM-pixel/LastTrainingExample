package com.example.mysamplemiddledev.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.ActivityMainBinding
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.ui.base.BaseActivity
import com.example.mysamplemiddledev.viewModel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private val viewModel =
        ViewModelProvider.AndroidViewModelFactory(this.application).create(MyViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showView(progressBar)
        binding.viewModel = viewModel
        viewModel.getFacts()
        viewModel.factLiveData.observe(this, {
            text_view_factCats.text = it.get(0).text.plus("\n").plus(it.last().text)
        })
        viewModel.usersFromGitHubLivaData.observe(this, {
            showMessage("всего колличество прогеров:  ${it.total_count}")
        })
        viewModel.stateLiveData.observe(this, {
            when (it) {
                State.LOADING -> {
                    showView(progressBar)
                    hideKeyboard(binding.root)
                }
                State.LOADED -> hideView(progressBar)
                State.ERROR -> showMessage(it.name)
                else -> hideView(progressBar)
            }
        })
    }
}