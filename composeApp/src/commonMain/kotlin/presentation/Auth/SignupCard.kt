package presentation.Auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.darkColorBackground
import theme.themeYellow

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignUpPage(loginViewStates: AuthViewStates, setStateEvents: (AuthStateEvents) -> Unit) {
    val (isPasswordVisible, onVisiblityChange) = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 50.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f)
                .align(Alignment.TopCenter),
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Start Using Notsy",
                    color = themeYellow,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    label = {
                        Text("Enter your Name")
                    },
                    value = loginViewStates.loginEmail ?: "",
                    onValueChange = {
                        setStateEvents(
                            AuthStateEvents.UpdateViewStates(
                                authViewStates = AuthViewStates(
                                    loginEmail = it
                                )
                            )
                        )
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    label = {
                        Text("Enter your email")
                    },
                    value = loginViewStates.loginEmail ?: "",
                    onValueChange = {
                        setStateEvents(
                            AuthStateEvents.UpdateViewStates(
                                authViewStates = AuthViewStates(
                                    loginEmail = it
                                )
                            )
                        )
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f).padding(top = 20.dp),
                    label = {
                        Text("Enter your Password")
                    },
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

                Text(
                    text = "Forgot Password ?",
                    modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 10.dp),
                    textAlign = TextAlign.End
                )

                Button(
                    modifier = Modifier.fillMaxWidth(0.4f).padding(top = 10.dp),
                    onClick = {
                        setStateEvents(AuthStateEvents.Login)
                    }
                ) {
                    Text(
                        "SignIn",
                        color = darkColorBackground,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}