package presentation.Auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import data.api.utils.ApiResult
import data.repo.auth.AuthRepository
import data.requests.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(val repository: AuthRepository) : KMMViewModel() {

    private val _viewStates = MutableStateFlow(AuthViewStates())
    val viewStates = _viewStates.asStateFlow()

    fun setStateEvents(stateEvents: AuthStateEvents) {
        when (stateEvents) {
            is AuthStateEvents.UpdateViewStates -> {
                updateState(stateEvents.authViewStates)
            }

            is AuthStateEvents.Login -> {
                login()
            }
        }
    }

    fun login() {
        viewModelScope.coroutineScope.launch {
            viewStates.value.apply {
                val response = repository.login(LoginRequest(loginEmail ?: "", loginPassword ?: ""))
                when (response) {
                    is ApiResult.GenericError -> {

                        _viewStates.value = _viewStates.value.copy(error = response.errorMessage)
                    }

                    ApiResult.NetworkError -> {
                        updateState(AuthViewStates(error = error))
                    }

                    is ApiResult.Success -> {
                        val data = response.data
                        updateState(AuthViewStates(loginResponseToken = data?.token))
                    }
                }
            }
        }

    }

    private fun updateState(newStates: AuthViewStates) {
        _viewStates.value.apply {
            val newState = _viewStates.value.copy(
                loginEmail = newStates.loginEmail ?: this.loginEmail,
                loginPassword = newStates.loginPassword ?: this.loginPassword,
                signupEmail = newStates.signupEmail ?: this.signupEmail,
                signupPassword = newStates.signupPassword ?: this.signupPassword,
            )
            _viewStates.value = newState
        }
    }
}

sealed class AuthStateEvents {
    data class UpdateViewStates(val authViewStates: AuthViewStates) : AuthStateEvents()
    data object Login : AuthStateEvents()
}

data class AuthViewStates(
    val loginEmail: String? = null,
    val loginPassword: String? = null,
    val signupEmail: String? = null,
    val signupPassword: String? = null,
    val loginResponseToken: String? = null,
    val error: String? = null
)