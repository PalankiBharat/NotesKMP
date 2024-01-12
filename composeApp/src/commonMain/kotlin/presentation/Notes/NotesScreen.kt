package presentation.Notes

import Note
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import presentation.Notes.Notes.noteItem
import presentation.Notes.Notes.noteItem2
import presentation.Notes.Notes.noteItem3
import presentation.Notes.Notes.noteItem4
import presentation.Utils.ifTrue
import theme.cardGradColor1
import theme.cardGradColor2
import theme.darkColorBackground

class NotesScreen : Screen {
    @Composable
    override fun Content() {
        NotesPage()
    }
}

@Composable
fun NotesPage() {
    Column(modifier = Modifier.fillMaxSize().background(darkColorBackground).padding(20.dp)) {
        Text(text = "Your Notes", style = MaterialTheme.typography.h2, color = Color.White)
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
            ).toImmutableList(),
            modifier = Modifier.padding(top = 10.dp)
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
fun NotesListing(notesList: ImmutableList<Note>, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().background(darkColorBackground)) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            verticalItemSpacing = 10.dp
        ) {
            items(items = notesList) { note ->
                NotesItem(note, onNoteLongClick = {
                    // open option to tag or delete and highlight notes app
                }) {
                    //todo go to notes Detail Screen
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesItem(
    note: Note,
    onNoteLongClick: () -> Unit = {},
    onNotesClick: () -> Unit = {}
) {
    val (isBoxHighlighted, OnBoxHighlightChange) = remember {
        mutableStateOf(false)
    }

    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateValue(
        initialValue = -1f,
        targetValue = 1f,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(delayMillis = 0, durationMillis = 300, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Box(
        modifier = Modifier
            .ifTrue(isBoxHighlighted, modifier = Modifier.rotate(rotation))
            .padding(horizontal = 8.dp).background(
            brush = Brush.verticalGradient(
                listOf(
                    cardGradColor1,
                    cardGradColor1,
                    cardGradColor2,
                )
            ),
            shape = RoundedCornerShape(20.dp)
        ).combinedClickable(
            onLongClick = {
                OnBoxHighlightChange(true)
                onNoteLongClick()
            },
            onClick = {
                OnBoxHighlightChange(false)
                onNotesClick()
            }
        ).ifTrue(
            isBoxHighlighted,
            Modifier.border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, color = Color.White, style = MaterialTheme.typography.h4)
            Text(text = note.description, maxLines = 6)
        }
    }
}