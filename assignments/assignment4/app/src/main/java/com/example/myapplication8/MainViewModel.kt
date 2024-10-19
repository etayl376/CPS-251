package com.example.myapplication8

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Store the list of entered names
    private val nameList = mutableListOf<String>()

    // Add a name to the list
    fun addName(name: String) {
        nameList.add(name)
    }

    // Retrieve the list of names
    fun getNames(): List<String> {
        return nameList
    }
}
