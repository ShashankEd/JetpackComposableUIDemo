package com.shashank.jetpackcomposeuidemo.core.utils

sealed class Screen(val route: String) {

    object Splash: Screen("splash")

    //keep the nested routes under a hood
    object Setup: Screen("setup") {
        object OtpLogin: Screen("otplogin")
        object OtpSubmit: Screen("otpsubmit")
    }

    object DrawerScreen: Screen("drawerstack") {
        object Login: Screen("register")
        object Dashboard: Screen("employee")
        object Profile: Screen("login")
        object Logout: Screen("logout")
    }
}