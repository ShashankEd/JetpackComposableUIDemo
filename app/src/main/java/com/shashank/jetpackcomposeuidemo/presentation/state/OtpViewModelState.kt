package com.shashank.jetpackcomposeuidemo.presentation.state

import com.shashank.jetpackcomposeuidemo.domain.model.VerifyToken

data class OtpViewModelState(
    val verifyToken: VerifyToken? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean = false
)
