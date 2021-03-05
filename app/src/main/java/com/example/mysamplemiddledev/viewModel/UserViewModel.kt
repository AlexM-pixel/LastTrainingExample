package com.example.mysamplemiddledev.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mysamplemiddledev.db.repository.UserFromGitHubRepository
import com.example.mysamplemiddledev.db.room.AppDatabase
import com.example.mysamplemiddledev.db.room.UserDao
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.providers.RepositoriesProvider
import com.example.mysamplemiddledev.service.UserGitHubService
import com.example.mysamplemiddledev.ui.adapters.homefragment_adaper.UsersListRvAdapter
import io.reactivex.disposables.CompositeDisposable

class UserViewModel(application: Application) : AndroidViewModel(application),
    UsersListRvAdapter.OnItemClickListener {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val userDao: UserDao = AppDatabase.getDatabase(application).userDao()
    private val userGitHubService: UserGitHubService
    private val userRepository: UserFromGitHubRepository
    val usersLiveData = MutableLiveData<List<User>>()

    init {
        userRepository = RepositoriesProvider.provideGitHubRepository(userDao = userDao)
        userGitHubService = UserGitHubService(gitHubRepository = userRepository)
    }

    override fun onItemClick(user: User) {

    }

    fun getUsers() {
        val disposable = userGitHubService.getListUsers()
            .subscribe({
                usersLiveData.value = it
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