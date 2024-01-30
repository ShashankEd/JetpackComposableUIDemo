package com.shashank.jetpackcomposeuidemo.presentation.view.composables

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shashank.jetpackcomposeuidemo.R
import com.shashank.jetpackcomposeuidemo.core.common.JetpackProgressDialog
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.FirebaseAuthViewModel
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.MobileOtpAuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

@Composable
fun OtpLoginComposable(context: Context, navHostController: NavHostController, firebaseAuthViewModel:FirebaseAuthViewModel,
                       mobileAuthViewModel:MobileOtpAuthViewModel) {

    val sendOTPState = mobileAuthViewModel.mobileOtpAuth.collectAsState().value

    var mobileNumber by remember {
        mutableStateOf("")
    }

    var otp by remember {
        mutableStateOf("")
    }

    var sendClicked by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(sendClicked) {
        if(sendClicked) {
            sendClicked = false
            mobileNumber.let {
                if(mobileNumber.length == 10) {

//                    firebaseAuthViewModel.signWithEmailPass("test@gmail.com", "test1234")

//                    firebaseAuthViewModel.forceReCaptcha()
//                    //call the firebase's create user with phone
//                    firebaseAuthViewModel.createUserWithPhone("+91$mobileNumber", activity = context as Activity)
                    mobileAuthViewModel.sendOtp(mobileNumber.toLong())
                } else {
                    Toast.makeText(context, "Enter correct number", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    LaunchedEffect(sendOTPState) {
        if(sendOTPState.isLoading){
          isLoading = true
        } else {
            isLoading = false
            if(sendOTPState.mobileAuthOtp?.success == true) {
                //store the mobile number into the SP
                mobileAuthViewModel.storeMobileNoInSp(mobileNumber = mobileNumber.toLong())
                //take the user to otp consume screen
                navHostController.navigate(Screen.Setup.OtpSubmit.route)
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
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = Modifier
                .size(200.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            //number field and send button
            TextField(
                placeholder = { Text(text = "Enter Phone Number")},
                value = mobileNumber,
                onValueChange = {
                    mobileNumber = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
//                        sendClicked = true
                        //Take user to otp submit
                        sendClicked = true
                    },
                painter = painterResource(id = R.drawable.send_otp),
                contentDescription = "send otp"
            )
        }
    }
}