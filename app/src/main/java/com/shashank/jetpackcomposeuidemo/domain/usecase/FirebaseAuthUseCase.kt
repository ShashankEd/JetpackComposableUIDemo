package com.shashank.jetpackcomposeuidemo.domain.usecase

import android.app.Activity
import com.shashank.jetpackcomposeuidemo.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class FirebaseAuthUseCase @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository) {

    fun forceReCaptcha() = firebaseAuthRepository.forceReCaptcha()

    fun createUserWithPhone(phone:String, activity: Activity) = firebaseAuthRepository.createUserWithPhone(phone, activity)

    fun signWithCredential(otp: String) = firebaseAuthRepository.signWithCredential(otp)

    fun signWithEmailPass(email: String, pass:String) = firebaseAuthRepository.signWithEmailPass(email, pass)
}