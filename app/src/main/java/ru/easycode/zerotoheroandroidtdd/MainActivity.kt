package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var state:UiState = UiState.Base
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        with(binding) {
            state.apply(
                actionButton,
                titleTextView,
                progressBar,
            )
            actionButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.actionButton -> {
                //интерфейс загрузки пошел
                state = UiState.DownloadStart
                state.apply(
                    binding.actionButton,
                    binding.titleTextView,
                    binding.progressBar,
                )
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(3500)
                    withContext(Dispatchers.Main) {
                        state = UiState.DownloadEnd
                        state.apply(
                            binding.actionButton,
                            binding.titleTextView,
                            binding.progressBar,
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val KEY = "KEY"
    }
}