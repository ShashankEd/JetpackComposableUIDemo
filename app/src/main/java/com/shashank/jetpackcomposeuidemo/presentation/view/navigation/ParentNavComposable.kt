package com.shashank.jetpackcomposeuidemo.presentation.view.navigation

import android.content.Context
import androidx.compose.runtime.Composable
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
import javax.inject.Inject

@Composable
fun ParentNavComposable(context: Context, navController: NavHostController) {

    val firebaseAuthViewModel = hiltViewModel<FirebaseAuthViewModel>()

    NavHost(navController =navController , startDestination = Screen.Splash.route ) {

        //nav for splash
        composable(route =  Screen.Splash.route) {
            SplashComposable(
                context = context,
                navHostController = navController
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
                    firebaseAuthViewModel
                )
            }

            composable(route = Screen.Setup.OtpSubmit.route) {
                OtpSubmitComposable(
                    context = context,
                    navHostController = navController,
                    firebaseAuthViewModel
                )
            }
        }

        //nav graph for drawer layout
        composable(route = Screen.DrawerScreen.route) {
            DrawerMenuComposable(
                context = context
            )
        }
    }

}