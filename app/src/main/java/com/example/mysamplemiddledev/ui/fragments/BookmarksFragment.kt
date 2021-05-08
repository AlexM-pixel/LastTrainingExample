package com.example.mysamplemiddledev.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.FragmentBookmarksBinding
import com.example.mysamplemiddledev.model.habr_example.ResponseUser
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.ui.adapters.bookmarksfragment_adapter.ExpendableRecyclerViewAdapter
import com.example.mysamplemiddledev.ui.adapters.bookmarksfragment_adapter.SavedUsersRvAdapter
import com.example.mysamplemiddledev.ui.adapters.homefragment_adaper.UsersListRvAdapter
import com.example.mysamplemiddledev.ui.base.BaseFragment
import com.example.mysamplemiddledev.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_bookmarks.view.*
import javax.inject.Inject

class BookmarksFragment : BaseFragment(),SavedUsersRvAdapter.OnClickSavedUser {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModelByGitHub: UserViewModel
    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var bookmarksAdapter: ExpendableRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_bookmarks, container, false)
        viewModelByGitHub = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel
        viewModelByGitHub.getUsers()
        viewModelByGitHub.usersLiveData.observe(viewLifecycleOwner, {
            bookmarksAdapter.setUserList(it)
        })
    }

    private fun initRecyclerView() {
        bookmarksAdapter = ExpendableRecyclerViewAdapter(this)
        binding.rvProgrammers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = bookmarksAdapter
        }
    }

    override fun getUserName(name: String) {
        showMessage(name)
    }

}