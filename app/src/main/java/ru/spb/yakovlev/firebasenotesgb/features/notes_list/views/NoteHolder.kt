package ru.spb.yakovlev.firebasenotesgb.features.notes_list.views

import android.text.TextWatcher
import androidx.recyclerview.widget.RecyclerView
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.databinding.ItemNoteBinding
import android.text.Editable


class NoteHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(noteItem: Note) {
        binding.note = noteItem
        setListeners()
        binding.executePendingBindings()
    }

    private fun setListeners() {
        binding.etTitle.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    binding.model?.onNoteTitleChanged(binding.note, s.toString())
                }
            }
        )
        binding.etText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    binding.model?.onNoteTextChanged(binding.note, s.toString())
                }
            }
        )
    }
}
