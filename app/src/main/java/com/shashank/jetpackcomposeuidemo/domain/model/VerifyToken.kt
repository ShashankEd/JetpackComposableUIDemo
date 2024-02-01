package com.shashank.jetpackcomposeuidemo.domain.model

import com.shashank.jetpackcomposeuidemo.data.dto.Data

data class VerifyToken(
    val `data`: Data,
    val success: Boolean
)
