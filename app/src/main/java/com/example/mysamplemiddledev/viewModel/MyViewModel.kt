package com.example.mysamplemiddledev.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysamplemiddledev.db.room.AppDatabase
import com.example.mysamplemiddledev.db.room.UserDao
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.model.cat_facts.Fact
import com.example.mysamplemiddledev.model.colibri_example.Post
import com.example.mysamplemiddledev.model.habr_example.ResponseResult
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import com.example.mysamplemiddledev.providers.NetworkFactoryProvider
import com.example.mysamplemiddledev.providers.RepositoriesProvider
import com.example.mysamplemiddledev.service.CatsFactService
import com.example.mysamplemiddledev.service.UserGitHubService
import com.example.mysamplemiddledev.ui.adapters.HomeFragmentRvAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val catsFactService = CatsFactService()
    private val userDao: UserDao = AppDatabase.getDatabase(application = application).userDao()

    //LiveData
    val usersFromGitHubLivaData = MutableLiveData<ResponseResult>()
    val factLiveData = MutableLiveData<List<Fact>>()
    val stateLiveData = MutableLiveData<State>()

    //variables for query
    var city = ""
    var language = ""

    fun getFacts() {
        val disposable = catsFactService.getFactsCat()
            .subscribe({ response ->
                stateLiveData.value = State.LOADED
                factLiveData.value = response
                Log.e("subscribe", "Get_success:  " + response.get(0).text)
            }, { error ->
                Log.e("subscribe", "Get_error: ${error.message}")
            })
        compositeDisposable.add(disposable)
    }

    fun getUsersFromGitHub() {
        stateLiveData.value = State.LOADING
        val apiService =
            NetworkFactoryProvider.provideHubrApiFactory()
        val habrRepository =
            RepositoriesProvider.provideHabrRepository(apiService)
        val dis = habrRepository.searchUsers("location:$city language:$language", "user")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                usersFromGitHubLivaData.value = it
                stateLiveData.value = State.LOADED
                Log.e("subscribe", "Get_success:  " + it.total_count)
            }, {
                stateLiveData.value = State.ERROR
                Log.e("subscribe", "Get_error: ${it.message}")
            })
        compositeDisposable.add(dis)
    }

    fun getPostFromColibri() {
        stateLiveData.value = State.LOADING
        val dis = getRepository().searchUser(11)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                stateLiveData.value = State.LOADED
                Log.e("subscribe", "Get_success:  $it \n ${it.id}")
            }, {
                stateLiveData.value = State.ERROR
                Log.e("subscribe", "Get_error: ${it.message!!}")
            }, {
            })
        compositeDisposable.add(dis)
    }

    fun postToColibri() {
        stateLiveData.value = State.LOADING
        val dis = getRepository().sendPost(Post(1, 2, "MyPost", "Body from my Post"))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                stateLiveData.value = State.LOADED
                Log.e("subscribe", "Post_success:  ${it.title}, ${it.body} \n ${it.id}")
            }, {
                stateLiveData.value = State.ERROR
                Log.e("subscribe", "Post_error: ${it.message!!}")
            })
        compositeDisposable.add(dis)
    }

    fun getRepository(): ColibriRepository {
        val networkApiService =
            NetworkFactoryProvider.provideColibriApiFactory()                         // здесь приходит объект networkApiService
        val colibriRepository =
            RepositoriesProvider.provideColibriRepository(networkApiService)
        return colibriRepository
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun insertUser(user: User) {
        val userRepository = RepositoriesProvider.provideGitHubRepository(userDao = userDao)
        val userGitHubService = UserGitHubService(gitHubRepository = userRepository)
        val disposable = userGitHubService.insertUser(user)
            .subscribe({
                Log.e("subscribe", "Get_success: $it")
            }, { error ->
                Log.e("subscribe", "Get_error: ${error.message}")
            })
        compositeDisposable.add(disposable)

    }
}