package com.shashank.jetpackcomposeuidemo.data.api

import com.shashank.jetpackcomposeuidemo.core.utils.SendOTPModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyOtpModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyTokenModel
import com.shashank.jetpackcomposeuidemo.data.dto.MobileAuthOtpDto
import com.shashank.jetpackcomposeuidemo.data.dto.VerifyTokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MobileOtpAuthApi {

    @POST("send-otp")
    suspend fun sendOTP(@Body sendOTPModel: SendOTPModel): MobileAuthOtpDto

    @POST("verify-otp")
    suspend fun verifyOtp(@Body verifyOtpModel: VerifyOtpModel): MobileAuthOtpDto

    @POST("verify-token")
    suspend fun verifyToken(@Body verifyTokenModel: VerifyTokenModel): VerifyTokenDto
}