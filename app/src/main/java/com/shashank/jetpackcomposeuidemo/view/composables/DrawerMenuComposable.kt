package com.shashank.jetpackcomposeuidemo.view.composables

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shashank.jetpackcomposeuidemo.R
import com.shashank.jetpackcomposeuidemo.core.utils.Screen
import com.shashank.jetpackcomposeuidemo.view.navigation.NavigationComposable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenuComposable(context: Context, navHostController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = {Text("Login")},
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "setting clicked", Toast.LENGTH_LONG).show()
                        navHostController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Login.route)
                        }
                    }

                )
                Divider()
                NavigationDrawerItem(
                    label = {Text("Dashboard")},
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "setting clicked", Toast.LENGTH_LONG).show()
                        navHostController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Dashboard.route)
                        }
                    }

                )
                Divider()
                NavigationDrawerItem(
                    label = {Text("Profile")},
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "setting clicked", Toast.LENGTH_LONG).show()
                        navHostController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Profile.route)
                        }
                    }

                )
            }
        }) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
//            floatingActionButton = {
//                ExtendedFloatingActionButton(onClick = {
//                    scope.launch {
//                        drawerState.apply {
//                            if(this.isClosed) open() else close()
//                        }
//                    }
//                }) {
//                    Text(text = "Open Drawer")
//                }
//            },
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        modifier = Modifier.align(Alignment.Top),
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if(this.isClosed) open() else close()
                                }
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.drawer_menu),
                            contentDescription = "drawer icon"
                        )
                    }
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Jetpack Navigation Drawer"
                    )

                }
//                TopAppBar(
//                    modifier = Modifier.background(Color.Blue),
//                    actions = {
//                        IconButton(
//                            modifier = Modifier.align(Alignment.CenterVertically),
//                            onClick = {
//                                scope.launch {
//                                    drawerState.apply {
//                                        if(this.isClosed) open() else close()
//                                    }
//                                }
//                            }
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.drawer_menu),
//                                contentDescription = "drawer icon"
//                            )
//                        }
//                    },
//                    title = { Text(text = "Jetpack Navigation Drawer") } ,
//                )
            }
        ) {
            paddingValues -> paddingValues.calculateTopPadding()
//            LoginComposable(
//                context = context,
//                onFormSubmit = {
//                    Log.d("Jetpack", "Form Successful: ${it}")
//                }
//            )
            NavigationComposable(context = context, navController = navHostController)

        }
        
    }

}