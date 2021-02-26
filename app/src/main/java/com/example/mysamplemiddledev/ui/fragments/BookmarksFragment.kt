package com.example.mysamplemiddledev.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.FragmentBookmarksBinding
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.ui.adapters.HomeFragmentRvAdapter
import com.example.mysamplemiddledev.viewModel.MyViewModel
import com.example.mysamplemiddledev.viewModel.UserViewModel

class BookmarksFragment : Fragment(), HomeFragmentRvAdapter.OnItemClickListener {
    private lateinit var viewModelByGitHub: UserViewModel
    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var bookmarksAdapter: HomeFragmentRvAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelByGitHub =  ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_bookmarks, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
      //  binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel
        viewModelByGitHub.getUsers()
        viewModelByGitHub.usersLiveData.observe(this, {
            bookmarksAdapter.setUserList(it)
        })
    }

    private fun initRecyclerView() {
        bookmarksAdapter = HomeFragmentRvAdapter(listener = this)
        binding.rvProgrammers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarksAdapter
        }
    }

    override fun onItemClick(user: User) {
        TODO("Not yet implemented")
    }
}