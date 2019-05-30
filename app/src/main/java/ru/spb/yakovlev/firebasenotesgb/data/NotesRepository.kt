package ru.spb.yakovlev.firebasenotesgb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.common.NoteResult
import ru.spb.yakovlev.firebasenotesgb.data.remote.FireStoreProvider


object NotesRepository {
    private val provider = FireStoreProvider()
    val notes: LiveData<List<Note>> = uploadLiveData()

    fun addBlankNote() {
        var newId = (maxId() + 1L)
        val tt = maxId()
        Log.d("1234567", "notes.size ${notes.value?.size}, maxId = ${notes.value?.maxBy { it.id }?.id}")
        Log.d("1234567", "new id $newId, maxId = $tt")
        provider.saveNote(Note(newId.toString(), newId))

    }

    fun uploadLiveData(): LiveData<List<Note>> = Transformations.map(provider.getAllNotesLiveData()) { r ->
        if (r is NoteResult.Success<*>) {
            r as NoteResult.Success<List<Note>>

            Log.d("12345", "FS data fetched length ${r.data.size}")
            r.data
        } else {
            val tempList: List<Note> = mutableListOf<Note>().also { it.add(Note("0", 0L, "Error")) }
            tempList
        }
    }

    fun updateNote(
        note: Note,
        newTitle: String? = null,
        newText: String? = null,
        newColor: Int? = null
    ) {
        Log.d("12345", "update start note id ${note.id} title =$newTitle text =$newText")
        with(note) {
            newTitle?.let { title = it }
            newText?.let { text = it }
            newColor?.let { color = it }
            Log.d("12345", " note id ${note.id} title =$newTitle text =$newText")
        }

        provider.saveNote(note)
    }

    fun swap(item1: Note, item2: Note) {
        provider.deleteNote(item1)
        provider.deleteNote(item2)
        val tag1 = item1.fbTag
        item1.fbTag = item2.fbTag
        item2.fbTag = tag1
        provider.saveNote(item1)
        provider.saveNote(item2)
    }

    fun delete(item: Note) {
        provider.deleteNote(item)
    }

    private fun maxId(): Long = (notes.value?.maxBy { it.id }?.id) ?: 0L

}