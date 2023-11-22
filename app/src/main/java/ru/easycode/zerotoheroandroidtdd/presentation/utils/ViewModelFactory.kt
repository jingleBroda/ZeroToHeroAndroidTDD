package ru.easycode.zerotoheroandroidtdd.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.RetrofitInstance
import ru.easycode.zerotoheroandroidtdd.presentation.activity.MainViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    private val liveDataWrapper = LiveDataWrapper.Base()
    private val repository = Repository.Base(
        RetrofitInstance.create(),
        URL_CONST
    )
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = MainViewModel(liveDataWrapper, repository)
        return viewModel as T
    }

    companion object {
        private const val URL_CONST = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}