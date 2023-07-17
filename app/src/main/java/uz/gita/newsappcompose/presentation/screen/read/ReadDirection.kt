package uz.gita.newsappcompose.presentation.screen.read

import uz.gita.newsappcompose.navigation.AppNavigator
import javax.inject.Inject

class ReadDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : ReadContract.Direction {

    override suspend fun back() {
        appNavigator.back()
    }
}