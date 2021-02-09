package com.example.mysamplemiddledev.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysamplemiddledev.model.Result

class MyViewModel: ViewModel() {
val getUsers= MutableLiveData<Result>()

}