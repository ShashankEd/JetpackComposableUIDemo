package com.shashank.jetpackcomposeuidemo.data.mapper

import com.shashank.jetpackcomposeuidemo.data.dto.VerifyTokenDto
import com.shashank.jetpackcomposeuidemo.domain.model.VerifyToken

fun VerifyTokenDto.toDomainVerifyTokenDto() : VerifyToken {
    return VerifyToken(data = data, success = success)
}
