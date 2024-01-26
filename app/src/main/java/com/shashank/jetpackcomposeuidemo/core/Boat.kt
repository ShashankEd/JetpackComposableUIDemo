package com.shashank.jetpackcomposeuidemo.core

import javax.inject.Inject

class Boat @Inject constructor(private val engine: Engine) { // Boat is dependent on engine object

    fun startBoat() {
        engine.start()
    }

    fun stopBoat() {
        engine.stop()
    }
}