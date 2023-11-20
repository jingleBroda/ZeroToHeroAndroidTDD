package ru.easycode.zerotoheroandroidtdd.uiState

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable {
    fun apply(button:Button, textView: TextView, progressBar: ProgressBar)

    object Base: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = true
            textView.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }

    object ShowProgress: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = false
            textView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }

    object ShowData: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = true
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }
}