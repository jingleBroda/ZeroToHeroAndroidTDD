package ru.easycode.zerotoheroandroidtdd.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.activity.viewModel.MainViewModel
import ru.easycode.zerotoheroandroidtdd.activity.viewModel.utils.PresentationLiveData
import ru.easycode.zerotoheroandroidtdd.activity.viewModel.utils.PresentationRepository

class ViewModelFactory: ViewModelProvider.Factory {
    private val repository = PresentationRepository()
    private val presentationLiveData = PresentationLiveData()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = MainViewModel(presentationLiveData, repository)
        return viewModel as T
    }
}