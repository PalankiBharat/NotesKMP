package presentation.Notes

import Note
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import presentation.Notes.Notes.noteItem
import presentation.Notes.Notes.noteItem2
import presentation.Notes.Notes.noteItem3
import presentation.Notes.Notes.noteItem4
import theme.cardGradColor1
import theme.cardGradColor2
import theme.darkColorBackground

class NotesScreen : Screen {
    @Composable
    override fun Content() {
        NotesListing(
            listOf(
                noteItem3,
                noteItem,
                noteItem4,
                noteItem2,
                noteItem3,
                noteItem,
                noteItem2,
                noteItem4,
                noteItem,
            ).toImmutableList()
        )
    }
}

object Notes {
    val noteItem = Note(
        id = "123190eiedwej0239",
        title = "Do my things in Home",
        userId = "jkh3212123",
        description = "I have to add some groceries in my shop \n i have to cook for my dog. I have to add some groceries in my shop \n" +
                " i have to cook for my dog.  I have to add some groceries in my shop " +
                " i have to cook for my dog.  I have to add some groceries in my shop \n" +
                " i have to cook for my dog.   ",
        dateCreated = 1159,
        dateUpdated = 7568
    )

    val noteItem2 = Note(
        id = "123190eiedwej0239",
        title = "Do my things in Home",
        userId = "jkh3212123",
        description = "I have to add some groceries in my shop \n i have to cook for my dog. I have to add some groceries in my shop \n" +
                " i have to cook for my dog.  I have to add some groceries in my shop " +
                " i have to cook for my dog.  I have to add some groceries in my shop \n" +
                " i have to cook for my dog.   ",
        dateCreated = 1159,
        dateUpdated = 7568
    )

    val noteItem3 = Note(
        id = "123190eiedwej0239",
        title = "Do my things in Home",
        userId = "jkh3212123",
        description = "I have to add some groceries in my shop \n i have to cook for my dog.",
        dateCreated = 1159,
        dateUpdated = 7568
    )

    val noteItem4 = Note(
        id = "123190eiedwej0239",
        title = "Do my things in Home",
        userId = "jkh3212123",
        description = "I have to add some groceries in my shop ",
        dateCreated = 1159,
        dateUpdated = 7568
    )
}

@Composable
fun NotesListing(notesList: ImmutableList<Note>) {
    Box(modifier = Modifier.fillMaxSize().background(darkColorBackground)) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp
        ) {
            items(items = notesList) { note ->
                NotesItem(note)
            }
        }
    }
}


@Composable
fun NotesItem(note: Note) {
    Box(
        modifier = Modifier.padding(10.dp).background(
            brush = Brush.verticalGradient(
                listOf(
                    cardGradColor1,
                    cardGradColor1,
                    cardGradColor2,
                )
            ),
            shape = RoundedCornerShape(20.dp)
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = note.title, color = Color.White, textAlign = TextAlign.Center)
            Text(text = note.description, maxLines = 6)
        }
    }
}