package com.shashank.jetpackcomposeuidemo.domain.model

data class MobileAuthOtp(
    val success:Boolean,
    val message: String,
    val token: String?
)
