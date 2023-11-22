package ru.easycode.zerotoheroandroidtdd.presentation.utils

import android.os.Build
import android.os.Bundle

interface BundleWrapper {
    interface Save: BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore: BundleWrapper {
        fun restore(): UiState
    }

    interface Mutable: Save, Restore

    class Base(private val bundle: Bundle): Mutable {
        override fun save(uiState: UiState) =
            bundle.putSerializable(SAVE_STATE_KEY, uiState)

        override fun restore(): UiState =
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(SAVE_STATE_KEY) as UiState
            }
            else {
                bundle.getSerializable(SAVE_STATE_KEY, UiState::class.java) as UiState
            }
    }

    companion object {
        private const val SAVE_STATE_KEY = "SAVE_STATE_KEY"
    }
}