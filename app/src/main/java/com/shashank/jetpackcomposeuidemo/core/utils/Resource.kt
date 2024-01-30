package com.shashank.jetpackcomposeuidemo.core.utils

sealed class Resource<T>(val status: String? = null, val mobileAuthOtp: T? = null){
    class Success<T>(mobileAuthOtp: T?): Resource<T>(mobileAuthOtp = mobileAuthOtp)
    class Error<T>(status: String?): Resource<T>(status = status)
    class Loading<T>: Resource<T>()
}