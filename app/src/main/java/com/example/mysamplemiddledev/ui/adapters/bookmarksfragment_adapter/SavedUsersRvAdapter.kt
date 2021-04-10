package com.example.mysamplemiddledev.ui.adapters.bookmarksfragment_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.ItemSavedUsersBinding
import com.example.mysamplemiddledev.model.habr_example.User
import com.squareup.picasso.Picasso

class SavedUsersRvAdapter(listener: OnClickSavedUser) :
    RecyclerView.Adapter<SavedUsersRvAdapter.MySavedListHolder>() {
    private var usersList: MutableList<User>? = mutableListOf()
    private val picasso = Picasso.get()
    private var listener: OnClickSavedUser? = null

    init {
        this.listener = listener
    }

    interface OnClickSavedUser {
        fun getUserName(name: String)
    }

    inner class MySavedListHolder(val binding: ItemSavedUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?) {
            picasso.load(user?.avatar_url)
                .into(binding.imageCircle)
            binding.user = user
            //  binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener?.getUserName(user!!.login)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySavedListHolder {
        val myInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemSavedUsersBinding>(
                myInflater,
                R.layout.item_saved_users,
                parent,
                false
            )
        return MySavedListHolder(binding)
    }

    override fun onBindViewHolder(holder: MySavedListHolder, position: Int) {
        val user = usersList?.get(position)
        holder.bind(user = user)
    }

    override fun getItemCount(): Int {
        return usersList?.size ?: 0
    }

    fun setUserList(list: List<User>?) {
        usersList?.clear()
        if (list != null) {
            usersList?.addAll(list)
        }
        notifyDataSetChanged()
    }
}