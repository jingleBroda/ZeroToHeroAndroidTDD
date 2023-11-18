package ru.easycode.zerotoheroandroidtdd.activity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.utils.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.utils.Repository
import ru.easycode.zerotoheroandroidtdd.utils.UiState

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
): ViewModel() {
    val liveData: LiveData<UiState> by lazy {
        liveDataWrapper.liveData()
    }

    fun load(){
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            repository.load()
            withContext(Dispatchers.Main) {
                liveDataWrapper.update(UiState.ShowData)
            }
        }
    }
}