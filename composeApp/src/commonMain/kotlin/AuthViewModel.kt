import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

class AuthViewModel {

    private val _viewStates = MutableStateFlow<AuthViewStates>(AuthViewStates())
    val viewStates = _viewStates.asStateFlow()

    fun setStateEvents(stateEvents: AuthStateEvents) {
        when (stateEvents) {
            is AuthStateEvents.UpdateViewStates -> {
               updateState(stateEvents.authViewStates)
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
}

data class AuthViewStates(
    val loginEmail: String? = null,
    val loginPassword: String? = null,
    val signupEmail: String? = null,
    val signupPassword: String? = null,
)