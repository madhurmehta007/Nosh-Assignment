package dev.redfox.noshassignment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.redfox.noshassignment.R
import dev.redfox.noshassignment.ui.theme.Orange


@Composable
fun NavBar() {
    var selectedItem by remember { mutableStateOf(MenuItem.Cook) }

    Row {
        Box(
            modifier = Modifier
                .shadow(10.dp)
                .fillMaxHeight()
                .width(150.dp),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MenuItem("Cook", Icon.Cook, selectedItem == MenuItem.Cook) {
                    selectedItem = MenuItem.Cook
                }
                MenuItem("Favourites", Icon.Favourites, selectedItem == MenuItem.Favourites) {
                    selectedItem = MenuItem.Favourites
                }
                MenuItem("Manual", Icon.Manual, selectedItem == MenuItem.Manual) {
                    selectedItem = MenuItem.Manual
                }
                MenuItem("Device", Icon.Device, selectedItem == MenuItem.Device) {
                    selectedItem = MenuItem.Device
                }
                MenuItem("Preferences", Icon.Preferences, selectedItem == MenuItem.Preferences) {
                    selectedItem = MenuItem.Preferences
                }
                MenuItem("Settings", Icon.Settings, selectedItem == MenuItem.Settings) {
                    selectedItem = MenuItem.Settings
                }
            }

        }


    }


}

@Composable
fun MenuItem(text: String, icon: Int, isSelected: Boolean, onItemClick: () -> Unit) {
    val color = if (isSelected) Orange else Color.Black
        Column(
            modifier = Modifier
                .padding(vertical = 25.dp)
                .padding(end = 25.dp)
                .clickable(onClick = onItemClick),

        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null, tint = color, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(42.dp))
            Text(text, color = color)

        }

}


enum class MenuItem {
    Cook,
    Favourites,
    Manual,
    Device,
    Preferences,
    Settings
}

object Icon {
    val Cook = R.drawable.ic_cook
    val Favourites = R.drawable.ic_favorite
    val Settings = R.drawable.ic_settings
    val Manual = R.drawable.ic_manual
    val Device =  R.drawable.ic_device
    val Preferences = R.drawable.ic_account_circle
}
