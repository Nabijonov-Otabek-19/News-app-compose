package uz.gita.newsappcompose.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.newsappcompose.navigation.AppScreen
import uz.gita.newsappcompose.presentation.screen.home.page.home.HomePage
import uz.gita.newsappcompose.presentation.screen.home.page.saved.SavedPage
import uz.gita.newsappcompose.ui.theme.Light_Blue
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
                            NavigationBar(
                                modifier = Modifier
                                    .height(70.dp),
                                containerColor = Color.Blue
                            ) {
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
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Light_Blue,
            unselectedIconColor = Color.White,
            indicatorColor = Color.White
        ),
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}