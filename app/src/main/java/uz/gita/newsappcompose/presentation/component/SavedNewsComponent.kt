package uz.gita.newsappcompose.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.response.Result

@Composable
fun SavedNewsComponent(
    result: Result,
    modifier: Modifier,
    onClick: (Result) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(result) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Column(modifier = Modifier.padding(8.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(result.image_url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.image),
                    error = painterResource(id = R.drawable.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = result.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Text(
                        text = result.description,
                        maxLines = 2,
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

@Preview(showBackground = true)
@Composable
fun SavedNewsPreview() {
    SavedNewsComponent(result = Result(
        category = listOf(),
        content = "Content",
        country = listOf(),
        creator = listOf(),
        description = "Description",
        image_url = "IMAGE URL",
        keywords = listOf(),
        language = "Language",
        link = "Link",
        pubDate = "02.02.2023",
        source_id = "Source id",
        title = "Title",
        video_url = ""
    ), modifier = Modifier.padding(8.dp), onClick = {})
}