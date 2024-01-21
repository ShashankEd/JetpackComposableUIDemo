package com.shashank.jetpackcomposeuidemo

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormComposable(context: Context, onSubmit: (JSONObject) -> Unit) {

    var name by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var working by remember {
        mutableStateOf(true)
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var formJson by remember {
        mutableStateOf(JSONObject())
    }
    var readyToSubmit by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(readyToSubmit) {
        if(readyToSubmit) {
            val tempForm = JSONObject()
            tempForm.put("name", name)
            tempForm.put("address", address)
            tempForm.put("working", working)
            formJson = tempForm //update the state
            readyToSubmit = false
            onSubmit(formJson)
        }
    }

    // A text field to get name
    // A drop down list for gender
    // A radio button for working status
    // A save button to save the data into the DB

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {value -> name = value},
                placeholder = { Text(text = "Enter your Name")}
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                value = address,
                onValueChange = {value -> address = value},
                maxLines = 2,
                placeholder = { Text(text = "Enter your Address")}
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue -> isExpanded = newValue }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Working")},
                    onClick = {
                        working = true
                        isExpanded = false
                })
                DropdownMenuItem(
                    text = { Text(text = "Not Working")},
                    onClick = {
                        working = false
                        isExpanded = false
                })
            }
            Button(onClick = {
                if(name.isNotEmpty() && address.isNotEmpty()) {
                    readyToSubmit = true
                }
            }
            ) {
                Text(text = "Confirm")
            }
        }
    }

}