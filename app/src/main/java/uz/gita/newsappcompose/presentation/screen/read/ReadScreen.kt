package uz.gita.newsappcompose.presentation.screen.read

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.response.Result
import uz.gita.newsappcompose.navigation.AppScreen
import uz.gita.newsappcompose.ui.theme.NewsAppComposeTheme

class ReadScreen(private val result: Result) : AppScreen() {
    @Composable
    override fun Content() {
        NewsAppComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                ReadScreenContent(result = result)
            }
        }
    }
}

@Composable
fun ReadScreenContent(result: Result) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
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
                fontSize = 20.sp,
                color = Color.Black
            )

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

            Text(
                text = result.description,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = result.content,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Language : ${result.language}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = result.link,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ReadScreenPreview() {
    NewsAppComposeTheme {
        ReadScreenContent(
            result = Result(
                category = listOf(),
                content = "Content",
                country = listOf(),
                creator = listOf(),
                description = "Description",
                image_url = "",
                keywords = listOf(),
                language = "Language",
                link = "",
                pubDate = "20.02.2023",
                source_id = "",
                title = "Title",
                video_url = Any()
            )
        )
    }
}