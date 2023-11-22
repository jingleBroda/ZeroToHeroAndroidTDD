package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.domain.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.presentation.utils.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.presentation.utils.UiState

interface LoadResult {
    fun show(updateLiveData: LiveDataWrapper.Update)

    data class Success(val data: SimpleResponse): LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update){
            updateLiveData.update(UiState.ShowData(data.text))
        }
    }

    data class Error(val noConnection: Boolean): LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            if(noConnection) updateLiveData.update(UiState.ShowData(NO_INTERNET_TEXT))
            else updateLiveData.update(UiState.ShowData(OTHER_BAD_REQUEST_TEXT))
        }
    }

    companion object {
        private const val NO_INTERNET_TEXT = "No internet connection"
        private const val OTHER_BAD_REQUEST_TEXT = "Something went wrong"
    }
}