package com.shashank.jetpackcomposeuidemo.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.jetpackcomposeuidemo.core.utils.Constants
import com.shashank.jetpackcomposeuidemo.core.utils.Resource
import com.shashank.jetpackcomposeuidemo.core.utils.SendOTPModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyOtpModel
import com.shashank.jetpackcomposeuidemo.domain.usecase.MobileOtpAuthUseCase
import com.shashank.jetpackcomposeuidemo.presentation.state.MobileOtpAuthViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MobileOtpAuthViewModel @Inject constructor(
    private val mobileOtpAuthUseCase: MobileOtpAuthUseCase) : ViewModel() {

    private val _mobileOtpAuth = MutableStateFlow(MobileOtpAuthViewModelState())
    val mobileOtpAuth: StateFlow<MobileOtpAuthViewModelState>
        get() = _mobileOtpAuth

    private val _verifyOtpAuth = MutableStateFlow(MobileOtpAuthViewModelState())
    val verifyOtpAuth: StateFlow<MobileOtpAuthViewModelState>
        get() = _verifyOtpAuth

    fun sendOtp(mobileNumber: Long) {
        val model = SendOTPModel(mobileNumber = mobileNumber)
        Log.d("Fast2sms", "sendOtp: called in viewmodel $mobileNumber")
        mobileOtpAuthUseCase.sendOtpUseCase(model).onEach {
            when(it) {
                is Resource.Error -> {
                    _mobileOtpAuth.value = MobileOtpAuthViewModelState().copy(errorMessage = it.status)
                }
                is Resource.Loading -> {
                    _mobileOtpAuth.value = MobileOtpAuthViewModelState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _mobileOtpAuth.value = MobileOtpAuthViewModelState().copy(mobileAuthOtp = it.mobileAuthOtp)
                }

                else -> {
                    _mobileOtpAuth.value = MobileOtpAuthViewModelState().copy(mobileAuthOtp = it.mobileAuthOtp)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun verifyOtp(mobileNumber: Long, otp: Long) {
        val model = VerifyOtpModel(mobileNumber = mobileNumber, otp = otp)

        mobileOtpAuthUseCase.verifyOtpUseCase(model).onEach {
            when(it) {
                is Resource.Error -> {
                    _verifyOtpAuth.value = MobileOtpAuthViewModelState().copy(errorMessage = it.status)
                }
                is Resource.Loading -> {
                    _verifyOtpAuth.value = MobileOtpAuthViewModelState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _verifyOtpAuth.value = MobileOtpAuthViewModelState().copy(mobileAuthOtp = it.mobileAuthOtp)
                }

                else -> {
                    _verifyOtpAuth.value = MobileOtpAuthViewModelState().copy(mobileAuthOtp = it.mobileAuthOtp)
                }
            }
        }.launchIn(viewModelScope)
    }

    //shared preference get and set
    fun storeMobileNoInSp(mobileNumber: Long) {
        mobileOtpAuthUseCase.storeMobileNoInSp(mobileNumber = mobileNumber)
    }

    fun getMobileNoFromSp(): Long {
        return mobileOtpAuthUseCase.getMobileNoFromSp()
    }
}