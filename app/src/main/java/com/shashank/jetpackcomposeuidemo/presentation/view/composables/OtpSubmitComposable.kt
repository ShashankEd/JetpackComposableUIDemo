package com.shashank.jetpackcomposeuidemo.presentation.view.composables

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shashank.jetpackcomposeuidemo.core.common.JetpackButton
import com.shashank.jetpackcomposeuidemo.core.common.JetpackProgressDialog
import com.shashank.jetpackcomposeuidemo.core.common.JetpackText
import com.shashank.jetpackcomposeuidemo.core.common.OtpView
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.FirebaseAuthViewModel
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.MobileOtpAuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun OtpSubmitComposable(context: Context, navHostController: NavHostController,
                        firebaseAuthViewModel: FirebaseAuthViewModel,
                        mobileAuthViewModel: MobileOtpAuthViewModel) {
    val verifyOTPState = mobileAuthViewModel.verifyOtpAuth.collectAsState().value

    val verifyTokenState = mobileAuthViewModel.verifyToken.collectAsState().value


    var otp by remember {
        mutableStateOf("")
    }

    var otpSent by remember {
        mutableStateOf(false)
    }

    var isDialog by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

//    if(isDialog)
//        JetpackProgressDialog()

    LaunchedEffect(otp) {
        Log.d("Jetpack", "LaunchedEffect OTP changed: $otp")
    }

    LaunchedEffect(otpSent) {
        Log.d("Jetpack", "LaunchedEffect otpSent: $otpSent")
        if(otpSent) {
            otpSent = false
            isLoading = true
            //navigate to drawer
            if(otp.length == 6) {
//                firebaseAuthViewModel.signWithCredential(otp)
                Log.d("Fast2sms", "mobile in SP: ${mobileAuthViewModel.getMobileNoFromSp()}")
                if(!mobileAuthViewModel.getMobileNoFromSp().toString().equals("0")) {
                    mobileAuthViewModel.verifyOtp(mobileAuthViewModel.getMobileNoFromSp(), otp.toLong())
                } else {
                    Toast.makeText(context,"Mobile no not found in the SP", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(context,"Enter a valid OTP!!", Toast.LENGTH_LONG).show()

            }
        }
    }

    LaunchedEffect(verifyOTPState) {
        if(verifyOTPState.isLoading){
            isLoading = true
        } else {
            isLoading = false
            if(verifyOTPState.mobileAuthOtp?.success == true) {
                // now we'll need to verify the token- call the API
                verifyOTPState.mobileAuthOtp.token?.let {
                    mobileAuthViewModel.storeAuthTokenInSp(authToken = it)
                }
                verifyOTPState.mobileAuthOtp.token?.let {
                    mobileAuthViewModel.verifyToken(mobileAuthViewModel.getMobileNoFromSp(),
                        it
                    )
                }
            }
        }
    }

    LaunchedEffect(verifyTokenState) {
        if(verifyTokenState.isLoading){
            isLoading = true
        } else {
            isLoading = false
            if(verifyTokenState.verifyToken?.success == true) {
                //take the user to the dashboard
                navHostController.navigate(Screen.DrawerScreen.route)
            } else if(verifyTokenState.verifyToken?.success == false) {
                navHostController.navigate(Screen.Setup.route)
            }
        }
    }

    if(isLoading) {
        JetpackProgressDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        JetpackText(
            text = "Please Enter your OTP",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        OtpView(
            otpText = otp
        ) {
            otp = it
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        JetpackButton(
            modifier = Modifier,
            buttonText = "Submit",
            onButtonClick = {
                otpSent = true
            }
        )
    }
}