package com.example.startingservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("process","processing!")
        intent?.let {
            CoroutineScope(Job() + Dispatchers.Default ).launch {
                val secs = it.getIntExtra("secs", 0);
                var secsLeft = secs
                repeat(secs) {
                    Log.d("timer", secsLeft.toString())
                    secsLeft--;
                    delay(1000)
                }
            }
        }
        stopSelf()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

}