package com.example.stopwatchproject

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.SystemClock

class StopWatchService : Service() {

    private val binder = LocalBinder()
    private var startTime = 0L
    private var elapsedTime = 0L
    private var running = false
    private val handler = Handler()

    // From professor
    private val updateRunnable = object : Runnable {
        override fun run() {
            if (running) {
                val timeNow = SystemClock.elapsedRealtime()
                elapsedTime = timeNow - startTime
                handler.postDelayed(this, 100)
            }
        }
    }

    inner class LocalBinder : Binder() {
        fun getService(): StopWatchService = this@StopWatchService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun start() {
        if (!running) {
            startTime = SystemClock.elapsedRealtime() - elapsedTime
            running = true
            handler.post(updateRunnable)
        }
    }

    fun pause() {
        if (running) {
            running = false
            handler.removeCallbacks(updateRunnable)
        }
    }

    fun reset() {
        running = false
        handler.removeCallbacks(updateRunnable)
        elapsedTime = 0L
    }

    fun getElapsedTime(): Long {
        return elapsedTime
    }
}
