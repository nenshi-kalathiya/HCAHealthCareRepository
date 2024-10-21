package com.example.hcahealthcaretask.view.customviews

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.hcahealthcaretask.R
import com.example.hcahealthcaretask.databinding.ViewSearchBarBinding

class CustomSearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ViewSearchBarBinding

    var onSearchListener: ((String) -> Unit)? = null
    var onClearListener: (() -> Unit)? = null

    init {
        // Inflate layout
        val view = LayoutInflater.from(context).inflate(R.layout.view_search_bar, this, true)
        binding = ViewSearchBarBinding.bind(view)

        // Set up listeners
        initListeners()
    }

    private fun initListeners() {
        // Listen for text changes to show/hide clear button
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.clearIcon.visibility = if (s.isNullOrEmpty()) GONE else VISIBLE
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Handle clear icon click
        binding.clearIcon.setOnClickListener {
            binding.searchEditText.text.clear()
            onClearListener?.invoke()
        }

        // Handle search action (keyboard "Search" button pressed)
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchListener?.invoke(binding.searchEditText.text.toString())
                true
            } else {
                false
            }
        }
    }

    fun setText(text: String) {
        binding.searchEditText.setText(text)
    }

    fun getText(): String {
        return binding.searchEditText.text.toString()
    }

    fun setHint(hint: String) {
        binding.searchEditText.hint = hint
    }

    // method to allow fragment to add its own TextWatcher
    fun addTextChangedListener(textWatcher: TextWatcher) {
        binding.searchEditText.addTextChangedListener(textWatcher)
    }

    // pass edit search text for listen to API call
    fun textEditSearch() : String = binding.searchEditText.text.toString()
}