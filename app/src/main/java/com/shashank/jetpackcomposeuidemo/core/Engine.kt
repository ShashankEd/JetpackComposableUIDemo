package com.shashank.jetpackcomposeuidemo.core

import android.util.Log
import javax.inject.Inject

class Engine @Inject constructor() {
    fun start() {
        Log.d("Hilt", "startEngine: called ")
    }

    fun stop() {
        Log.d("Hilt", "stopEngine: called ")
    }
}