package com.shashank.jetpackcomposeuidemo.presentation.view.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.DrawerMenuComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.OtpLoginComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.OtpSubmitComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.SplashComposable
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.FirebaseAuthViewModel
import com.shashank.jetpackcomposeuidemo.presentation.viewmodel.MobileOtpAuthViewModel
import javax.inject.Inject

@Composable
fun ParentNavComposable(context: Context, navController: NavHostController) {

    val firebaseAuthViewModel = hiltViewModel<FirebaseAuthViewModel>()

    val mobileAuthViewModel = hiltViewModel<MobileOtpAuthViewModel>()

    NavHost(navController =navController , startDestination = Screen.Splash.route ) {

        //nav for splash
        composable(route =  Screen.Splash.route) {
            SplashComposable(
                context = context,
                navHostController = navController,
                mobileAuthViewModel
            )
        }

        //nav graph for otp send and consume
        navigation(
            route = Screen.Setup.route,
            startDestination = Screen.Setup.OtpLogin.route) {

            composable(route = Screen.Setup.OtpLogin.route) {
                OtpLoginComposable(
                    context = context,
                    navHostController = navController,
                    firebaseAuthViewModel,
                    mobileAuthViewModel
                )
            }

            composable(route = Screen.Setup.OtpSubmit.route) {
                OtpSubmitComposable(
                    context = context,
                    navHostController = navController,
                    firebaseAuthViewModel,
                    mobileAuthViewModel
                )
            }
        }

        //nav graph for drawer layout
        composable(route = Screen.DrawerScreen.route) {
            DrawerMenuComposable(
                context = context,
                mobileAuthViewModel
            )
        }
    }

}