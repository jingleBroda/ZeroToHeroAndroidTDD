package ru.easycode.zerotoheroandroidtdd.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.presentation.uiState.UiState
import ru.easycode.zerotoheroandroidtdd.utils.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.utils.Repository

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
): ViewModel() {
    val liveData: LiveData<UiState> by lazy {
        liveDataWrapper.liveData()
    }

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            repository.load()
            withContext(Dispatchers.Main) {
                liveDataWrapper.update(UiState.ShowData)
            }
        }
    }
}