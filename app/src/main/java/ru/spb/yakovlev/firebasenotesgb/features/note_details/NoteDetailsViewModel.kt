package ru.spb.yakovlev.firebasenotesgb.features.note_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.data.NotesRepository

class NoteDetailsViewModel : ViewModel() {
    private val noteRepo = NotesRepository
    var tempNote = Note()
    val noteItem = MutableLiveData<Note>()

    fun updateNote() {
        noteRepo.updateNote()
    }

    fun

}
