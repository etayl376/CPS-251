package com.example.mytipc

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Declare views
    private lateinit var billAmount: EditText
    private lateinit var tipBtn: Button
    private lateinit var output: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        billAmount = findViewById(R.id.billAmount)
        tipBtn = findViewById(R.id.tipBtn)
        output = findViewById(R.id.output)

        // Set a click listener for the button
        tipBtn.setOnClickListener {
            validateAndCalculateTips()
        }

        // Add a TextWatcher to clear the output message when typing
        billAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    output.text = "" // Clear output when user starts typing
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    @SuppressLint("SetTextI18n")
    private fun validateAndCalculateTips() {
        val bill = billAmount.text.toString().trim() // Trim any whitespace

        if (bill.isEmpty()) {
            // Display message if the bill amount is not entered
            output.text = "YOU MUST ENTER A BILL AMOUNT"
            Toast.makeText(this, "Please enter a valid bill amount", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val billValue = bill.toDouble()

                // Calculate the tips for 10%, 15%, and 20%
                val tip10 = billValue * 1.10
                val tip15 = billValue * 1.15
                val tip20 = billValue * 1.20

                // Display the calculated tip amounts
                output.text = """
                    The tips are as follows:
                    10% = ${"%.2f".format(tip10)}
                    15% = ${"%.2f".format(tip15)}
                    20% = ${"%.2f".format(tip20)}
                """.trimIndent()
            } catch (e: NumberFormatException) {
                // Handle invalid number input
                output.text = "Please enter a valid number"
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
