package presentation.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
    Box(modifier = Modifier.fillMaxWidth()) {
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
                Image(
                    modifier = Modifier.fillMaxWidth(0.35f)
                        .aspectRatio(1f)
                        .padding(bottom = 10.dp),
                    painter = painterResource("notsy_logo.png"),
                    contentDescription = "Notsy Logo"
                )
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
                    value = loginViewStates.signupEmail ?: "",
                    onValueChange = {
                        setStateEvents(
                            AuthStateEvents.UpdateViewStates(
                                authViewStates = AuthViewStates(
                                    signupEmail = it
                                )
                            )
                        )
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f).padding(top = 20.dp),
                    label = {
                        Text("Enter your email")
                    },
                    value = loginViewStates.signupName ?: "",
                    onValueChange = {
                        setStateEvents(
                            AuthStateEvents.UpdateViewStates(
                                authViewStates = AuthViewStates(
                                    signupName = it
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
                    value = loginViewStates.signupPassword ?: "", onValueChange = {
                        setStateEvents(
                            AuthStateEvents.UpdateViewStates(
                                authViewStates = AuthViewStates(
                                    signupPassword = it
                                )
                            )
                        )
                    })

                Button(
                    modifier = Modifier.fillMaxWidth(0.4f).padding(top = 20.dp),
                    onClick = {
                        setStateEvents(AuthStateEvents.Signup)
                    }
                ) {
                    Text(
                        "Signup",
                        color = darkColorBackground,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}