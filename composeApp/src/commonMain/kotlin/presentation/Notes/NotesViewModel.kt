package presentation.Notes

import Note
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import data.api.utils.ApiResult
import data.repo.notes.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(val repository: NotesRepository) : KMMViewModel() {

    private val _viewStates = MutableStateFlow(NotesViewStates())
    val viewStates = _viewStates.asStateFlow()

    fun setStateEvents(stateEvents: NotesStateEvents) {
        when (stateEvents) {
            is NotesStateEvents.UpdateViewStates -> {
                updateState(stateEvents.notesViewStates)
            }

            is NotesStateEvents.GetAllNotes -> {
                getAllNotes()
            }
        }
    }

    private fun getAllNotes() {
        viewModelScope.coroutineScope.launch {
            viewStates.value.apply {
                val response = repository.getAllNotes()
                when (response) {
                    is ApiResult.GenericError -> {
                        updateState(NotesViewStates(message = response.errorMessage))
                    }

                    ApiResult.NetworkError -> {
                        updateState(NotesViewStates(message = "No Internet"))
                    }

                    is ApiResult.Success -> {
                        val data = response.data
                        updateState(
                            NotesViewStates(
                                listOfNotes = data,
                                message = "Notes Fetched Successfully"
                            )
                        )
                    }
                }
            }
        }

    }

    fun resetErrorState() {
        _viewStates.value = _viewStates.value.copy(message = null)
    }

    private fun updateState(newStates: NotesViewStates) {
        _viewStates.value.apply {
            val newState = _viewStates.value.copy(
                listOfNotes = newStates.listOfNotes ?: this.listOfNotes,
                message = newStates.message ?: this.message,
            )
            _viewStates.value = newState
        }
    }
}

sealed class NotesStateEvents {
    data class UpdateViewStates(val notesViewStates: NotesViewStates) : NotesStateEvents()
    data object GetAllNotes : NotesStateEvents()
}

data class NotesViewStates(
    val listOfNotes: List<Note>? = emptyList(),
    val message: String? = null
)