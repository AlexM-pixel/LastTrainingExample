package com.example.mysamplemiddledev.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.FragmentHomeBinding
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.model.habr_example.ResponseUser
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.ui.adapters.homefragment_adaper.ActionModeController
import com.example.mysamplemiddledev.ui.adapters.homefragment_adaper.MyItemKeyProvider
import com.example.mysamplemiddledev.ui.adapters.homefragment_adaper.MyLookup
import com.example.mysamplemiddledev.ui.adapters.homefragment_adaper.UsersListRvAdapter
import com.example.mysamplemiddledev.ui.base.BaseFragment
import com.example.mysamplemiddledev.viewModel.MyViewModel

class HomeFragment : BaseFragment(), UsersListRvAdapter.OnItemClickListener {
    private lateinit var viewModel: MyViewModel
    private lateinit var homeAdapter: UsersListRvAdapter
    private lateinit var binding: FragmentHomeBinding
    private var myTracker: SelectionTracker<Long>? = null
    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        initRecyclerView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.getFacts()
        viewModel.factLiveData.observe(viewLifecycleOwner, {
            binding.textViewFactCats.text = it.last().text
        })
        viewModel.usersFromGitHubLivaData.observe(viewLifecycleOwner, {
            showMessage("всего колличество прогеров:  ${it.size}")
            homeAdapter.setUserList(it)
        })
        viewModel.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                State.LOADING -> {
                    showView(binding.progressBar)
                    hideKeyboard()
                }
                State.LOADED -> hideView(binding.progressBar)
                State.ERROR -> showMessage(it.stateDescription)
                State.SAVED -> showMessage(it.stateDescription)
                else -> hideView(binding.progressBar)
            }
        })

    }

    private fun initRecyclerView() {
        homeAdapter = UsersListRvAdapter(listener = this)
        binding.rvResponseUserList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
        setTracker()
    }

    private fun setTracker() {
        myTracker = SelectionTracker.Builder<Long>(
            "muSelection", binding.rvResponseUserList,
            MyItemKeyProvider(adapter = homeAdapter),
            MyLookup(recyclerView = binding.rvResponseUserList), StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()
        homeAdapter.tracker = myTracker
        myTracker!!.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    if (myTracker!!.hasSelection() && actionMode == null) {
                        actionMode =
                            activity!!.startActionMode(ActionModeController(myTracker!!, viewModel))
                        setSelectedTitle(myTracker!!.selection.size())
                    } else if (!myTracker!!.hasSelection()) {
                        actionMode?.finish()
                        actionMode = null
                    } else {
                        setSelectedTitle(myTracker!!.selection.size())
                    }
                }
            })
    }

    private fun setSelectedTitle(selected: Int) {
        val title = resources.getString(R.string.action_mode_selected)
        actionMode?.title = "$title: $selected"
    }

    override fun onItemClick(user: User) {
        Log.e("subscribe", "Get_User: ${user.login}")
    }

}