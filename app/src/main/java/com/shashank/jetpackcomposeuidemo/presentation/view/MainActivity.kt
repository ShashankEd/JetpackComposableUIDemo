package com.shashank.jetpackcomposeuidemo.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.shashank.jetpackcomposeuidemo.MyApplication
import com.shashank.jetpackcomposeuidemo.core.Boat
import com.shashank.jetpackcomposeuidemo.core.Engine
import com.shashank.jetpackcomposeuidemo.di.AppModule
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.DrawerMenuComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.ui.theme.JetpackComposeUIDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(){

    //it tells the Hilt to inject the below object to this activity
    @Inject
    lateinit var boat: Boat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boat.startBoat()
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                boat.stopBoat()
            }
        }
//        Log.d("Hilt", "onCreate: ${Boat(Engine()).startBoat()}")
        setContent {
            JetpackComposeUIDemoTheme {
                // A surface container using the 'background' color from the theme
                val formSubmitted by remember {
                    mutableStateOf(false)
                }
                val navController= rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        DrawerMenuComposable(context = this@MainActivity, navController)
                    }
                }
            }
        }
    }
}