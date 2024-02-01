package com.shashank.jetpackcomposeuidemo.data.mapper

import com.shashank.jetpackcomposeuidemo.data.dto.MobileAuthOtpDto
import com.shashank.jetpackcomposeuidemo.domain.model.MobileAuthOtp

fun MobileAuthOtpDto.toDomainMobileAuthOtp() : MobileAuthOtp {
    return MobileAuthOtp(success,message,token)
}

