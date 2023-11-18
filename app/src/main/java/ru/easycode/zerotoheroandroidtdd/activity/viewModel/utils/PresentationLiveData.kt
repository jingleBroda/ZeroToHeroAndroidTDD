package ru.easycode.zerotoheroandroidtdd.activity.viewModel.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.utils.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.utils.UiState

class PresentationLiveData: LiveDataWrapper {
    private val actualCallsList = MutableLiveData<UiState>()

    override fun update(value: UiState) {
        actualCallsList.value = value
    }

    override fun liveData(): LiveData<UiState> = actualCallsList
}