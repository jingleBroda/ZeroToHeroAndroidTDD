package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let {
            val saveList = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                (it.getSerializable(SAVE_LIST_KEY) as SaveStringList).saveList
            }
            else (it.getSerializable(SAVE_LIST_KEY, SaveStringList::class.java) as SaveStringList).saveList
            list = saveList.toMutableList()
            restoreList()
        }
        with(binding) {
            actionButton.setOnClickListener(this@MainActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVE_LIST_KEY, SaveStringList(list))
    }

    private fun restoreList() {
        for(index in list.indices) {
            val textView = TextView(this)
            textView.text = list[index]
            binding.contentLayout.addView(textView, index)
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.actionButton -> {
                val textView = TextView(this)
                textView.text = binding.inputEditText.text
                list.add(textView.text.toString())
                binding.contentLayout.addView(textView, list.lastIndex)
                binding.inputEditText.setText("")
            }
        }
    }

    companion object {
        private const val SAVE_LIST_KEY = "SAVE_LIST_KEY"
    }
}