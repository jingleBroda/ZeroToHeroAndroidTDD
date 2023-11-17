package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState {
    fun apply(button: Button, textView: TextView, progressBar: ProgressBar)

    object Base: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = true
            textView.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }

    object DownloadStart: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = false
            textView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }

    object DownloadEnd: UiState {
        override fun apply(button: Button, textView: TextView, progressBar: ProgressBar) {
            button.isEnabled = true
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }
}