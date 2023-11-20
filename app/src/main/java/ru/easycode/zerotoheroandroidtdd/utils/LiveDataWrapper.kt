package ru.easycode.zerotoheroandroidtdd.utils

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.uiState.UiState

interface LiveDataWrapper {
    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base: LiveDataWrapper {
        private val liveEvent = SingleLiveEvent<UiState>()

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveEvent.value?.let {
                bundleWrapper.save(it)
            }
        }

        override fun update(value: UiState) {
            liveEvent.value = value
        }

        override fun liveData(): LiveData<UiState> = liveEvent
    }
}