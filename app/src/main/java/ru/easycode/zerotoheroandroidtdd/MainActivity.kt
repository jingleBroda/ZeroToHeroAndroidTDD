package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val incrementCount = Count.Base(2)
    private var number = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let {
            number = savedInstanceState.getString(INCREMENT_DATA_KEY, "0")
        }
        with(binding) {
            countTextView.text = number
            incrementButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INCREMENT_DATA_KEY, number)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.incrementButton -> {
                number = incrementCount.increment(binding.countTextView.text.toString())
                binding.countTextView.text = number
            }
        }
    }
    companion object {
        private const val INCREMENT_DATA_KEY = "INCREMENT_DATA_KEY"
    }
}