package ru.spb.yakovlev.firebasenotesgb.features.note_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.data.NotesRepository

class GetNoteByIdUseCase {
    val repo = NotesRepository
    fun getNoteById(id: Long): LiveData<Note> =
        Transformations.map(repo.notes) { notesList -> notesList.find { it.id == id } }
}