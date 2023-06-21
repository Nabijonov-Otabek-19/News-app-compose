package uz.gita.newsappcompose.presentation.screen.home.page.saved

import org.orbitmvi.orbit.ContainerHost

interface SavedContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        object LoadLatestNews : Intent
    }

    sealed interface UIState {}

    sealed interface SideEffect {}

}