package com.shashank.jetpackcomposeuidemo.view

import android.os.Bundle
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
import com.shashank.jetpackcomposeuidemo.view.composables.DrawerMenuComposable
import com.shashank.jetpackcomposeuidemo.view.ui.theme.JetpackComposeUIDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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