package ru.easycode.zerotoheroandroidtdd.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.retrofit.RetrofitInstance
import ru.easycode.zerotoheroandroidtdd.presentation.activity.MainViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    private val repository = Repository.Base(RetrofitInstance.create())
    private val liveDataWrapper = LiveDataWrapper.Base()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = MainViewModel(liveDataWrapper, repository)
        return viewModel as T
    }
}