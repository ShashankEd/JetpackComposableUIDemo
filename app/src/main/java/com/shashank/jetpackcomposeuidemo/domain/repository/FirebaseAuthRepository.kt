package com.shashank.jetpackcomposeuidemo.domain.repository

import android.app.Activity
import com.shashank.jetpackcomposeuidemo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {

    fun createUserWithPhone(
        phone:String,
        activity: Activity
    ) : Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>

    fun forceReCaptcha()

    fun signWithEmailPass(
        email:String,
        pass:String
    ): Flow<ResultState<String>>
}