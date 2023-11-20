package ru.easycode.zerotoheroandroidtdd.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.utils.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.utils.ViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding
    private var bundleWrapper: BundleWrapper.Mutable = BundleWrapper.Mutable.Base()
    private val viewModelFactory = ViewModelFactory()
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let { bundle->
            bundleWrapper = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(BUNDLE_KEY) as BundleWrapper.Mutable
            }
            else bundle.getSerializable(BUNDLE_KEY, BundleWrapper::class.java) as BundleWrapper.Mutable
            viewModel.restore(bundleWrapper)
        }
        with(binding) {
            viewModel.liveData.observe(this@MainActivity) { uiState->
                uiState.apply(
                    actionButton,
                    titleTextView,
                    progressBar
                )
                viewModel.save(bundleWrapper)
            }
            actionButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(BUNDLE_KEY, bundleWrapper)
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

    companion object {
        private const val BUNDLE_KEY = "BUNDLE_KEY"
    }
}