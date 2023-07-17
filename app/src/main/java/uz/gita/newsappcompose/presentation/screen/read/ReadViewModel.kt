package uz.gita.newsappcompose.presentation.screen.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.domain.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class ReadViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val direction: ReadDirection
) : ReadContract.ViewModel, ViewModel() {

    override val container =
        container<ReadContract.UIState, ReadContract.SideEffect>(ReadContract.UIState.Init)

    override fun onEventDispatcher(intent: ReadContract.Intent) {
        when (intent) {
            ReadContract.Intent.Back -> {
                viewModelScope.launch { direction.back() }
            }

            is ReadContract.Intent.CheckNews -> {
                intent {
                    reduce {
                        ReadContract.UIState.CheckNews(repository.checkSavedNews(intent.result.title))
                    }
                }
            }

            is ReadContract.Intent.DeleteNews -> {
                repository.deleteFromSaved(intent.result)
            }

            is ReadContract.Intent.SaveNews -> {
                repository.addToSaved(intent.result)
            }
        }
    }
}