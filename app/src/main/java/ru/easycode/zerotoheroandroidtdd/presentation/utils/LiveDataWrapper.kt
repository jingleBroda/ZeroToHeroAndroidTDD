package ru.easycode.zerotoheroandroidtdd.presentation.utils

import androidx.lifecycle.LiveData

interface LiveDataWrapper {
    fun liveData(): LiveData<UiState>

    interface Save: LiveDataWrapper {
        fun save(bundleWrapper: BundleWrapper.Save)
    }
    interface Update: LiveDataWrapper {
        fun update(value: UiState)
    }
    interface Mutable: Save, Update

    class Base: Mutable {
        private val singleLiveEvent = SingleLiveEvent<UiState>()

        override fun liveData(): LiveData<UiState> = singleLiveEvent

        override fun save(bundleWrapper: BundleWrapper.Save) {
            singleLiveEvent.value?.let {
                bundleWrapper.save(it)
            }
        }

        override fun update(value: UiState) {
            singleLiveEvent.value = value
        }
    }
}