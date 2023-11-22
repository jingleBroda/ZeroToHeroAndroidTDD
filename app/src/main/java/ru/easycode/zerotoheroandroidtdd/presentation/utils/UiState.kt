package ru.easycode.zerotoheroandroidtdd.presentation.utils

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable {
    fun apply(button: Button, textView: TextView, progressBar: ProgressBar)
    object ShowProgress: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = false
            textView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }

    data class ShowData(val text: String): UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = true
            textView.text = text
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }
}