package com.example.mysamplemiddledev.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysamplemiddledev.model.State
import com.example.mysamplemiddledev.model.cat_facts.Fact
import com.example.mysamplemiddledev.model.colibri_example.Post
import com.example.mysamplemiddledev.model.habr_example.Result
import com.example.mysamplemiddledev.net.repository.colibri.ColibriRepository
import com.example.mysamplemiddledev.providers.NetworkFactoryProvider
import com.example.mysamplemiddledev.providers.RepositoriesProvider
import com.example.mysamplemiddledev.service.CatsFactService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyViewModel :
    ViewModel() {        // добавить базовое активити and showProgressBar
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val catsFactService = CatsFactService()

    //LiveData
    val usersFromGitHubLivaData = MutableLiveData<Result>()
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
}