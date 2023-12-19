package data.respository.notes

import Note

class NotesRepositoryImpl:NotesRepository {
    override suspend fun addNote(note: Note): Boolean {
        return true
    }

    override suspend fun getNotesPerUserId(userId: String): List<Note> {
        return emptyList()
    }
}