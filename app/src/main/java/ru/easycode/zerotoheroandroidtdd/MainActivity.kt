package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        with(binding) {
            titleTextView.visibility = savedInstanceState?.getInt(VISIBILITY_TEXT_KEY) ?: View.VISIBLE
            hideButton.setOnClickListener(this@MainActivity)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(VISIBILITY_TEXT_KEY, binding.titleTextView.visibility)
    }
    override fun onClick(v: View) {
        when(v.id) {
            R.id.hideButton -> binding.titleTextView.visibility = View.GONE
        }
    }

    companion object {
        private const val VISIBILITY_TEXT_KEY = "VISIBILITY_TEXT_KEY"
    }
}