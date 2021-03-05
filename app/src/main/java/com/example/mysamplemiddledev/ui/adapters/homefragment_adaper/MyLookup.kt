package com.example.mysamplemiddledev.ui.adapters.homefragment_adaper

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamplemiddledev.model.habr_example.User

class MyLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {

    override fun getItemDetails(e: MotionEvent) :ItemDetails<Long>? = recyclerView.findChildViewUnder(e.x, e.y)
        ?.let {
            (recyclerView.getChildViewHolder(it) as? UsersListRvAdapter.HomeHolder)?.getItemDetails()
        }

}