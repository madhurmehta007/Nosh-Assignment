package dev.redfox.noshassignment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.redfox.noshassignment.model.Item
import dev.redfox.noshassignment.ui.theme.Orange
import dev.redfox.noshassignment.ui.theme.TextColor




@Composable
fun HorizontalLazyList(items: List<Item>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        items(items.size) { index ->
            ItemCard(item = items[index])
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Spacer(modifier = Modifier.width(25.dp))

    Card(
        modifier = Modifier
            .height(70.dp)
            .clip(CircleShape)
            .padding(8.dp)
            .shadow(10.dp, CircleShape, spotColor = Orange),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = item.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.title,
                fontSize = 24.sp,
                color = TextColor,
                modifier = Modifier.align(Alignment.CenterVertically).padding(end = 10.dp)
            )
        }
    }

}

