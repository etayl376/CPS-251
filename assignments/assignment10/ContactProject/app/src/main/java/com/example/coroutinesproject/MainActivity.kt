package com.example.coroutinesproject

import android.os.Bundle
import androidx.activity.
viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinesproject.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var nameAdapter: NameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        nameAdapter = NameAdapter(mainViewModel.namesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = nameAdapter

        // Restore the list on rotation
        nameAdapter.notifyDataSetChanged()

        // Add button click listener
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isNotBlank()) {
                binding.etName.text.clear()
                addNameWithDelay(name)
            }
        }
    }

    private fun addNameWithDelay(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val delayTime = (1000..10000).random().toLong()
            withContext(Dispatchers.IO) {
                delay(delayTime)
            }
            val message = "The name is $name and the delay was $delayTime milliseconds"
            mainViewModel.addName(message)
            nameAdapter.notifyDataSetChanged() // Update the adapter
        }
    }
}
