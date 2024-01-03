package presentation.Auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginPage(loginViewStates: AuthViewStates, setStateEvents: (AuthStateEvents) -> Unit) {
    val (isPasswordVisible, onVisiblityChange) = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = loginViewStates.loginEmail ?: "", onValueChange = {
            setStateEvents(
                AuthStateEvents.UpdateViewStates(
                    authViewStates = AuthViewStates(
                        loginEmail = it
                    )
                )
            )
        })
        TextField(
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onVisiblityChange(!isPasswordVisible) }) {
                    Icon(
                        painter = painterResource(if (isPasswordVisible) "eye.png" else "eye_hide.png"),
                        contentDescription = ""
                    )
                }
            },
            value = loginViewStates.loginPassword ?: "", onValueChange = {
                setStateEvents(
                    AuthStateEvents.UpdateViewStates(
                        authViewStates = AuthViewStates(
                            loginPassword = it
                        )
                    )
                )
            })

        Button(
            onClick = {
                setStateEvents(AuthStateEvents.Login)
            }
        ){
            Text("SignIn")
        }
    }
}