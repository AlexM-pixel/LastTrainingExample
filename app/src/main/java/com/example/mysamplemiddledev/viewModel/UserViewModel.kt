package com.example.mysamplemiddledev.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.service.UserGitHubService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserViewModel @Inject constructor(
    application: Application,
    private val userGitHubService: UserGitHubService
) : AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    val usersLiveData = MutableLiveData<List<User>>()

    fun getUsers() {
        val disposable = userGitHubService.getListUsers()
            .subscribe({
                usersLiveData.value = it
                Log.e("subscribe", "listusers: ${it.size}")
            }, { error ->
                Log.e("subscribe", "Get_error: ${error.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}