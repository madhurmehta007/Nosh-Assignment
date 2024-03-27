package dev.redfox.noshassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.redfox.noshassignment.model.Item
import dev.redfox.noshassignment.model.RecommendationCardData
import dev.redfox.noshassignment.ui.components.AppBar
import dev.redfox.noshassignment.ui.components.HorizontalLazyList
import dev.redfox.noshassignment.ui.components.NavBar
import dev.redfox.noshassignment.ui.theme.Orange
import dev.redfox.noshassignment.ui.theme.TextColor


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Row {
                NavBar()
                
                Column {
                    AppBar({}, {})
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    Text(text = "What's on your mind?", fontSize = 24.sp, modifier = Modifier.padding(start = 45.dp), color = TextColor)

                    Spacer(modifier = Modifier.height(10.dp))

                    val items = listOf(
                        Item(imageUrl = R.drawable.ic_itallian_spaghetti2, title = "Rice Items"),
                        Item(imageUrl = R.drawable.ic_itallian_spaghetti2, title = "Indian"),
                        Item(imageUrl = R.drawable.ic_itallian_spaghetti2, title = "Curries"),
                        Item(imageUrl = R.drawable.ic_itallian_spaghetti2, title = "Soups"),
                        Item(imageUrl = R.drawable.ic_itallian_spaghetti2, title = "Desserts"),
                        Item(imageUrl = R.drawable.ic_itallian_spaghetti2, title = "Snacks"),
                    )

                    HorizontalLazyList(items = items)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Recommendations", fontSize = 24.sp, modifier = Modifier.padding(start = 45.dp), color = TextColor)

                    Spacer(modifier = Modifier.height(10.dp))

                    val recommendationItems = listOf(
                        RecommendationCardData(imageUrl = R.drawable.recommendation_image, title = "Italian Spaghetti \n Pasta", preparationTime = "30 min", preparationType = "Medium prep."),
                        RecommendationCardData(imageUrl = R.drawable.recommendation_image, title = "Italian Spaghetti \n Pasta", preparationTime = "30 min", preparationType = "Medium prep."),
                        RecommendationCardData(imageUrl = R.drawable.recommendation_image, title = "Italian Spaghetti \n Pasta", preparationTime = "30 min", preparationType = "Medium prep."),
                        RecommendationCardData(imageUrl = R.drawable.recommendation_image, title = "Italian Spaghetti \n Pasta", preparationTime = "30 min", preparationType = "Medium prep."),
                        RecommendationCardData(imageUrl = R.drawable.recommendation_image, title = "Italian Spaghetti \n Pasta", preparationTime = "30 min", preparationType = "Medium prep."),
                        RecommendationCardData(imageUrl = R.drawable.recommendation_image, title = "Italian Spaghetti \n Pasta", preparationTime = "30 min", preparationType = "Medium prep."),

                        )

                    RecommendationLazyList(items = recommendationItems)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Image(painter = painterResource(id = R.drawable.banner_image_1), contentDescription = "Banner 1", modifier = Modifier.height(60.dp).width(360.dp))

                        Spacer(modifier = Modifier.width(10.dp))

                        Image(painter = painterResource(id = R.drawable.banner_image_2), contentDescription = "Banner 2", modifier = Modifier.height(60.dp).width(360.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
         
                
            }

        }
    }

    @Composable
    fun RecommendationLazyList(items: List<RecommendationCardData>){

        val selectedIndex = remember { mutableStateOf(0) }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        ) {
            items(items.size) { index ->
                RecommendationCardItem(item = items[index],
                        isSelected = index == selectedIndex.value,
                    onItemSelected = { selectedIndex.value = index }
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RecommendationCardItem(
        item: RecommendationCardData,
        isSelected: Boolean,
        onItemSelected: () -> Unit
    ){
        val cardColor = if (isSelected) TextColor else Color.White
        val text1Color = if (isSelected) Color.White else TextColor
        val text2Color = if (isSelected) Color.LightGray else Color.Black

        Card(
            modifier = Modifier
                .width(240.dp)
                .height(350.dp)
                .padding(12.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp), spotColor = Orange)
                .clip(RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(
                containerColor = cardColor,
            ),
            onClick = { onItemSelected() }
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)

            ) {
                Image(painter = painterResource(id = item.imageUrl), contentDescription = "", modifier = Modifier.width(500.dp).height(200.dp))

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = item.title, modifier = Modifier.align(Alignment.CenterHorizontally), color = text1Color, fontSize = 22.sp)

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = item.preparationTime,color = text2Color, fontSize = 16.sp)

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = item.preparationType,color = text2Color, fontSize = 16.sp)
                }
            }
        }
    }
}



