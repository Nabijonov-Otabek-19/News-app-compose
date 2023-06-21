package uz.gita.newsappcompose.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.newsappcompose.navigation.AppScreen
import uz.gita.newsappcompose.presentation.screen.home.page.home.HomePage
import uz.gita.newsappcompose.presentation.screen.home.page.saved.SavedPage
import uz.gita.newsappcompose.ui.theme.NewsAppComposeTheme

class HomeScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        NewsAppComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                TabNavigator(HomePage) {
                    Scaffold(
                        content = {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(it)
                            ) {
                                CurrentTab()
                            }
                        },
                        bottomBar = {
                            NavigationBar(modifier = Modifier) {
                                TabNavigationItem(tab = HomePage)
                                TabNavigationItem(tab = SavedPage)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}