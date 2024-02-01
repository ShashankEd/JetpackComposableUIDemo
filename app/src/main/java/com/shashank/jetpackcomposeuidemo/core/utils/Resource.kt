package com.shashank.jetpackcomposeuidemo.core.utils

sealed class Resource<T>(val status: String? = null, val model: T? = null){
    class Success<T>(model: T?): Resource<T>(model = model)
    class Error<T>(status: String?): Resource<T>(status = status)
    class Loading<T>: Resource<T>()
}