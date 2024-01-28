package com.shashank.jetpackcomposeuidemo.presentation.view.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.DashboardComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.LoginComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.ProfileComposable

@Composable
fun NavigationComposable(context: Context, navController: NavHostController, onDrawerItemSelected: () -> Unit) {
    NavHost(navController =navController , startDestination = Screen.DrawerScreen.route) {

        //Nav graph for drawer components
        navigation(
            route = Screen.DrawerScreen.route,
            startDestination = Screen.DrawerScreen.Login.route) {

            composable(route = Screen.DrawerScreen.Login.route) {
                LoginComposable(
                    context = context,
                    onFormSubmit = {
                        Log.d("Jetpack", "Form Successful: ${it}")
                    },
                    navController = navController
                )
                onDrawerItemSelected()
            }

            composable(route = Screen.DrawerScreen.Dashboard.route) {
                DashboardComposable(
                    context = context,
                    onFormSubmit = {
                        Log.d("Jetpack", "Form Successful: ${it}")
                    },
                    navController = navController
                )
                onDrawerItemSelected()
            }
            composable(route = Screen.DrawerScreen.Profile.route) {
                ProfileComposable(
                    context = context,
                    onFormSubmit = {
                        Log.d("Jetpack", "Form Successful: ${it}")
                    },
                    navController = navController
                )
                onDrawerItemSelected()
            }
        }
    }
}