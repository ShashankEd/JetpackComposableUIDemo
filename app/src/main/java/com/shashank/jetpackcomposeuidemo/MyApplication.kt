package com.shashank.jetpackcomposeuidemo

import android.app.Application
import com.shashank.jetpackcomposeuidemo.core.Boat
import dagger.Module
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class MyApplication: Application() {

    //below inject will construct boat object along with engine object and let us
    //call the method - we can do the same in the activity
//    @Inject
//    @Singleton
//    lateinit var boat: Boat

    override fun onCreate() {
        super.onCreate()

    }
}