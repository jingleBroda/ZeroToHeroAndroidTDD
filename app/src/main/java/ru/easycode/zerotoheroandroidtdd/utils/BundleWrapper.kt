package ru.easycode.zerotoheroandroidtdd.utils

import ru.easycode.zerotoheroandroidtdd.uiState.UiState
import java.io.Serializable

interface BundleWrapper: Serializable {
    interface Save: BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore: BundleWrapper {
        fun restore(): UiState
    }

    interface Mutable: Save, Restore {
        class Base: Mutable {
            private var uiState: UiState = UiState.Base

            override fun save(uiState: UiState) {
                this.uiState = uiState
            }

            override fun restore(): UiState = uiState
        }
    }
}