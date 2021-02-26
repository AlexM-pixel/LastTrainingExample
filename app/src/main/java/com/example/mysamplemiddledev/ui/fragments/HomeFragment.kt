package com.example.mysamplemiddledev.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.FragmentHomeBinding
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.ui.adapters.HomeFragmentRvAdapter
import com.example.mysamplemiddledev.ui.base.BaseFragment
import com.example.mysamplemiddledev.viewModel.MyViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeFragmentRvAdapter.OnItemClickListener {
    private lateinit var viewModel: MyViewModel
    private lateinit var homeAdapter: HomeFragmentRvAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
      //  binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.getFacts()
        viewModel.factLiveData.observe(this, {
            text_view_factCats.text = it.last().text
        })
        viewModel.usersFromGitHubLivaData.observe(this, {
            showMessage("всего колличество прогеров:  ${it.total_count}")
            homeAdapter.setUserList(it.items)
        })
        viewModel.stateLiveData.observe(this, {
            when (it) {
                State.LOADING -> {
                    showView(progressBar)
                    hideKeyboard()
                }
                State.LOADED -> hideView(progressBar)
                State.ERROR -> showMessage(it.name)
                else -> hideView(progressBar)
            }
        })

    }

    private fun initRecyclerView() {
        homeAdapter = HomeFragmentRvAdapter(listener = this)
        binding.rvResponseResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }

    override fun onItemClick(user: User) {
        showMessage("added")
        viewModel.insertUser(user)
    }

}