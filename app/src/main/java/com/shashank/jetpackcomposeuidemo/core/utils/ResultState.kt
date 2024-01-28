package com.shashank.jetpackcomposeuidemo.core.utils

sealed class ResultState<out T> {

    data class Success<out R>(val data:R) : ResultState<R>()
    data class Failure(val msg:String) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()

}