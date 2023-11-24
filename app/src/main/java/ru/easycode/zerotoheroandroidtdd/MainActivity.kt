package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        with(binding) {
            inputEditText.addTextChangedListener(object : CustomTexWatcher {
                override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    println(charSequence)
                    actionButton.isEnabled = charSequence.toString() == "min"
                }
            })
            actionButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.actionButton ->
                binding.titleTextView.text = binding.inputEditText.text.apply {
                    binding.inputEditText.setText("")
                }
        }
    }
}