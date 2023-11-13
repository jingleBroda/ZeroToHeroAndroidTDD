package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        with(binding) {
            binding.titleTextView.text = savedInstanceState?.getString(SAVE_TEXT_KEY) ?: START_TEXT
            changeButton.setOnClickListener(this@MainActivity)
        }
    }
    override fun onClick(v: View) {
        when(v.id) {
            R.id.changeButton -> binding.titleTextView.text = BUTTON_CLICK_TEXT
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_TEXT_KEY, binding.titleTextView.text.toString())
    }
    companion object {
        private const val START_TEXT = "Hello World!"
        private const val BUTTON_CLICK_TEXT = "I am an Android Developer!"
        private const val SAVE_TEXT_KEY = "SAVE_TEXT_KEY"
    }
}