package ru.easycode.zerotoheroandroidtdd.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.presentation.utils.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.presentation.utils.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.presentation.utils.UiState

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val repository: Repository
): ViewModel() {
    val liveData by lazy {
        liveDataWrapper.liveData()
    }

    fun load(){
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.load()
            withContext(Dispatchers.Main) {
                result.show(liveDataWrapper)
            }
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) =
        liveDataWrapper.save(bundleWrapper)

    fun restore(bundleWrapper: BundleWrapper.Restore){
        liveDataWrapper.update(bundleWrapper.restore())
    }
}