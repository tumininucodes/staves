package com.tecx.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecx.notes.viewholder.ViewHolder
import com.tecx.notes.databinding.RecyclerViewChildBinding
import com.tecx.notes.db.Notes
import com.tecx.notes.ui.NoteActivity

class NoteAdapter(
    private val list: MutableList<Notes>,
    private val activity: NoteActivity
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewChildBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleLine.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}