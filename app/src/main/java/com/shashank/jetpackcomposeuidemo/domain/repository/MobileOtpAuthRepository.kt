package com.shashank.jetpackcomposeuidemo.domain.repository

import com.shashank.jetpackcomposeuidemo.core.utils.Resource
import com.shashank.jetpackcomposeuidemo.core.utils.SendOTPModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyOtpModel
import com.shashank.jetpackcomposeuidemo.domain.model.MobileAuthOtp
import kotlinx.coroutines.flow.Flow

interface MobileOtpAuthRepository {

    fun sendOTP(sendOTPModel: SendOTPModel): Flow<Resource<MobileAuthOtp>>

    fun verifyOTP(verifyOtpModel: VerifyOtpModel): Flow<Resource<MobileAuthOtp>>

}