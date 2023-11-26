package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let {
            val restoreList = it.getStringArrayList(SAVE_LIST_KEY) ?: ArrayList()
            restoreList.forEach { inputText ->
                addTextView(inputText)
            }
        }
        with(binding) {
            actionButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textList = binding.contentLayout.children.map {
            (it as TextView ).text.toString()
        }.toList()
        outState.putStringArrayList(SAVE_LIST_KEY, ArrayList(textList))
    }

    private fun addTextView(text: String) {
        val textView = TextView(this)
        textView.text = text
        binding.contentLayout.addView(textView)
        binding.inputEditText.setText("")
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.actionButton -> {
                val inputText = binding.inputEditText.text.toString()
                addTextView(inputText)
            }
        }
    }

    companion object {
        private const val SAVE_LIST_KEY = "SAVE_LIST_KEY"
    }
}