package com.shashank.jetpackcomposeuidemo.presentation.view.composables

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
import com.shashank.jetpackcomposeuidemo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

@Composable
fun OtpLoginComposable(context: Context) {
    var mobileNumber by remember {
        mutableStateOf("")
    }

    var otp by remember {
        mutableStateOf("")
    }

    var sendClicked by remember {
        mutableStateOf(false)
    }

//    var showOTP by remember {
//        mutableStateOf(false)
//    }

    LaunchedEffect(sendClicked) {
        mobileNumber.let {
            if(sendClicked && mobileNumber.length == 10) {
                // call the API here which will send the OTP
                otp =""
                CoroutineScope(Dispatchers.IO).launch {
                    delay(2000)
                    withContext(Dispatchers.Main) {
                        repeat(4) {
                            otp += (1..9).random().toString()
                        }
                        sendClicked = false
                        Log.d("Jetpack", "Otp genrated = $otp")
                    }
                }
            } else {
                Toast.makeText(context, "Enter correct number", Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(otp) {
        if(otp.length == 4) {
//            showOTP = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
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
                        sendClicked = true
                    },
                painter = painterResource(id = R.drawable.send_otp),
                contentDescription = "send otp"
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            OtpComposable(context, otp) {
                //Otp filled, now take the user to the other screen
            }
        }
    }
}

@Composable
fun OtpComposable(context: Context, otp:String, onOtpSubmit: (String) -> Unit) {
    var typeOTP by remember {
        mutableStateOf(otp)
    }

    LaunchedEffect(typeOTP) {
        Log.d("Jetpack", "LaunchedEffect typeOTP = $typeOTP")
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(4) {
            index ->
            val number = when {
                index >= typeOTP.length -> ""
                else -> typeOTP[index].toString()
            }
            Log.d("Jetpack", "OtpComposable: number = $number , index = $index")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .background(Color.Blue)
                ) {
                    TextField(
                        value = number,
                        onValueChange = {
                            Log.d("Jetpack", "onValueChange: $it otp = $typeOTP")
                            typeOTP.plus(it)
                        },
                        modifier = Modifier.background(Color.White),
                        textStyle = MaterialTheme.typography.titleLarge,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

//                    Text(
//                        modifier = Modifier
//                            .padding(5.dp)
//                            .align(Alignment.Center),
//                        text = number,
//                        color = Color.White,
//                        style = MaterialTheme.typography.titleLarge,
//                    )
                }

            }
        }
    }
}