package ru.easycode.zerotoheroandroidtdd.utils

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

interface UiState {
    fun apply(
        button: Button,
        textView: TextView,
        processBar: ProgressBar
    )

    object ShowProgress: UiState {
        override fun apply(button: Button, textView: TextView, processBar: ProgressBar) {
            button.isEnabled = false
            textView.visibility = View.INVISIBLE
            processBar.visibility = View.VISIBLE
        }
    }

    object ShowData: UiState {
        override fun apply(button: Button, textView: TextView, processBar: ProgressBar) {
            button.isEnabled = true
            textView.visibility = View.VISIBLE
            processBar.visibility = View.INVISIBLE
        }
    }
}