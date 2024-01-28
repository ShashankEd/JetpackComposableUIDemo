package com.shashank.jetpackcomposeuidemo.data.firebaseauth.repository

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.shashank.jetpackcomposeuidemo.core.utils.ResultState
import com.shashank.jetpackcomposeuidemo.domain.repository.FirebaseAuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): FirebaseAuthRepository {

    private lateinit var mVerificationCode:String
    private lateinit var resendToken:ForceResendingToken

    override fun createUserWithPhone(phone: String, activity: Activity): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        val onVerificationCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("Auth", "onVerificationCompleted: $credential")
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.d("Auth", "onVerificationFailed: $exception")
                trySend(ResultState.Failure(exception.toString()))
            }

            override fun onCodeSent(verificationCode: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationCode, token)

                trySend(ResultState.Success("Otp Sent Successfully"))
                Log.d("Auth", "onCodeSent: $verificationCode")
                mVerificationCode =  verificationCode
                //if it fails, then use this to resend token
                resendToken = token

            }
        }

        val optionsAuth = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(onVerificationCallback)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(optionsAuth)

        awaitClose{
            close()
        }
    }

    override fun signWithCredential(otp: String): Flow<ResultState<String>>  = callbackFlow {
        trySend(ResultState.Loading)

        val credential = PhoneAuthProvider.getCredential(mVerificationCode, otp)

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    trySend(ResultState.Success("Otp Verified"))
                } else {
                    trySend(ResultState.Failure(it.toString()))
                }
            }
        awaitClose {
            close()
        }
    }

    override fun forceReCaptcha() {
       firebaseAuth.firebaseAuthSettings.forceRecaptchaFlowForTesting(true)
    }

    override fun signWithEmailPass(email: String, pass: String): Flow<ResultState<String>> = callbackFlow {

        firebaseAuth.signInWithEmailAndPassword(email,pass)
           .addOnCompleteListener{
               trySend(ResultState.Success("Email login complete"))
           }
           .addOnSuccessListener {
               trySend(ResultState.Success("Email login success"))
           }
           .addOnFailureListener{
               trySend(ResultState.Failure("Email login failure $it"))
           }
        awaitClose {
            close()
        }
    }

}