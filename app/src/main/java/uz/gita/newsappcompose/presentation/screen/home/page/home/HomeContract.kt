package uz.gita.newsappcompose.presentation.screen.home.page.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.response.ResultData

interface HomeContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Loading : UIState
        data class PrepareData(val newsData: NewsData) : UIState
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }

    sealed interface Intent {
        object LoadLatestNews : Intent
        class OpenReadScreen(val resultData: ResultData) : Intent
    }

    interface Direction {
        suspend fun navigateToReadScreen(resultData: ResultData)
    }
}