package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.utils.Count
import ru.easycode.zerotoheroandroidtdd.utils.UiState

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val count = Count.Base(2, 4, 0)
    private var state:UiState = UiState.Min("0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let {
            state = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(SAVE_STATE_KEY) as UiState
            }
            else savedInstanceState.getSerializable(SAVE_STATE_KEY, UiState::class.java) as UiState
        }
        with(binding) {
            state.apply(countTextView, incrementButton, decrementButton)
            incrementButton.setOnClickListener(this@MainActivity)
            decrementButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVE_STATE_KEY, state)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.incrementButton -> {
                with(binding) {
                    state = count.increment(countTextView.text.toString())
                    state.apply(countTextView, incrementButton, decrementButton)
                }
            }

            R.id.decrementButton ->{
                with(binding) {
                    state = count.decrement(countTextView.text.toString())
                    state.apply(countTextView, incrementButton, decrementButton)
                }
            }
        }
    }

    companion object {
        private const val SAVE_STATE_KEY = "SAVE_STATE_KEY"
    }
}