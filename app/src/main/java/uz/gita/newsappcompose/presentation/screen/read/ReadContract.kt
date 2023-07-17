package uz.gita.newsappcompose.presentation.screen.read

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.response.ResultData

interface ReadContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Init : UIState
        data class CheckNews(val isSaved: Boolean) : UIState
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class SaveNews(val resultData: ResultData) : Intent
        data class DeleteNews(val resultData: ResultData) : Intent
        data class CheckNews(val resultData: ResultData) : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun back()
    }
}