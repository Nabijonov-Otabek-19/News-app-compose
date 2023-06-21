package uz.gita.newsappcompose.presentation.screen.home.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.domain.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(), HomeContract.ViewModel {

    override val container =
        container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState.Loading)

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.LoadLatestNews -> {
                repository.loadLatestNews().onEach { result ->
                    result.onSuccess {
                        intent { reduce { HomeContract.UIState.PrepareData(it) } }
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                HomeContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}