package uz.gita.newsappcompose.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.response.Result

@Composable
fun NewsItemComponent(
    result: Result,
    modifier: Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(result.image_url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.imageplaceholder),
                    error = painterResource(id = R.drawable.noimage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = result.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Text(
                        text = result.description,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = result.source_id, fontSize = 14.sp)
                Text(text = result.pubDate, fontSize = 14.sp)
            }
        }
    }
}