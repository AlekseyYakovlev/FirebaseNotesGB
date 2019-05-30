package ru.spb.yakovlev.firebasenotesgb.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.common.NoteResult

class FireStoreProvider : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
    }

    private val store = FirebaseFirestore.getInstance()
    private val notesReference = store.collection(NOTES_COLLECTION)

    override fun getAllNotesLiveData(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.value = NoteResult.Error(e)
            } else if (snapshot != null) {
                val notes = snapshot.documents.map { it.toObject(Note::class.java) }
                result.value = NoteResult.Success(notes)
            }
        }
        return result
    }

//     fun getNoteById(id: Long): LiveData<NoteResult> {
//        val result = MutableLiveData<NoteResult>()
//        val docKey = id.toString()
//        notesReference.document(docKey).get()
//            .addOnSuccessListener { snapshot ->
//                result.value = NoteResult.Success(snapshot.toObject(Note::class.java))
//            }
//            .addOnFailureListener { e ->
//                result.value = NoteResult.Error(e)
//            }
//        return result
//    }

    override fun saveNote(note: Note) {
        val docKey = note.fbTag
        notesReference.document(docKey).set(note)
            .addOnSuccessListener {
                Log.d("12345", "note ${note.title} saved")
            }
            .addOnFailureListener {
                Log.d("12345", "note ${note.title} not saved. Error: ${it.message}")
            }
    }

    override fun deleteNote(note: Note) {
        //val result = MutableLiveData<NoteResult>()
        val docKey = note.fbTag
        notesReference.document(docKey).delete()
            .addOnFailureListener {
                Log.d("12345", "note ${note.title} not deleted. Error: ${it.message}")
            }
    }
}