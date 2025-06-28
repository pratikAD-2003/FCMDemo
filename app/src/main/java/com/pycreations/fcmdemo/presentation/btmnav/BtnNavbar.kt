package com.pycreations.fcmdemo.presentation.btmnav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.pycreations.fcmdemo.R
import com.pycreations.fcmdemo.presentation.screens.Cart
import com.pycreations.fcmdemo.presentation.screens.Favorite
import com.pycreations.fcmdemo.presentation.screens.Home
import com.pycreations.fcmdemo.presentation.screens.Profile

@Composable
fun BottomNavbar(index : Int) {
    val items = listOf<BtnNavItem>(
        BtnNavItem(Icons.Default.Home, "Home"),
        BtnNavItem(Icons.Default.Favorite, "Favorite"),
        BtnNavItem(Icons.Default.ShoppingCart, "Cart"),
        BtnNavItem(Icons.Default.Person, "Profile")
    )
    var selectedIndex by remember { mutableIntStateOf(index) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        icon = {
                            Icon(imageVector = item.imageVector, contentDescription = item.label)
                        },
                        label = {
                            Text(text =
                                item.label, color = colorResource(R.color.black), fontSize = 16.sp)
                        },
                        onClick = {
                            selectedIndex = index
                        })
                }
            }
        }
    ) { _ ->
        Box(
            modifier = Modifier
                .padding(bottom = 0.dp, start = 0.dp, end = 0.dp, top = 0.dp)
        ) {
            when (selectedIndex) {
                0 -> Home()
                1 -> Favorite()
                2 -> Cart()
                3 -> Profile()
            }
        }
    }
}

data class BtnNavItem(val imageVector: ImageVector, val label: String)