package ru.easycode.zerotoheroandroidtdd.utils

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable {
    val text: String

    fun apply(button: Button, textView: TextView)

    class Base(override val text: String): UiState {
        override fun equals(other: Any?): Boolean {
            val local = (other as? UiState)?.text
            return if(local == null) false
            else text.equals(local)
        }

        override fun hashCode(): Int = text.hashCode()

        override fun apply(button: Button, textView: TextView) {
            textView.text = text
        }
    }

    class Max(override val text: String): UiState {
        override fun equals(other: Any?): Boolean {
            val local = (other as? UiState)?.text
            return if(local == null) false
            else text.equals(local)
        }

        override fun hashCode(): Int = text.hashCode()

        override fun apply(button: Button, textView: TextView) {
            button.isEnabled = false
            textView.text = text
        }
    }
}