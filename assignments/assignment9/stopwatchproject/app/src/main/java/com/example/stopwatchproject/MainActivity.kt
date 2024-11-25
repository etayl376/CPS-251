package com.example.stopwatchproject

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatchproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var stopwatchService: StopWatchService? = null
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bind to the stopwatch service
        val intent = Intent(this, StopWatchService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)

        // Start button
        binding.startButton.setOnClickListener {
            stopwatchService?.start()
        }

        // Pause button
        binding.pauseButton.setOnClickListener {
            stopwatchService?.pause()
        }

        // Reset button
        binding.resetButton.setOnClickListener {
            stopwatchService?.reset()
            binding.timeTextView.text = "00:00:00"
        }

        updateElapsedTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as StopWatchService.LocalBinder
            stopwatchService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }
    // From professor
    private fun updateElapsedTime() {
        binding.timeTextView.postDelayed({
            if (isBound && stopwatchService != null) {
                val elapsedTime = stopwatchService?.getElapsedTime() ?: 0L
                val seconds = (elapsedTime / 1000) % 60
                val minutes = (elapsedTime / (1000 * 60)) % 60
                val hours = (elapsedTime / (1000 * 60 * 60)) % 24
                binding.timeTextView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }
            updateElapsedTime()
        }, 1000)
    }
}
