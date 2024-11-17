package com.example.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val nameList = mutableListOf<NameItem>()

    fun addName(name: String, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val sleepTime = Random.nextLong(1, 11) * 1000
            delay(sleepTime)
            nameList.add(NameItem(name, sleepTime))
            callback() // Notify adapter of changes
        }
    }
}
