package com.pycreations.fcmdemo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.firebase.messaging.FirebaseMessaging
import com.pycreations.fcmdemo.presentation.btmnav.BottomNavbar
import com.pycreations.fcmdemo.ui.theme.FCMDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseMessaging.getInstance().subscribeToTopic("all_devices")
        val type = intent.getStringExtra("type")
        val startIndex = when (type) {
            "home" -> 0
            "favorite" -> 1
            "cart" -> 2
            "profile" -> 3
            else -> 0
        }
        setContent {
            FCMDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        BottomNavbar(startIndex)
                    }
                }
            }
        }
    }
}