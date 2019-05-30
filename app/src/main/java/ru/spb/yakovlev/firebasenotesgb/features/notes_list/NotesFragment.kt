package ru.spb.yakovlev.firebasenotesgb.features.notes_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.spb.yakovlev.firebasenotesgb.R
import ru.spb.yakovlev.firebasenotesgb.databinding.NotesFragmentBinding
import ru.spb.yakovlev.firebasenotesgb.features.notes_list.views.CustomAdapter
import ru.spb.yakovlev.firebasenotesgb.features.notes_list.views.NoteHolder
import ru.spb.yakovlev.firebasenotesgb.utils.toast

class NotesFragment : Fragment() {
    private val defaultColumnCount = 2

    private lateinit var viewModel: NotesViewModel
    private lateinit var binding: NotesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NotesFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        configureRecyclerView()
        setupFab()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.context?.toast("starting...")
    }

    private fun configureRecyclerView() {
        val columnCount = (activity?.resources?.getInteger(R.integer.column_count)) ?: defaultColumnCount
        val notesAdapter = CustomAdapter(viewModel)
        viewModel.notes.observe(
            this@NotesFragment,
            Observer { list -> notesAdapter.updateData(list) }
        )

        val itemTouchHelper = configureItemTouchHelper(columnCount)

        with(binding.rvNotes) {
            layoutManager = when (columnCount) {
                1 -> androidx.recyclerview.widget.LinearLayoutManager(context)
                else -> androidx.recyclerview.widget.GridLayoutManager(context, columnCount)
            }
            //TODO: Add custom animation
            //itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

            itemTouchHelper.attachToRecyclerView(this@with)
            adapter = notesAdapter
        }
    }

    private fun configureItemTouchHelper(columnCount: Int): ItemTouchHelper {
        val dragDirs: Int
        val swipeDirs: Int
        when (columnCount) {
            1 -> {
                dragDirs = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeDirs = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            }
            else -> {
                dragDirs = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                swipeDirs = 0
            }
        }
        return ItemTouchHelper(getItemTouchHelperSimpleCallback(dragDirs, swipeDirs))
    }

    private fun getItemTouchHelperSimpleCallback(dragDirs: Int, swapDirs: Int) =
        object : ItemTouchHelper.SimpleCallback(dragDirs, swapDirs) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val item1 = (viewHolder as NoteHolder).binding.note
                val item2 = (target as NoteHolder).binding.note
                viewModel.onItemsSwap(item1, item2)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val item = (viewHolder as NoteHolder).binding.note
                viewModel.onItemSwiped(item)
            }
        }

    private fun setupFab(){
        binding.fab.setOnClickListener {
            it.context.toast("onFabClicked")
            viewModel.onFabClicked()
            }
    }
}
