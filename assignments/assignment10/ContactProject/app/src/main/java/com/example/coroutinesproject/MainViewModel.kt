package com.example.coroutinesproject

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val namesList = mutableListOf<String>()

    fun addName(name: String) {
        namesList.add(name)
    }
}
