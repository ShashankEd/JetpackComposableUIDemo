package com.shashank.jetpackcomposeuidemo.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.recaptcha.Recaptcha
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.shashank.jetpackcomposeuidemo.presentation.view.navigation.ParentNavComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.ui.theme.JetpackComposeUIDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appCheck()
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
                    ParentNavComposable(
                        context = this@MainActivity,
                        navController = navController
                    )
                }
            }
        }
    }

    //Not sure whether to use this or not
    private suspend fun setRecaptcha() {

        Recaptcha.getClient(
            application = application,
            siteKey = "6Lcyz14pAAAAAMSigUVV9eNN-auGmj1QJHdu8dv9",
            timeout = 20000L)
            .onSuccess {
                token ->
                Log.d("Auth", "setRecaptcha: success $token")

            }
            .onFailure {
                Log.d("Auth", "setRecaptcha: failure ${it.message}")
            }
    }

    private fun appCheck() {
        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
    }

}