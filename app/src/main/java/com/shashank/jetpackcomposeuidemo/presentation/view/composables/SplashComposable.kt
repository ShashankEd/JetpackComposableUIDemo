package com.shashank.jetpackcomposeuidemo.presentation.view.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.MobileOtpAuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SplashComposable(context: Context, navHostController: NavHostController,mobileAuthViewModel:MobileOtpAuthViewModel) {

    var isLoggedin by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = "checklogin" ) {
        Log.d("Fast2sms", "checklogin effect called ${mobileAuthViewModel.getLoginStatus() }")
        if(mobileAuthViewModel.getLoginStatus() == true) {
            isLoggedin = true
        }
    }

    LaunchedEffect(key1 = "test", block = {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                if(isLoggedin) {
                    navHostController.navigate(Screen.DrawerScreen.route)
                } else {
                    navHostController.navigate(Screen.Setup.route)

                }
            }
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Splash Screen")
    }
}