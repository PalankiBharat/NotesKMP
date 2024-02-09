package presentation.Notes

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import data.api.utils.ApiResult
import data.repo.notes.NotesRepository
import data.requests.AddNotesRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddNotesViewModel(val repository: NotesRepository) : KMMViewModel() {

    private val _viewStates = MutableStateFlow(AddNotesViewStates())
    val viewStates = _viewStates.asStateFlow()


    fun setStateEvents(stateEvents: AddNotesStateEvents) {
        when (stateEvents) {
            is AddNotesStateEvents.UpdateViewStates -> {
                updateState(stateEvents.notesViewStates)
            }

            is AddNotesStateEvents.AddNote -> {
                addNote()
            }
        }
    }

    private fun addNote() {
        viewModelScope.coroutineScope.launch {
            viewStates.value.apply {
                val response = repository.addNote(
                    AddNotesRequest(
                        title = this.title ?: "Blank One ",
                        description = this.description ?: "Why to add a blank note anyways"
                    )
                )
                when (response) {
                    is ApiResult.GenericError -> {
                        updateState(AddNotesViewStates(message = response.errorMessage))
                    }

                    ApiResult.NetworkError -> {
                        updateState(AddNotesViewStates(message = "No Internet"))
                    }

                    is ApiResult.Success -> {
                        updateState(
                            AddNotesViewStates(
                                message = "Notes Updated Successfully"
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

    private fun updateState(newStates: AddNotesViewStates) {
        _viewStates.value.apply {
            val newState = _viewStates.value.copy(
                title = newStates.title ?: this.title,
                description = newStates.description ?: this.description,
                loading = newStates.loading,
                message = newStates.message ?: this.message,
            )
            _viewStates.value = newState
        }
    }
}

sealed class AddNotesStateEvents {
    data object AddNote : AddNotesStateEvents()
    data class UpdateViewStates(val notesViewStates: AddNotesViewStates) : AddNotesStateEvents()

}

data class AddNotesViewStates(
    val title: String? = null,
    val description: String? = null,
    val loading: Boolean = false,
    val message: String? = null
)