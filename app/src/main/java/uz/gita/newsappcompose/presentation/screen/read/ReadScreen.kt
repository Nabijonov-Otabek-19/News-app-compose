package uz.gita.newsappcompose.presentation.screen.read

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.response.Result
import uz.gita.newsappcompose.navigation.AppScreen
import uz.gita.newsappcompose.ui.theme.NewsAppComposeTheme
import uz.gita.newsappcompose.utils.toast

class ReadScreen(private val result: Result) : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel: ReadContract.ViewModel = getViewModel<ReadViewModel>()
        val uiState = viewModel.collectAsState()

        NewsAppComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = { TopBar(result, uiState, viewModel::onEventDispatcher) })
                { ReadScreenContent(result = result, Modifier.padding(it)) }
            }
        }
    }
}

@Composable
fun TopBar(
    result: Result,
    uiState: State<ReadContract.UIState>,
    onEventDispatcher: (ReadContract.Intent) -> Unit
) {

    val context = LocalContext.current
    onEventDispatcher(ReadContract.Intent.CheckNews(result))

    when (uiState.value) {
        is ReadContract.UIState.CheckNews -> {
            val isSaved = (uiState.value as ReadContract.UIState.CheckNews).isSaved
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    modifier = Modifier.padding(start = 12.dp),
                    onClick = { onEventDispatcher(ReadContract.Intent.Back) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }

                IconButton(
                    modifier = Modifier.padding(end = 12.dp),
                    onClick = {
                        if (isSaved) {
                            onEventDispatcher(ReadContract.Intent.DeleteNews(result))
                            toast(context, "News Removed")
                        } else {
                            onEventDispatcher(ReadContract.Intent.SaveNews(result))
                            toast(context, "News Saved")
                        }

                        onEventDispatcher(ReadContract.Intent.CheckNews(result))
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isSaved) R.drawable.ic_fav
                            else R.drawable.ic_not_fav
                        ),
                        contentDescription = null
                    )
                }
            }
        }

        else -> {}
    }
}

@Composable
fun ReadScreenContent(result: Result, modifier: Modifier) {
    Column(
        modifier = modifier
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
                color = Color.Gray,
                textAlign = TextAlign.Justify
            )

            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = result.content,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Justify
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