package presentation.Notes

import Note
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class NotesScreen:Screen {
    @Composable
    override fun Content() {

    }
}

@Composable
fun NotesListingScreen() {
    Box {

    }
}


@Composable
fun NotesItem(note:Note) {
    Card {
        Column {
            Text(text = note.title)
            Text(text = note.description)
        }
    }
}