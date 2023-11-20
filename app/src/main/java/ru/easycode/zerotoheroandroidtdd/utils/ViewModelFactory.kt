package ru.easycode.zerotoheroandroidtdd.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.activity.MainViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    private val repository = Repository.Base()
    private val liveDataWrapper = LiveDataWrapper.Base()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = MainViewModel(liveDataWrapper, repository)
        return viewModel as T
    }
}