package com.shashank.jetpackcomposeuidemo.presentation.state

import com.shashank.jetpackcomposeuidemo.domain.model.MobileAuthOtp

data class MobileOtpAuthViewModelState(
    val mobileAuthOtp: MobileAuthOtp? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean = false
)