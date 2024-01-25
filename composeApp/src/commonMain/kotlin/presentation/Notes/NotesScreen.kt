package presentation.Notes

import Note
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expect_actuals.KMPToast
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.compose.koinInject
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
fun NotesPage(viewModel: NotesViewModel = koinInject()) {

    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(Unit) {
        //initialisation
        viewModel.setStateEvents(NotesStateEvents.GetAllNotes)
    }

    val viewStates = viewModel.viewStates.collectAsState()

    LaunchedEffect(viewStates.value) {
        viewStates.value.message?.let {
            KMPToast().showToast(it)
            viewModel.resetErrorState()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(darkColorBackground).padding(20.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Your Notes", style = MaterialTheme.typography.h2, color = Color.White)
            val listOfNotes = viewStates.value.listOfNotes ?: emptyList()
            NotesListing(
                listOfNotes.toImmutableList(),
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        FloatingActionButton(
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                navigator.push(AddNotesScreen())
                /* do something */
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier.size(60.dp),
            )
        }
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
            animation = tween(
                delayMillis = 0,
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            ),
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
                Modifier.border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, color = Color.White, style = MaterialTheme.typography.h4)
            Text(text = note.description, maxLines = 6)
        }
    }
}