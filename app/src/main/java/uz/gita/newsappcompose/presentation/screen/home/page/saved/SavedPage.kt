package uz.gita.newsappcompose.presentation.screen.home.page.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.*
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.ui.theme.NewsAppComposeTheme

object SavedPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.home_tab)
            val icon = rememberVectorPainter(Icons.Default.Favorite)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {

        NewsAppComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                HomePageContent()
            }
        }
    }
}


@Composable
fun HomePageContent() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Green))

}