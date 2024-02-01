package presentation.Notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import theme.darkColorBackground

class AddNotesScreen : Screen {
    @Composable
    override fun Content() {
        AddNotesScreenComposable()
    }
}

@Composable
fun AddNotesScreenComposable() {
    Column(modifier = Modifier.fillMaxSize().background(darkColorBackground).padding(20.dp)) {
        TextField(
            value = "",
            onValueChange = {

            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.h3,
            label = {
                Text("Title", style = MaterialTheme.typography.h3)
            }
        )
    }
}