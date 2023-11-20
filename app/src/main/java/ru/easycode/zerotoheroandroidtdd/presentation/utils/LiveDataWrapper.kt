package ru.easycode.zerotoheroandroidtdd.presentation.utils

import androidx.lifecycle.LiveData

interface LiveDataWrapper {
    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base: LiveDataWrapper {
        private val liveEvent = SingleLiveEvent<UiState>()

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveEvent.value?.let { uiState->
                bundleWrapper.save(uiState)
            }
        }

        override fun update(value: UiState) {
            liveEvent.value = value
        }

        override fun liveData(): LiveData<UiState> = liveEvent
    }
}