package com.shashank.jetpackcomposeuidemo.presentation.view.composables

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComposable(context: Context, onFormSubmit: (JSONObject) -> Unit, navController: NavController) {

    //define state
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var confirmName by remember {
        mutableStateOf("")
    }
    var confirmAddress by remember {
        mutableStateOf("")
    }
    var confirmTechStack by remember {
        mutableStateOf("")
    }


    //the below launch effect will be triggered whenever  showBottomSheet value is changed
    LaunchedEffect(showBottomSheet) {
        Log.d("BottomSheet", "LaunchedEffect called for updated $showBottomSheet ")
    }

    //define UI widgets

    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        topBar = {
//                 TopAppBar(
//                     modifier = Modifier.background(Color.Blue),
//                     title = { Text(text = "Jetpack Compose") } ,
//                 )
//        },
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                onClick = { showBottomSheet = true},
//            ) {
//                Text(text = "Open Bottom sheet")
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End,
        content = {
            paddingValues -> paddingValues.calculateTopPadding()
            Column(
                modifier = Modifier.padding(vertical = 60.dp)
            ) {
                //one way
//                FormComposable(context = context) {
//                    showBottomSheet = true
//                    Log.d("Jetpack", "Form uploaded: ${it}")
//                }
                //another way
                FormComposable(
                    context = context,
                    onSubmit = {
                        Log.d("Jetpack", "Form uploaded: ${it}")
                        showBottomSheet = true
                        confirmAddress = it.get("address").toString()
                        confirmName = it.get("name").toString()
                        confirmTechStack = it.get("techStack").toString()
                        Log.d("Confirm Name from post form", confirmName)
                        Log.d("Confirm address from post form", confirmAddress)
                        Log.d("Confirm tech-stack from post form", confirmTechStack)
                    }
                )

                if(showBottomSheet) {
                    ModalBottomSheet(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 5.dp),
                        onDismissRequest = {
                            showBottomSheet = false
                        }, sheetState = sheetState,
                        dragHandle = {
//                            Toast.makeText(context,"Bottom sheet!", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                text = "Please Confirm your details!",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                text = "Name: $confirmName",
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                text = "Address: $confirmAddress",
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                text = "Tech Stack: $confirmTechStack",
                            )
                            Button(
                                modifier = Modifier.wrapContentWidth().padding(10.dp),
                                onClick = {
                                    showBottomSheet = false
                                    Toast.makeText(context,"Form Submitted!", Toast.LENGTH_LONG).show()
                                    //send the JSON further back to the mainactivity composable
                                    val successForm = JSONObject()
                                    successForm.put("name", confirmName)
                                    successForm.put("address", confirmAddress)
                                    successForm.put("techStack", confirmTechStack)
                                    onFormSubmit(successForm)
                                },
                                content = {
                                    Text(text = "Confirm & Submit!")
                                })
                        }
                    }
                }
            }

        }
    )
}