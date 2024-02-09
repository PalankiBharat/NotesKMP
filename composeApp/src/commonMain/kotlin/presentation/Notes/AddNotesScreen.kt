package presentation.Notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import theme.darkColorBackground

class AddNotesScreen : Screen {
    @Composable
    override fun Content() {
        AddNotesScreenComposable()
    }
}

@Composable
fun AddNotesScreenComposable(viewModel: AddNotesViewModel = koinInject()) {
    val uiStates = viewModel.viewStates.collectAsState()
    Column(modifier = Modifier.fillMaxSize().background(darkColorBackground).padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back Button",
                tint = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Save Button",
                    tint = Color.White
                )
            }

            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Button",
                    tint = Color.White

                )
            }
        }
        OutlinedTextField(
            value = uiStates.value.title ?: "",
            onValueChange = {
                viewModel.setStateEvents(
                    AddNotesStateEvents.UpdateViewStates(
                        AddNotesViewStates(
                            title = it
                        )
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.h3,
            label = {
                Text("Title", style = MaterialTheme.typography.h3)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White)
        )

        OutlinedTextField(
            value = uiStates.value.description ?: "",
            onValueChange = {
                viewModel.setStateEvents(
                    AddNotesStateEvents.UpdateViewStates(
                        AddNotesViewStates(
                            description = it
                        )
                    )
                )
            },
            modifier = Modifier.fillMaxSize().padding(top = 20.dp),
            textStyle = MaterialTheme.typography.h3,
            label = {
                Text("Description", style = MaterialTheme.typography.h3)
            }

        )
    }
}