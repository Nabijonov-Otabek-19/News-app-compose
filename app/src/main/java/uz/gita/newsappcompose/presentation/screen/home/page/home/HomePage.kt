package uz.gita.newsappcompose.presentation.screen.home.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.presentation.component.LoadingComponent
import uz.gita.newsappcompose.presentation.component.NewsItemComponent
import uz.gita.newsappcompose.ui.theme.NewsAppComposeTheme
import uz.gita.newsappcompose.utils.logger
import uz.gita.newsappcompose.utils.toast

object HomePage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.home_tab)
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel: HomeContract.ViewModel = getViewModel<HomeViewModel>()
        val uiState = viewModel.collectAsState()

        val context = LocalContext.current

        NewsAppComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = { TopBar() }
                ) {
                    HomePageContent(
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }

        viewModel.collectSideEffect {
            when (it) {
                is HomeContract.SideEffect.HasError -> {
                    logger("HomePageScreen Error = " + it.message)
                    toast(context, it.message)
                }
            }
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(65.dp)
                .padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null
        )
    }
}


@Composable
fun HomePageContent(
    uiState: State<HomeContract.UIState>,
    onEventDispatcher: (HomeContract.Intent) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {

        when (uiState.value) {
            HomeContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher.invoke(HomeContract.Intent.LoadLatestNews)
            }

            is HomeContract.UIState.PrepareData -> {
                val data = (uiState.value as HomeContract.UIState.PrepareData).newsData

                LazyColumn {

                    items(data.results.size) {
                        NewsItemComponent(
                            data.results[it],
                            Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}