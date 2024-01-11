package com.bharat.noteskmp.previews

import Note
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.toImmutableList
import presentation.Notes.Notes
import presentation.Notes.NotesItem
import presentation.Notes.NotesListing


@Preview()
@Composable
fun LoginCardPrev() {
    NotesItem(
        note = Note(
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
    )
}

@Preview(showSystemUi = true)
@Composable
fun NotesListPrev() {
    NotesListing(
        listOf(
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
            Notes.noteItem,
        ).toImmutableList()
    )
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
}