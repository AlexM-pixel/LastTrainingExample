package com.example.mysamplemiddledev.ui.adapters.homefragment_adaper

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.databinding.ItemUserBinding
import com.example.mysamplemiddledev.model.habr_example.User
import com.squareup.picasso.Picasso

class UsersListRvAdapter(listener: OnItemClickListener) :
    RecyclerView.Adapter<UsersListRvAdapter.HomeHolder>() {
    private var usersList: MutableList<User>? = mutableListOf()
    var listener: OnItemClickListener? = null
    private val picasso = Picasso.get()
    var tracker: SelectionTracker<Long>? = null


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
        val user = usersList?.get(position)
        tracker?.let {
            holder.bind(user, it.isSelected(user?.id))
        }
    }

    override fun getItemCount(): Int {
        return usersList?.size ?: 0

    }

    fun setUserList(list: List<User>) {
        usersList?.clear()
        usersList?.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = usersList!![position].id
    fun getPosition(key: Long) = usersList?.indexOfFirst { it.id == key }

    inner class HomeHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?, isActivated: Boolean = false) {
            itemView.isActivated = isActivated
            binding.user = user
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener?.onItemClick(user!!)
            }
            picasso.load(user?.avatar_url)
                .into(binding.userImage)
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = usersList!![position].id
                override fun inSelectionHotspot(e: MotionEvent): Boolean = true
            }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}