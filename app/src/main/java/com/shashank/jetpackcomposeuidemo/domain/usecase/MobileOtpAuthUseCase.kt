package com.shashank.jetpackcomposeuidemo.domain.usecase

import android.content.SharedPreferences
import com.shashank.jetpackcomposeuidemo.core.utils.Constants
import com.shashank.jetpackcomposeuidemo.core.utils.SendOTPModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyOtpModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyTokenModel
import com.shashank.jetpackcomposeuidemo.domain.repository.MobileOtpAuthRepository
import javax.inject.Inject

class MobileOtpAuthUseCase @Inject constructor(
    private val mobileOtpAuthRepository: MobileOtpAuthRepository,
    private val sharedPreferences: SharedPreferences) {

    fun sendOtpUseCase(sendOTPModel: SendOTPModel) = mobileOtpAuthRepository.sendOTP(sendOTPModel)

    fun verifyOtpUseCase(verifyOtpModel: VerifyOtpModel) = mobileOtpAuthRepository.verifyOTP(verifyOtpModel)

    //Shared preference use cases--
    fun storeMobileNoInSp(mobileNumber: Long) {
        sharedPreferences.edit().putLong(Constants.PREF_NAME_MOBILE_NO,mobileNumber).apply()
    }

    fun getMobileNoFromSp(): Long {
        return sharedPreferences.getLong(Constants.PREF_NAME_MOBILE_NO,0)
    }

    fun verifyTokenCase(verifyTokenModel: VerifyTokenModel) = mobileOtpAuthRepository.verifyToken(verifyTokenModel)

    //Shared preference use cases--
    fun storeAuthTokenInSp(authToken: String) {
        sharedPreferences.edit().putString(Constants.PREF_NAME_AUTH_TOKEN,authToken).apply()
    }

    fun getAuthTokenFromSp(): String? {
        return sharedPreferences.getString(Constants.PREF_NAME_AUTH_TOKEN,"")
    }

    fun clearSP() {
        sharedPreferences.edit().clear().apply()
    }


}