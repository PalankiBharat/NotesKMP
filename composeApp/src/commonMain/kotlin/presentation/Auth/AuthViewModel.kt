package presentation.Auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import data.api.utils.ApiResult
import data.preferance.PreferenceManager
import data.repo.auth.AuthRepository
import data.requests.LoginRequest
import data.requests.SignupRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(val repository: AuthRepository, val preferenceManager: PreferenceManager) :
    KMMViewModel() {

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

            is AuthStateEvents.Signup -> {
                signup()
            }
        }
    }

    private fun login() {
        viewModelScope.coroutineScope.launch {
            viewStates.value.apply {
                val response = repository.login(LoginRequest(loginEmail ?: "", loginPassword ?: ""))
                when (response) {
                    is ApiResult.GenericError -> {
                        updateState(AuthViewStates(message = response.errorMessage))
                    }

                    ApiResult.NetworkError -> {
                        updateState(AuthViewStates(message = "No Internet"))
                    }

                    is ApiResult.Success -> {
                        val data = response.data
                        updateState(AuthViewStates(loginResponseToken = data?.token))
                        updateState(AuthViewStates(message = "Successfully Logged In"))
                        preferenceManager.saveToken(data?.token ?: "")
                    }
                }
            }
        }

    }

    private fun signup() {
        viewModelScope.coroutineScope.launch {
            viewStates.value.apply {
                val response = repository.signUp(
                    SignupRequest(
                        name = signupName ?: "",
                        email = signupEmail ?: "",
                        password = signupPassword ?: ""
                    )
                )
                when (response) {
                    is ApiResult.GenericError -> {
                        updateState(AuthViewStates(message = response.errorMessage))
                    }

                    ApiResult.NetworkError -> {
                        updateState(AuthViewStates(message = "No Internet"))
                    }

                    is ApiResult.Success -> {
                        val data = response.data
                        updateState(AuthViewStates(loginResponseToken = data?.token))
                        updateState(AuthViewStates(message = "Signup Successful"))
                        preferenceManager.saveToken(data?.token ?: "")
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
                signupName = newStates.signupName ?: this.signupName,
                signupPassword = newStates.signupPassword ?: this.signupPassword,
                loginResponseToken = newStates.loginResponseToken ?: this.loginResponseToken,
                message = newStates.message ?: this.message,
            )
            _viewStates.value = newState
        }
    }
}

sealed class AuthStateEvents {
    data class UpdateViewStates(val authViewStates: AuthViewStates) : AuthStateEvents()
    data object Login : AuthStateEvents()
    data object Signup : AuthStateEvents()
}

data class AuthViewStates(
    val loginEmail: String? = null,
    val loginPassword: String? = null,
    val signupEmail: String? = null,
    val signupName: String? = null,
    val signupPassword: String? = null,
    val loginResponseToken: String? = null,
    val message: String? = null
)