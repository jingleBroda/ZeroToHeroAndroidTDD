package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        with(binding) {
            changeButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.changeButton -> {
                binding.titleTextView.text = "I am an Android Developer!"
            }
        }
    }
}