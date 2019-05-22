package ru.spb.yakovlev.firebasenotesgb.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.spb.yakovlev.firebasenotesgb.common.Note
import ru.spb.yakovlev.firebasenotesgb.databinding.ItemNoteBinding

class CustomAdapter : RecyclerView.Adapter<NoteHolder>() {

    private val itemsList = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount() = itemsList.size

    fun updateData(newItems: List<Note>) {
        val diffCallback = ItemsDiffCallback(itemsList, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemsList.clear()
        itemsList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}