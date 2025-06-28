package com.pycreations.fcmdemo.presentation.screens

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.pycreations.fcmdemo.R
import com.pycreations.fcmdemo.notifications.sendFcmNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Home() {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") } // can be empty

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionsState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.POST_NOTIFICATIONS
            )
        )
        LaunchedEffect(Unit) {
            permissionsState.launchMultiplePermissionRequest()
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Send Notification", fontSize = 20.sp, color = colorResource(R.color.black))
            Spacer(Modifier.height(40.dp))
            OutlinedTextField(value = title, placeholder = {}, onValueChange = {
                title = it
            }, label = {
                Text(
                    text = "Title",
                    color = colorResource(R.color.black).copy(0.6f),
                    fontSize = 16.sp
                )
            })
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(value = body, placeholder = {}, onValueChange = {
                body = it
            }, label = {
                Text(
                    text = "Body",
                    color = colorResource(R.color.black).copy(0.6f),
                    fontSize = 16.sp
                )
            })
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(value = type, placeholder = {}, onValueChange = {
                type = it
            }, label = {
                Text(
                    text = "Category",
                    color = colorResource(R.color.black).copy(0.6f),
                    fontSize = 16.sp
                )
            })
            Spacer(Modifier.height(20.dp))
            ElevatedButton(
                onClick = {
                    if (checkValid(title, body, type)) {
                        CoroutineScope(Dispatchers.IO).launch {
                            sendFcmNotification(
                                context,
                                title.trim(),
                                body.trim(),
                                type.trim().lowercase()
                            )
                            title = ""
                            body = ""
                            type = ""
                        }
                        Toast.makeText(context, "Sent Successfully!", Toast.LENGTH_SHORT).show()
                    }
                }, content = {
                    Text(text = "Send", color = colorResource(R.color.white), fontSize = 20.sp)
                }, colors = ButtonColors(
                    containerColor = Color.Gray, contentColor = colorResource(R.color.white),
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = colorResource(R.color.white)
                )
            )
        }
    }
}

private fun checkValid(title: String, body: String, type: String): Boolean {
    return !(title.isEmpty() || body.isEmpty() || type.isEmpty())
}