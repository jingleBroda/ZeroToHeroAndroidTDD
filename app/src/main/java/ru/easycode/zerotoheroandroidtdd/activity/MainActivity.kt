package ru.easycode.zerotoheroandroidtdd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.activity.viewModel.MainViewModel
import ru.easycode.zerotoheroandroidtdd.utils.ViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModelFactory = ViewModelFactory()
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        with(binding) {
            actionButton.setOnClickListener(this@MainActivity)
            viewModel.liveData.observe(this@MainActivity) {
                it.apply(
                    actionButton,
                    titleTextView,
                    progressBar
                )
            }
        }
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