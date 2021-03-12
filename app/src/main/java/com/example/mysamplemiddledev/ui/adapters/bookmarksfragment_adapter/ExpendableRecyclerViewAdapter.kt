package com.example.mysamplemiddledev.ui.adapters.bookmarksfragment_adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.ItemUserExpandBinding
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.utils.animations.Animations
import kotlinx.android.synthetic.main.item_user_expand.view.*


class ExpendableRecyclerViewAdapter() :
    RecyclerView.Adapter<ExpendableRecyclerViewAdapter.ViewHolder>() {
    private var titleList: MutableList<User>? = mutableListOf()
    private var usersList: MutableList<User>? = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpendableRecyclerViewAdapter.ViewHolder {
        val inflaterMy = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemUserExpandBinding>(
            inflaterMy, R.layout.item_user_expand,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpendableRecyclerViewAdapter.ViewHolder, position: Int) {
        val user = titleList?.get(position)
        holder.bind(user = user!!)
    }

    override fun getItemCount(): Int {
        return titleList?.size ?: 0
    }

    fun setUserList(list: List<User>) {
        usersList?.clear()
        titleList?.clear()
        titleList?.addAll(list.distinctBy { it.language })
        Log.e("subscribe", "setUserListTitle: ${titleList?.size}")
        usersList?.addAll(list)
        Log.e("subscribe", "setUserList: ${usersList?.size}")
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: ItemUserExpandBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val savedAdapter = SavedUsersRvAdapter()
        init {
            itemView.rvSavedProgrammers.apply {
                layoutManager = LinearLayoutManager(itemView.root.context)
                adapter = savedAdapter
            }
        }

        fun bind(user: User) {
            itemView.name_language.text = user.language
            savedAdapter.setUserList(getUserList(user))
            itemView.viewMoreBtn.setOnClickListener { v ->
                val show: Boolean =
                    toggleLayout(user.isExpanded, v, itemView.layoutExpand)
                user.isExpanded = show
            }
        }
    }

    private fun getUserList(user: User): List<User>? {
        return usersList?.filter { it.language == user.language }
    }

    private fun toggleLayout(isExpanded: Boolean, v: View, layoutExpand: LinearLayout): Boolean {
        Animations.toggleArrow(v, isExpanded)
        if (!isExpanded) {
            Animations.expand(layoutExpand)
            return true
        } else {
            Animations.collapse(layoutExpand)
            return false
        }
    }
}