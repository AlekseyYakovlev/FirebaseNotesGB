package ru.spb.yakovlev.firebasenotesgb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.spb.yakovlev.firebasenotesgb.common.Note


object NotesRepository {
    private val listOfNotes = mutableListOf<Note>()
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        listOfNotes.add(Note(1L))
        reload()
    }

    fun addBlankNote() {
        val newId = getMaxId() + 1
        listOfNotes.add(Note(newId))
        reload()
    }

    fun reload() {
        _notes.postValue(listOfNotes)
    }

    fun updateNote(
        note: Note,
        newTitle: String? = null,
        newText: String? = null,
        newColor: Int? = null
    ) {
        val i = listOfNotes.indexOfFirst { it == note }
        with(listOfNotes[i]) {
            newTitle?.let { title = it }
            newText?.let { text = it }
            newColor?.let { color = it }
        }
        reload()
    }

    fun swap(item1: Note, item2: Note) {
        val index1 = listOfNotes.indexOf(item1)
        val index2 = listOfNotes.indexOf(item2)
        if (index1 == -1 || index2 == -1) return
        listOfNotes[index2] = item1
        listOfNotes[index1] = item2
        reload()
    }

    fun delete(item: Note) {
        listOfNotes.remove(item)
        reload()
    }

    private fun getMaxId() = listOfNotes.map { it.id }.max() ?: 0
}