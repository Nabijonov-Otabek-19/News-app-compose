package uz.gita.newsappcompose.presentation.screen.home.page.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.domain.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val direction: SavedDirection
) : SavedContract.ViewModel, ViewModel() {

    override val container =
        container<SavedContract.UIState, SavedContract.SideEffect>(SavedContract.UIState.Loading)

    override fun onEventDispatcher(intent: SavedContract.Intent) {
        when (intent) {
            SavedContract.Intent.LoadLatestNews -> {
                repository.getSavedNews().onEach { data ->
                    intent { reduce { SavedContract.UIState.PrepareData(data) } }
                }.launchIn(viewModelScope)
            }

            is SavedContract.Intent.OpenReadScreen -> {
                viewModelScope.launch {
                    direction.navigateToReadScreen(intent.result)
                }
            }
        }
    }
}