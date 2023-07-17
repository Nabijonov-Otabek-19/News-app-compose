package uz.gita.newsappcompose.presentation.screen.home.page.saved

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.response.Result

interface SavedContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        object LoadLatestNews : Intent
        class OpenReadScreen(val result: Result) : Intent
    }

    sealed interface UIState {
        object Loading : UIState
        data class PrepareData(val resultData: List<Result>) : UIState
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun navigateToReadScreen(result: Result)
    }
}