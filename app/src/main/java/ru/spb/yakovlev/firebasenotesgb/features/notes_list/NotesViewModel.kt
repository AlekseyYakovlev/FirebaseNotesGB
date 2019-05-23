package ru.spb.yakovlev.firebasenotesgb.features.notes_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.data.NotesRepository


class NotesViewModel : ViewModel() {
    private val noteRepo = NotesRepository
    val notes: LiveData<List<Note>> = noteRepo.notes

    fun onNoteTitleChanged(note: Note?, title: String) {
        note?.let { noteRepo.updateNote(it, newTitle = title) }
    }

    fun onNoteTextChanged(note: Note?, text: String) {
        note?.let {noteRepo.updateNote(note, newText = text)}
    }

    fun onItemsSwap(item1: Note?, item2: Note?) {
        item1?.run { item2?.run { noteRepo.swap(item1, item2) } }
    }

    fun onItemSwiped(item: Note?) {
        item?.let { noteRepo.delete(it) }
    }

    fun onFabClicked() {
        noteRepo.addBlankNote()
    }
}
