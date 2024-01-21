package com.shashank.jetpackcomposeuidemo.view.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shashank.jetpackcomposeuidemo.R
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormComposable(
    context: Context,
    onSubmit: (JSONObject) -> Unit) {
    val techStacks = arrayOf("Android", "iOS", "Java", "Docker", "NodeJS")


    //Define states
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
    var techStack by remember {
        mutableStateOf("")
    }
    var selectedText by remember { mutableStateOf(techStacks[0]) }


    //define the effect
    LaunchedEffect(readyToSubmit) {
        if(readyToSubmit) {
            val tempForm = JSONObject()
            tempForm.put("name", name)
            tempForm.put("address", address)
            tempForm.put("working", working)
            tempForm.put("techStack", techStack)
            formJson = tempForm //update the state
            readyToSubmit = false
            onSubmit(formJson)
        }
    }

    //define the UI widgets
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
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = name,
                onValueChange = {value -> name = value},
                placeholder = { Text(text = "Enter your Name")}
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = address,
                onValueChange = {value -> address = value},
                maxLines = 2,
                placeholder = { Text(text = "Enter your Address")}
            )
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                expanded = isExpanded,
                onExpandedChange = { newValue -> isExpanded = newValue }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ){

                    techStacks.forEach { item ->
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth().align(Alignment.End),
                            text = { Text(text = item)},
                            onClick = {
                                working = true
                                isExpanded = false
                                selectedText = item
                                techStack = item
                            })
                     }
                }
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
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