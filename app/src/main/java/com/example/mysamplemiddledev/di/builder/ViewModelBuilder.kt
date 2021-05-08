package com.example.mysamplemiddledev.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysamplemiddledev.di.qualifier.ViewModelKey
import com.example.mysamplemiddledev.viewModel.MyViewModel
import com.example.mysamplemiddledev.viewModel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [(RepositoryBuilder::class),
        (ServiceBuilder::class)
    ]
)
abstract class ViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    abstract fun bindMyViewModel(myViewModel: MyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}