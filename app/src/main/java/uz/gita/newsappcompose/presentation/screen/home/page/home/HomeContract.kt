package uz.gita.newsappcompose.presentation.screen.home.page.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.response.NewsData

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
    }

    interface Direction {

    }
}