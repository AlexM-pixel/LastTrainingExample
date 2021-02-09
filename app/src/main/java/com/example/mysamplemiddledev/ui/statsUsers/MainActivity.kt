package com.example.mysamplemiddledev.ui.statsUsers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mysamplemiddledev.R
import com.example.mysamplemiddledev.model.Post
import com.example.mysamplemiddledev.providers.NetworkFactoryProvider
import com.example.mysamplemiddledev.providers.SearchRepositoryProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiService =
            NetworkFactoryProvider.provideHubrApiFactory()                                 // здесь приходит объект ApiService
        val networkApiService =
            NetworkFactoryProvider.provideColibriApiFactory()                         // здесь приходит объект networkApiService
        val habrRepository =
            SearchRepositoryProviders.provideHabrRepository(apiService)                // здесь использую апи из примера на хабре
        val colibriRepository =
            SearchRepositoryProviders.provideColibriRepository(networkApiService)      // десь использую апи из примера на devColibri
        habr_button.setOnClickListener {
            habrRepository.searchUsers("Omsk", "java")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e("subscribe", "Get_succses:  " + it.total_count)
                }, {
                    Log.e("subscribe", "Get_error: ${it.message}")
                })
        }
        colibri_button.setOnClickListener {
            colibriRepository.searchUser(11)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e("subscribe", "Get_succses:  $it.body \n ${it.id}")

                }, {
                    Log.e("subscribe", "Get_error: ${it.message!!}")
                })
        }
        colibri_button_post.setOnClickListener {
            colibriRepository.sendPost(Post(1, 2, "MyPost", "Body from my Post"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e("subscribe", "Post_succses:  ${it.title}, ${it.body} \n ${it.id}")
                }, {
                    Log.e("subscribe", "Post_error: ${it.message!!}")
                })
        }

    }
}