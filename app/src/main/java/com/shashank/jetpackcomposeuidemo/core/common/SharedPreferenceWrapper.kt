package com.shashank.jetpackcomposeuidemo.core.common

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class SharedPreferenceWrapper @Inject constructor(@ApplicationContext context : Context){
//    private val PREF_NAME = "otpauth"
//    private val PREF_NAME_MOBILE_NO = "mobile_no"
//    val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//
//    fun getStoredTag(): Long {
//        return prefs.getLong(PREF_NAME_MOBILE_NO, 0)
//    }
//    fun setStoredTag(mobileNumber: Long) {
//        prefs.edit().putLong(PREF_NAME_MOBILE_NO, mobileNumber).apply()
//    }
//}