package com.shashank.jetpackcomposeuidemo.presentation.view.composables

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
fun ProfileComposable(context: Context, onFormSubmit: (JSONObject) -> Unit, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 40.dp, horizontal = 20.dp)
    ) {
        Text(text = "Profile")
    }
}