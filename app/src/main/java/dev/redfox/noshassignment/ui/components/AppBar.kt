package dev.redfox.noshassignment.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.redfox.noshassignment.R
import dev.redfox.noshassignment.ui.theme.DarkBlue
import dev.redfox.noshassignment.ui.theme.Red
import dev.redfox.noshassignment.ui.theme.TextColor

@Composable
fun AppBar(onNotificationClick: () -> Unit, onPowerOffClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.Transparent)
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search option
        SearchBar("Search for dish or ingredient",{})

        Spacer(modifier = Modifier.width(16.dp))

        // Circular image
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clip(CircleShape)
                .width(250.dp)
                .background(DarkBlue)

        ) {
           Row(modifier = Modifier.padding(5.dp)) {
               Image(
                   painter = painterResource(id = R.drawable.itallian_spaghetti),
                   contentDescription = "Food",
                   modifier = Modifier.size(60.dp)
               )
               Spacer(modifier = Modifier.width(16.dp))
               Column(
                   modifier = Modifier
                       .weight(1f)
                       .align(Alignment.CenterVertically)
               ) {
                   Text(
                       text = "Italian Spaghetti..",
                       fontSize = 17.sp,
                       color = Color.White
                   )
                   Spacer(modifier = Modifier.height(5.dp))
                   Text(
                       text = "Scheduled 6:30 AM",
                       fontSize = 14.sp,
                       color = Color.White
                   )
               }
           }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Notifications icon button
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications",
            modifier = Modifier
                .padding(end = 35.dp)
                .size(36.dp)
                .clickable(onClick = onNotificationClick),
                tint = TextColor
        )

//        Spacer(modifier = Modifier.width(16.dp))

        // Power off icon button
        Icon(
            painterResource(id = R.drawable.ic_power),
            contentDescription = "Power off",
            modifier = Modifier
                .padding(end = 35.dp)
                .size(36.dp)
                .clickable(onClick = onPowerOffClick),
            tint = Red
        )
    }
}

@Composable
fun SearchBar(hint: String = "Search", onValueChange: (String) -> Unit) {
    var textValue by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clip(CircleShape)
            .fillMaxHeight()
            .width(600.dp)
            .border(
                BorderStroke(2.dp, TextColor),
                shape = CircleShape
            )
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = {textValue = it
                            onValueChange(it.text)},
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, color = Color.Gray),
            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    innerTextField()
                }
            }
        )
        if (textValue.text.isEmpty()) {
            Text(
                text = hint,
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 45.dp)
            )
        }
    }
}