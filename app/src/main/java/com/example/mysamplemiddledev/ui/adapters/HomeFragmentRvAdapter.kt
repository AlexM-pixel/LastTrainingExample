package com.example.mysamplemiddledev.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.ItemUserBinding
import com.example.mysamplemiddledev.model.habr_example.User
import com.squareup.picasso.Picasso

class HomeFragmentRvAdapter(listener: OnItemClickListener) :
    RecyclerView.Adapter<HomeFragmentRvAdapter.HomeHolder>() {
    private var usersList: MutableList<User>? = mutableListOf()
    var listener: OnItemClickListener? = null
    val picasso = Picasso.get()

    init {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val inflaterMy = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemUserBinding>(inflaterMy, R.layout.item_user, parent, false)
        return HomeHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(usersList?.get(position))
    }

    override fun getItemCount(): Int {
        return usersList?.size ?: 0

    }

    fun setUserList(list: List<User>) {
        usersList?.clear()
        usersList?.addAll(list)
        notifyDataSetChanged()
    }

    inner class HomeHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?) {
            binding.user = user
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener?.onItemClick(user!!)
            }
            picasso.load(user?.avatar_url)
                .into(binding.userImage)
        }

    }
}