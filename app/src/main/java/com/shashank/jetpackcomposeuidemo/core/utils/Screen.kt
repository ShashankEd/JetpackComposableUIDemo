package com.shashank.jetpackcomposeuidemo.core.utils

sealed class Screen(val route: String) {
    object Login: Screen("register")
    object Dashboard: Screen("employee")
    object Profile: Screen("login")
    object Splash: Screen("splash")
    object OtpLogin: Screen("otplogin")
    object OtpSubmit: Screen("otpsubmit")
}