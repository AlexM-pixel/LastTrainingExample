package com.example.mysamplemiddledev.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mysamplemiddledev.db.room.AppDatabase
import com.example.mysamplemiddledev.db.room.UserDao
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.model.cat_facts.Fact
import com.example.mysamplemiddledev.model.colibri_example.Post
import com.example.mysamplemiddledev.model.habr_example.ResponseResult
import com.example.mysamplemiddledev.model.habr_example.ResponseUser
import com.example.mysamplemiddledev.model.habr_example.User
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import com.example.mysamplemiddledev.providers.NetworkFactoryProvider
import com.example.mysamplemiddledev.providers.RepositoriesProvider
import com.example.mysamplemiddledev.service.CatsFactService
import com.example.mysamplemiddledev.service.UserGitHubService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val catsFactService = CatsFactService()
    private val userDao: UserDao = AppDatabase.getDatabase(application = application).userDao()
    private val listUsers = mutableListOf<User>()

    //LiveData
    val usersFromGitHubLivaData = MutableLiveData<List<User>>()
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
        listUsers.clear()
        stateLiveData.value = State.LOADING
        val apiService =
            NetworkFactoryProvider.provideHubrApiFactory()
        val habrRepository =
            RepositoriesProvider.provideHabrRepository(apiService)
        val dis = habrRepository.searchUsers("location:$city language:$language", "user")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.items }
            .flatMap { responseUser -> Observable.fromIterable(responseUser) }
            .subscribe({
                listUsers.add(
                    User(
                        login = it.login,
                        id = it.id,
                        language = language,
                        url = it.url,
                        html_url = it.html_url,
                        avatar_url = it.avatar_url,
                        isExpanded = false,
                        isSaved = false,
                        type = it.type,
                        score = it.score
                    )
                )
            }, {
                State.ERROR.stateDescription = it.message.toString()
                stateLiveData.value = State.ERROR
                Log.e("subscribe", "Get_error: ${it.message}")
            }, {
                usersFromGitHubLivaData.value = listUsers
                stateLiveData.value = State.LOADED
                Log.e("subscribe", "onComplete:  " + listUsers.size)
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
        return RepositoriesProvider.provideColibriRepository(networkApiService)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun insertUsers(list: List<Long>) {
        val usersList = mutableListOf<User>()
        val userRepository = RepositoriesProvider.provideGitHubRepository(userDao = userDao)
        val userGitHubService = UserGitHubService(gitHubRepository = userRepository)
        for (id in list) {
            listUsers.forEach {
                if (id == it.id) {
                    usersList.add(it)
                }
            }
        }
        val disposable = userGitHubService.insertUser(usersList)
            .subscribe({
                State.SAVED.stateDescription = "saved users: ${it.size}"
                stateLiveData.value = State.SAVED
            }, { error ->
                State.ERROR.stateDescription = error.message ?: "error"
                stateLiveData.value = State.ERROR
            })
        compositeDisposable.add(disposable)
    }
}
