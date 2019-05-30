package ru.spb.yakovlev.firebasenotesgb.features.note_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spb.yakovlev.firebasenotesgb.common.Note

class NoteDetailsViewModel : ViewModel() {
    var tempNote = Note()
    val noteItem = MutableLiveData<Note>()
}
