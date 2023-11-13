package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var deletedFlag = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        deletedFlag = savedInstanceState?.getBoolean(DELETE_TEXT_VIEW_KEY) ?: false
        if(deletedFlag) binding.rootLayout.removeView(binding.titleTextView)
        with(binding) {
            removeButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(DELETE_TEXT_VIEW_KEY, deletedFlag)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.removeButton-> {
                binding.rootLayout.removeView(binding.titleTextView)
                deletedFlag = true
            }
        }
    }

    companion object {
        private const val DELETE_TEXT_VIEW_KEY = "DELETE_TEXT_VIEW_KEY"
    }
}