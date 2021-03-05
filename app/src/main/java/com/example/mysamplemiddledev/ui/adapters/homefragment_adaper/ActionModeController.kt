package com.example.mysamplemiddledev.ui.adapters.homefragment_adaper

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.selection.SelectionTracker
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.viewModel.MyViewModel

class ActionModeController(
    private val tracker: SelectionTracker<Long>,
    val viewModel: MyViewModel
) : ActionMode.Callback {

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = true

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_clear -> {
                mode.finish()
                return true
            }
            R.id.action_save -> {
                val idItemsList = tracker.selection.toMutableList()
                viewModel.insertUsers(idItemsList)
                mode.finish()
                return true
            }
            else -> return false
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        tracker.clearSelection()
    }
}