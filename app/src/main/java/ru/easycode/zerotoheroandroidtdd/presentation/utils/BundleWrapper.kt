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

    class Base(private val bundle:Bundle): Mutable {

        override fun save(uiState: UiState) {
            bundle.putSerializable(BUNDLE_KEY, uiState)
        }

        override fun restore(): UiState =
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(BUNDLE_KEY) as UiState
            }
            else {
                bundle.getSerializable(BUNDLE_KEY, UiState::class.java) as UiState
            }
    }

    companion object {
        private const val BUNDLE_KEY = "BUNDLE_KEY"
    }
}