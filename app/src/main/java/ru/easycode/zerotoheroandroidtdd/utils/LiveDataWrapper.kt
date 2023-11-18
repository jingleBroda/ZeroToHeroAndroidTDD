package ru.easycode.zerotoheroandroidtdd.utils

import androidx.lifecycle.LiveData

interface LiveDataWrapper {
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>
}