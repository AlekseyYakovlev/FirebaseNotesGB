package ru.spb.yakovlev.firebasenotesgb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.spb.yakovlev.firebasenotesgb.common.Note


object NotesRepository {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes
    private val listOfNotes by lazy {
        mutableListOf<Note>().also {
            it.add(Note(1L))
            updateLiveData()
        }
    }

    fun addBlankNote() {
        val newId = getMaxId() + 1
        listOfNotes.add(Note(newId))
        updateLiveData()
    }

    fun updateLiveData() {
        _notes.postValue(listOfNotes)
    }

    fun updateNote(
        noteId: Long,
        newTitle: String? = null,
        newText: String? = null,
        newColor: Int? = null
    ) {
        val i = listOfNotes.indexOfFirst { it.id == noteId }
        with(listOfNotes[i]) {
            newTitle?.let { title = it }
            newText?.let { text = it }
            newColor?.let { color = it }
        }
        updateLiveData()
    }

    fun swap(item1: Note, item2: Note) {
        val index1 = listOfNotes.indexOf(item1)
        val index2 = listOfNotes.indexOf(item2)
        if (index1 == -1 || index2 == -1) return
        listOfNotes[index2] = item1
        listOfNotes[index1] = item2
        updateLiveData()
    }

    fun delete(item: Note) {
        listOfNotes.remove(item)
        updateLiveData()
    }

    private fun getMaxId() = listOfNotes.map { it.id }.max() ?: 0

}