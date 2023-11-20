package ru.easycode.zerotoheroandroidtdd.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.utils.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.utils.ViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding
    private val viewModelFactory = ViewModelFactory()
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let { bundle->
            viewModel.restore(BundleWrapper.Base(bundle))
        }
        with(binding) {
            viewModel.liveData.observe(this@MainActivity) { uiState->
                uiState.apply(
                    actionButton,
                    titleTextView,
                    progressBar
                )
            }
            actionButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveData.removeObservers(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.actionButton -> viewModel.load()
        }
    }
}