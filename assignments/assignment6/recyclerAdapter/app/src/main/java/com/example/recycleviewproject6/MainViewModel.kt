package com.example.recycleviewproject6

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val data = Data()

    // Hold the randomized list of items
    private var randomItems: List<Triple<String, String, Int>>? = null

    // Function to get or generate the random items
    fun getRandomItems(): List<Triple<String, String, Int>> {
        if (randomItems == null) {
            // Generate the random list if it's not already created
            randomItems = mutableListOf<Triple<String, String, Int>>().apply {
                val indices = (data.titles.indices).toList().shuffled()

                for (index in indices) {
                    val title = data.titles.random()
                    val detail = data.details.random()
                    val image = data.images.random() // Randomly pick an image
                    add(Triple(title, detail, image))
                }
            }
        }
        return randomItems!!
    }
}
