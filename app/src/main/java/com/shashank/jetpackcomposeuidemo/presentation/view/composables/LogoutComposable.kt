package com.shashank.jetpackcomposeuidemo.presentation.view.composables

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.shashank.jetpackcomposeuidemo.core.common.JetpackButton
import com.shashank.jetpackcomposeuidemo.core.common.JetpackProgressDialog
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.FirebaseAuthViewModel
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.MobileOtpAuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.math.log

@Composable
fun LogoutComposable(context: Context,
                     navHostController: NavHostController,
                     mobileAuthViewModel: MobileOtpAuthViewModel,
                     onFormSubmit: (JSONObject) -> Unit) {

    var logoutClicked by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(logoutClicked) {
        if(logoutClicked) {
            CoroutineScope(Dispatchers.IO).launch {
                delay(2000)
                System.exit(0)
            }
        }
    }

    if(logoutClicked) {
        JetpackProgressDialog()
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        JetpackButton(onButtonClick = {
            mobileAuthViewModel.storeLoginStatus(false)
            mobileAuthViewModel.clearSP()
            logoutClicked = true

        }, buttonText = "Logout")
    }
}