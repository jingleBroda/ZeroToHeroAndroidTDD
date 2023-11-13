package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var textVisibility = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        textVisibility = savedInstanceState?.getBoolean(VISIBILITY_TEXT_KEY) ?: true
        with(binding) {
            if(textVisibility) titleTextView.visibility = View.VISIBLE
            else titleTextView.visibility = View.GONE

            hideButton.setOnClickListener(this@MainActivity)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(VISIBILITY_TEXT_KEY, textVisibility)
    }
    override fun onClick(v: View) {
        when(v.id) {
            R.id.hideButton -> {
                binding.titleTextView.visibility = View.GONE
                textVisibility = false
            }
        }
    }

    companion object {
        private const val VISIBILITY_TEXT_KEY = "VISIBILITY_TEXT_KEY"
    }
}