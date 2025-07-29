package com.example.pingall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pingall.adapters.PingsResultAdapter
import com.example.pingall.databinding.ActivityMainBinding
import com.example.pingall.viewmodel.PingViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding;

    private val viewModel: PingViewModel by viewModels()
    private lateinit var pingsAdapter: PingsResultAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        setupRecyclerView()
        setupAddButtonListener()
        observeViewModel()
    }

    fun setupRecyclerView(){
        pingsAdapter = PingsResultAdapter()
        binding.pingsRecyler.apply {
            adapter = pingsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    private fun setupAddButtonListener() {
        binding.button.setOnClickListener {
            val urlText = binding.editTextText.text.toString().trim()

            if (urlText.isNotBlank()) {
                viewModel.addUrlToPing(urlText)
                binding.editTextText.text.clear()
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pingResults.collect { updatedList ->
                    pingsAdapter.updateList(updatedList)
                }
            }
        }
    }
}