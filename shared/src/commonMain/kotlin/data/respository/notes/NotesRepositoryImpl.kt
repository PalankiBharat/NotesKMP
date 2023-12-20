package data.respository.notes

import Note
import org.koin.core.component.KoinComponent

class NotesRepositoryImpl:NotesRepository, KoinComponent {
    override suspend fun addNote(note: Note): Boolean {
        return true
    }

    override suspend fun getNotesPerUserId(userId: String): List<Note> {
        return emptyList()
    }
}