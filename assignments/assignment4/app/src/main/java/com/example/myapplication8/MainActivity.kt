package com.example.myapplication8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declare View Binding
    private lateinit var binding: ActivityMainBinding

    // Declare ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge UI
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adjust for system window insets (edge-to-edge padding)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle button click to add a name
        binding.button.setOnClickListener {
            val name = binding.editTextName.text.toString()

            if (name.isBlank()) {
                // Show a Toast message and update the TextView if no name is entered
                Toast.makeText(this, "No Name Entered", Toast.LENGTH_SHORT).show()
                binding.displayNames.text = "No Name Entered"
            } else {
                // Add the entered name to the ViewModel and update the display
                viewModel.addName(name)
                displayNames()
                binding.editTextName.text.clear() // Clear the input field
            }
        }

        // Display names on screen recreation (e.g., on rotation)
        displayNames()
    }

    // Helper function to display names from the ViewModel
    private fun displayNames() {
        val names = viewModel.getNames()
        if (names.isEmpty()) {
            binding.displayNames.text = "No names to display"
        } else {
            binding.displayNames.text = names.joinToString("\n")
        }
    }
}
