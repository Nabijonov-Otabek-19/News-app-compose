package uz.gita.newsappcompose.presentation.screen.home.page.home

import uz.gita.newsappcompose.data.response.Result
import uz.gita.newsappcompose.navigation.AppNavigator
import uz.gita.newsappcompose.presentation.screen.read.ReadScreen
import javax.inject.Inject


class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeContract.Direction {

    override suspend fun navigateToReadScreen(result: Result) {
        appNavigator.navigateTo(ReadScreen(result))
    }
}