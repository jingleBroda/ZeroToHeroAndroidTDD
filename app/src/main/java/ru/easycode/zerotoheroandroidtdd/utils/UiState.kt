package ru.easycode.zerotoheroandroidtdd.utils

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable {
    fun apply(textView:TextView, incrementButton: Button, decrementButton: Button)

    data class Base(val text: String): UiState {
        override fun apply(textView: TextView, incrementButton: Button, decrementButton: Button) {
            textView.text = text
            incrementButton.isEnabled = true
            decrementButton.isEnabled = true
        }
    }

    data class Max(val text: String): UiState {
        override fun apply(textView: TextView, incrementButton: Button, decrementButton: Button) {
            textView.text = text
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
        }
    }

    data class Min(val text: String): UiState {
        override fun apply(textView: TextView, incrementButton: Button, decrementButton: Button) {
            textView.text = text
            incrementButton.isEnabled = true
            decrementButton.isEnabled = false
        }
    }
}