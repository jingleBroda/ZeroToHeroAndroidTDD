package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var state: State = State.NotRemove
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        savedInstanceState?.let {
            state = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(DELETE_TEXT_VIEW_KEY) as State
            }
            else savedInstanceState.getSerializable(DELETE_TEXT_VIEW_KEY, State::class.java) as State
        }
        with(binding) {
            state.apply(rootLayout, titleTextView, binding.removeButton)
            removeButton.setOnClickListener(this@MainActivity)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(DELETE_TEXT_VIEW_KEY, state)
    }
    override fun onClick(v: View) {
        when(v.id) {
            R.id.removeButton-> {
                state = State.Remove
                state.apply(binding.rootLayout, binding.titleTextView, binding.removeButton)
            }
        }
    }
    companion object {
        private const val DELETE_TEXT_VIEW_KEY = "DELETE_TEXT_VIEW_KEY"
    }
    private interface State: Serializable {
        fun apply(layout: LinearLayout, textView: TextView, button: Button)
        object NotRemove: State {
            override fun apply(layout: LinearLayout, textView: TextView, button: Button) = Unit
        }
        object Remove: State {
            override fun apply(layout: LinearLayout, textView: TextView, button: Button) {
                layout.removeView(textView)
                button.isEnabled = false
            }
        }
    }
}