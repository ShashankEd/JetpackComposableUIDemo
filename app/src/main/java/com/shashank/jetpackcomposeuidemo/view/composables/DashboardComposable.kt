package com.shashank.jetpackcomposeuidemo.view.composables

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.json.JSONObject

@Composable
fun DashboardComposable(context: Context, onFormSubmit: (JSONObject) -> Unit, navController: NavController) {
    //dashboard
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 40.dp, horizontal = 20.dp)
    ) {
        Text(text = "Dashboard")
    }
}