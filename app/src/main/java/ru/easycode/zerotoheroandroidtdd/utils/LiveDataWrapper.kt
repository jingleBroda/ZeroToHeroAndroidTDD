package ru.easycode.zerotoheroandroidtdd.utils

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.uiState.UiState
import java.lang.Exception

interface LiveDataWrapper {
    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base: LiveDataWrapper {
        private val liveEvent = SingleLiveEvent<UiState>()

        override fun save(bundleWrapper: BundleWrapper.Save) {
            try {
                bundleWrapper.save(liveEvent.value!!)
            }
            catch(e:Error) {
                throw Exception(e)
            }
        }

        override fun update(value: UiState) {
            liveEvent.value = value
        }

        override fun liveData(): LiveData<UiState> = liveEvent
    }
}