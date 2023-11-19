package ru.easycode.zerotoheroandroidtdd.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.presentation.uiState.UiState

interface LiveDataWrapper {
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base: LiveDataWrapper {
        private val mutableLiveData = MutableLiveData<UiState>()
        override fun update(value: UiState) {
            mutableLiveData.value = value
        }

        override fun liveData(): LiveData<UiState> = mutableLiveData
    }
}