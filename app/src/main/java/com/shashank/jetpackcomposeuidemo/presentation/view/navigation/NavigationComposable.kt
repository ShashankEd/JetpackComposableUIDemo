package com.shashank.jetpackcomposeuidemo.presentation.view.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.DashboardComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.LoginComposable
import com.shashank.jetpackcomposeuidemo.presentation.view.composables.ProfileComposable
import org.json.JSONObject

@Composable
fun NavigationComposable(context: Context, navController: NavHostController, onDrawerItemSelected: () -> Unit) {
    NavHost(navController =navController , startDestination = Screen.Login.route ) {

        composable(route = Screen.Login.route) {
            LoginComposable(
                context = context,
                onFormSubmit = {
                    Log.d("Jetpack", "Form Successful: ${it}")
                },
                navController = navController
            )
            onDrawerItemSelected()
        }

        composable(route = Screen.Dashboard.route) {
            DashboardComposable(
                context = context,
                onFormSubmit = {
                    Log.d("Jetpack", "Form Successful: ${it}")
                },
                navController = navController
            )
            onDrawerItemSelected()
        }
        composable(route = Screen.Profile.route) {
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